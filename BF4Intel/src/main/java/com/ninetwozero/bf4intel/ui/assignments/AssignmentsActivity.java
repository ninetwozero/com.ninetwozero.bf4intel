package com.ninetwozero.bf4intel.ui.assignments;

import android.os.Bundle;
import android.support.v4.content.Loader;

import com.google.gson.JsonObject;
import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseLoadingIntelActivity;
import com.ninetwozero.bf4intel.factories.UrlFactory;
import com.ninetwozero.bf4intel.json.assignments.Assignments;
import com.ninetwozero.bf4intel.network.IntelLoader;
import com.ninetwozero.bf4intel.network.SimpleGetRequest;
import com.ninetwozero.bf4intel.utils.BusProvider;
import com.ninetwozero.bf4intel.utils.Result;

public class AssignmentsActivity extends BaseLoadingIntelActivity {

    private static final int ID_LOADER = 1000;

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
        showLoadingStateInActionBar(true);
        return new IntelLoader(getApplicationContext(), new SimpleGetRequest(UrlFactory.buildAssignmentsURL("LittleBoySVK", 200661244, 2832665149443593606L, 1)));
    }

    @Override
    protected void onLoadSuccess(Loader<Result> resultLoader, String resultMessage) {
        if (resultLoader.getId() == ID_LOADER) {
            processResult(resultMessage);
        }
    }

    private void processResult(String resultMessage) {
        JsonObject dataJson = extractFromJson(resultMessage);
        Assignments assignments = gson.fromJson(dataJson, Assignments.class);
        showLoadingStateInActionBar(false);
        BusProvider.getInstance().post(assignments);
    }
}
