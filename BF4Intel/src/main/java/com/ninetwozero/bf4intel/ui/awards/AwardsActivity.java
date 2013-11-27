package com.ninetwozero.bf4intel.ui.awards;

import android.os.Bundle;
import android.support.v4.content.Loader;
import android.util.Log;

import com.google.gson.JsonObject;
import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseIntelActivity;
import com.ninetwozero.bf4intel.json.awards.Awards;
import com.ninetwozero.bf4intel.network.ConnectionRequest;
import com.ninetwozero.bf4intel.network.IntelLoader;
import com.ninetwozero.bf4intel.ui.assignments.AssignmentsActivity;
import com.ninetwozero.bf4intel.utils.BusProvider;
import com.ninetwozero.bf4intel.utils.Result;

public class AwardsActivity extends BaseIntelActivity {
    //TODO temporary absolute url
    private static final String AWARDS_URL = "http://battlelog.battlefield.com/bf4/warsawawardspopulate/200661244/1/";
    private static final int ID_LOADER = 1100;
    private static final String TAG = "Awards";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignments);

        AwardsFragment awardsFragment = new AwardsFragment();
        //getSupportFragmentManager().beginTransaction().add(R.id.container_assignments, awardsFragment, TAG).commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSupportLoaderManager().initLoader(ID_LOADER, null, this);
    }

    @Override
    public Loader<Result> onCreateLoader(int i, Bundle bundle) {
        return new IntelLoader(getApplicationContext(), new ConnectionRequest(AWARDS_URL));
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
        Awards awards = gson.fromJson(dataJson, Awards.class);
        showLoadingStateInActionBar(true);
        BusProvider.getInstance().post(awards);
    }

    private void processError(String resultMessage) {
        Log.e(AssignmentsActivity.class.getSimpleName(), resultMessage);
    }
}
