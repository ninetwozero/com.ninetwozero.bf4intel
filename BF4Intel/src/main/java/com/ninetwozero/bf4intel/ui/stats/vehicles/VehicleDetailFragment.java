package com.ninetwozero.bf4intel.ui.stats.vehicles;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseDialogFragment;
import com.ninetwozero.bf4intel.json.stats.vehicles.GroupedVehicleStats;
import com.ninetwozero.bf4intel.json.stats.vehicles.VehicleStats;
import com.ninetwozero.bf4intel.resources.maps.vehicles.VehicleImageMap;
import com.ninetwozero.bf4intel.resources.maps.vehicles.VehicleStringMap;

import java.util.Collections;
import java.util.List;

public class VehicleDetailFragment extends BaseDialogFragment {
    public static final String INTENT_VEHICLES_GROUP = "vehicles_group";
    public static final String TAG = VehicleDetailFragment.class.getSimpleName();

    private GroupedVehicleStats groupedVehicles;
    private List<VehicleStats> vehicleStats;


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
        vehicleStats = groupedVehicles.getVehicleList();
        Collections.sort(vehicleStats);
    }

    private void setupTitle() {
        final String title = groupedVehicles.getGroupName();
        setTitle(title);
    }

    private void populateViews(final View view) {

        populateImageContainer(view, 0);
        populateTotalScore(view);
        populateListView(view);
    }

    private void populateImageContainer(final View view, final int index) {
        VehicleStats stats = vehicleStats.get(index);
        setImage(view, R.id.vehicle_image, VehicleImageMap.get(stats.getName()));
        setText(view, R.id.vehicle_name, VehicleStringMap.get(stats.getName()));
        setText(view, R.id.vehicle_kills, String.valueOf(stats.getKillsCount()));
    }

    private void populateTotalScore(final View view) {
        setText(view, R.id.vehicles_total_score_label, R.string.total_kills);
        setText(view, R.id.vehicles_score, String.valueOf(groupedVehicles.getKillCount()));
    }

    private void populateListView(final View view) {
        ListView listView = (ListView) view.findViewById(R.id.vehicles_list);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        BaseAdapter adapter = new VehicleDatailAdapter(getActivity(), vehicleStats);
        listView.setAdapter(adapter);
    }
}
