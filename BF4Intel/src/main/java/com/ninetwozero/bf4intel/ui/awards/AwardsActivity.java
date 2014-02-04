package com.ninetwozero.bf4intel.ui.awards;

import android.os.Bundle;
import android.support.v4.content.Loader;

import com.google.gson.JsonObject;
import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseLoadingIntelActivity;
import com.ninetwozero.bf4intel.factories.UrlFactory;
import com.ninetwozero.bf4intel.json.awards.Awards;
import com.ninetwozero.bf4intel.network.IntelLoader;
import com.ninetwozero.bf4intel.network.SimpleGetRequest;
import com.ninetwozero.bf4intel.utils.BusProvider;
import com.ninetwozero.bf4intel.utils.Result;

public class AwardsActivity extends BaseLoadingIntelActivity {

    private static final int ID_LOADER = 1100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignments);

        AwardsFragment awardsFragment = new AwardsFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.container_assignments, awardsFragment).commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSupportLoaderManager().initLoader(ID_LOADER, null, this);
    }

    @Override
    public Loader<Result> onCreateLoader(int i, Bundle bundle) {
        return new IntelLoader(getApplicationContext(), new SimpleGetRequest(UrlFactory.buildAwardsURL(200661244, 1)));
    }

    @Override
    protected void onLoadSuccess(Loader<Result> resultLoader, String resultMessage) {
        if (resultLoader.getId() == ID_LOADER) {
            processResult(resultMessage);
        }
    }

    private void processResult(String resultMessage) {
        JsonObject dataJson = extractFromJson(resultMessage);
        Awards awards = gson.fromJson(dataJson, Awards.class);
        showLoadingStateInActionBar(false);
        BusProvider.getInstance().post(awards);
    }
}
