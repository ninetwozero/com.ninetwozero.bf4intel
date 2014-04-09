package com.ninetwozero.bf4intel.services;

import android.app.Service;
import android.content.Intent;
import android.util.Log;

import com.ninetwozero.bf4intel.Bf4Intel;
import com.ninetwozero.bf4intel.events.RefreshCompletedEvent;
import com.ninetwozero.bf4intel.network.SimpleGetRequest;
import com.ninetwozero.bf4intel.utils.BusProvider;

import java.net.URL;

import se.emilsjolander.sprinkles.Model;
import se.emilsjolander.sprinkles.Transaction;

public abstract class BaseSingleDaoApiService<T extends Model, E extends RefreshCompletedEvent> extends BaseApiService {
    @Override
    public int onStartCommand(final Intent intent, final int flags, final int startId) {
        super.onStartCommand(intent, flags, startId);

        Bf4Intel.getRequestQueue().add(
            new SimpleGetRequest<Boolean>(
                getUrlForService(),
                this
            ) {
                @Override
                protected Boolean doParse(String json) {
                    final Transaction transaction = new Transaction();
                    boolean success = true;

                    final T daoObject = parseJsonIntoDao(json);
                    Log.d("YOLO", "daoObject => " + daoObject.getClass().getSimpleName());
                    if (!daoObject.save(transaction)) {
                        success = false;
                    }
                    Log.d("YOLO", "daoObject.save() => " + success);

                    transaction.setSuccessful(success);
                    transaction.finish();
                    return success;
                }

                @Override
                protected void deliverResponse(Boolean result) {
                    final E event = getEventToBroadcast(result);
                    Log.d("YOLO", "event => " + event.getClass().getSimpleName());
                    BusProvider.getInstance().post(event);
                    stopSelf(startId);
                }
            }
        );
        return Service.START_NOT_STICKY;
    }


    protected abstract T parseJsonIntoDao(String json);

    protected abstract URL getUrlForService();

    protected abstract E getEventToBroadcast(boolean result);
}
