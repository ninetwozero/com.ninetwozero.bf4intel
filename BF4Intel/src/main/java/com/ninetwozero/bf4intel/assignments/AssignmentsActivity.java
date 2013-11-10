package com.ninetwozero.bf4intel.assignments;

import android.os.Bundle;
import android.support.v4.content.Loader;
import android.util.Log;

import com.google.gson.JsonObject;
import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.IntelActivity;
import com.ninetwozero.bf4intel.connection.ConnectionRequest;
import com.ninetwozero.bf4intel.connection.IntelLoader;
import com.ninetwozero.bf4intel.utils.BusProvider;
import com.ninetwozero.bf4intel.utils.Result;

public class AssignmentsActivity extends IntelActivity {

    private static final int ID_LOADER = 1000;
    //TODO will be replaced with some url building logic in near future
    private static final String ASSIGNMENTS_URL = "http://battlelog.battlefield.com/bf4/soldier/missionsPopulateStats/LittleBoySVK/200661244/2832665149443593606/1/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignments);

        AssignmentsFragment assignmentsFragment = new AssignmentsFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.container_assignments, assignmentsFragment).commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSupportLoaderManager().initLoader(ID_LOADER, null, this);
    }

    @Override
    public Loader<Result> onCreateLoader(int i, Bundle bundle) {
        setLoadingState(true);
        return new IntelLoader(getApplicationContext(), new ConnectionRequest(ASSIGNMENTS_URL));
    }

    @Override
    public void onLoadFinished(Loader<Result> resultLoader, Result result) {
        if (result == Result.SUCCESS) {
            processResult(result.getResultMessage());
        } else {
            processError(result.getResultMessage());
        }
    }

    private void processResult(String resultMessage) {
        JsonObject dataJson = extractFromJson(resultMessage);
        Assignments assignments = gson.fromJson(dataJson, Assignments.class);
        setLoadingState(false);
        BusProvider.getInstance().post(assignments);
    }

    private void processError(String resultMessage) {
        Log.e(AssignmentsActivity.class.getSimpleName(), resultMessage);
    }
}
