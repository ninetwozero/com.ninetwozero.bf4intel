package com.ninetwozero.bf4intel.services;

import android.app.Service;
import android.content.Intent;

import com.ninetwozero.bf4intel.Bf4Intel;
import com.ninetwozero.bf4intel.dao.assignments.AssignmentsDAO;
import com.ninetwozero.bf4intel.factories.UrlFactory;
import com.ninetwozero.bf4intel.json.assignments.Assignments;
import com.ninetwozero.bf4intel.network.SimpleGetRequest;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.utils.AssignmentSorter;
import com.ninetwozero.bf4intel.utils.AssignmentsRefreshedEvent;
import com.ninetwozero.bf4intel.utils.BusProvider;

import se.emilsjolander.sprinkles.Transaction;

public class AssignmentService extends BaseApiService {

    @Override
    public int onStartCommand(final Intent intent, final int flags, final int startId) {
        super.onStartCommand(intent, flags, startId);
        Bf4Intel.getRequestQueue().add(
            new SimpleGetRequest<Boolean>(
                UrlFactory.buildAssignmentsURL(
                    soldier.getString(Keys.Soldier.NAME, ""),
                    soldier.getString(Keys.Soldier.ID, ""),
                    soldier.getString(Keys.Profile.ID, ""),
                    soldier.getInt(Keys.Soldier.PLATFORM, 0)
                ),
                this
            ) {
                @Override
                protected Boolean doParse(String json) {
                    final Transaction transaction = new Transaction();
                    final Assignments assignments = fromJson(json, Assignments.class);
                    boolean success = true;

                    final AssignmentsDAO assignmentsDAO = new AssignmentsDAO(
                        soldier.getString(Keys.Soldier.ID),
                        soldier.getString(Keys.Soldier.NAME),
                        soldier.getInt(Keys.Soldier.PLATFORM),
                        AssignmentSorter.sort(assignments)
                    );

                    if (!assignmentsDAO.save(transaction)) {
                        success = false;
                    }

                    transaction.setSuccessful(success);
                    transaction.finish();
                    return success;
                }

                @Override
                protected void deliverResponse(Boolean result) {
                    BusProvider.getInstance().post(new AssignmentsRefreshedEvent(result));
                    stopSelf(startId);
                }
            }
        );
        return Service.START_NOT_STICKY;
    }
}
