package com.ninetwozero.bf4intel.ui.stats.vehicles;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseDialogFragment;
import com.ninetwozero.bf4intel.json.stats.vehicles.GroupedVehicleStats;

public class VehicleDetailFragment extends BaseDialogFragment {
    public static final String INTENT_VEHICLES_GROUP = "vehicles_group";
    private GroupedVehicleStats groupedVehicles;


    public static VehicleDetailFragment newInstance(Bundle bundle) {
        final VehicleDetailFragment fragment = new VehicleDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle state) {
        super.onCreateView(inflater, parent, state);

        View view = inflater.inflate(R.layout.fragment_vehicle_details, parent, false);
        initialize(view);
        return view;
    }

    private void initialize(final View view) {
        setupData(getArguments());
        setupTitle();
        populateViews(view);
    }

    private void setupData(final Bundle data) {
        groupedVehicles = (GroupedVehicleStats) data.getSerializable(INTENT_VEHICLES_GROUP);
    }

    private void setupTitle() {
        final String title = groupedVehicles.getGroupName();
        setTitle(title);
    }

    private void populateViews(final View view) {

    }
}
