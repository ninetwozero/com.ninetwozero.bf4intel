package com.ninetwozero.bf4intel.ui.stats.weapons;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseFragment;
import com.ninetwozero.bf4intel.json.stats.weapons.Weapon;
import com.ninetwozero.bf4intel.resources.maps.weapons.WeaponImageMap;
import com.ninetwozero.bf4intel.resources.maps.weapons.WeaponStringMap;
import com.ninetwozero.bf4intel.utils.DateTimeUtils;
import com.ninetwozero.bf4intel.utils.NumberFormatter;

import java.util.Locale;

public class WeaponDetailStatsFragment extends BaseFragment {
    public static final String INTENT_WEAPON = "weapon";
    public static final String TAG = "WeaponDetailStatsFragment";

    private Weapon weapon;

    public static WeaponDetailStatsFragment newInstance(final Bundle data) {
        final WeaponDetailStatsFragment fragment = new WeaponDetailStatsFragment();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        final View view = inflater.inflate(R.layout.fragment_weapon_details, container, false);
        initialize(view);
        return view;
    }

    private void initialize(View view) {
        setupData(getArguments());
        setupActionBar();
        populateViews(view);
    }

    private void setupData(final Bundle data) {
        weapon = (Weapon) data.getSerializable(INTENT_WEAPON);
    }

    private void setupActionBar() {
        if (!isSw720dp()) {
            final String key = weapon.getUniqueName();
            getActivity().getActionBar().setTitle(
                String.format(
                    Locale.getDefault(),
                    "Viewing %s", //TODO
                    getString(WeaponStringMap.get(key))
                )
            );
        }
    }

    private void populateViews(final View view) {
        populateOverviewBox(view);
        populateInformation(view);
        populateStatistics(view);
    }

    private void populateOverviewBox(View view) {
        setText(view, R.id.item_name, WeaponStringMap.get(weapon.getUniqueName()));
        setImage(view, R.id.item_image, WeaponImageMap.get(weapon.getUniqueName()));
        setText(view, R.id.service_star_count, String.valueOf(weapon.getServiceStarsCount()));
        setProgress(view, R.id.item_progress, weapon.getServiceStarsProgress());
        setText(view, R.id.item_progress_value, weapon.getServiceStarsProgress() + "%");
    }

    /*
        TODO: WIP get information somewhere
        http://jsonviewer.stack.hu/#http://eaassets-a.akamaihd.net/bl-cdn/cdnprefix/48b53386750f67b51cd145edda57c2696174953b/public/gamedatawarsaw/warsaw.items.js
    */
    private void populateInformation(View view) {
        setText(view, R.id.value_damage, "DAMAGE");
        setText(view, R.id.value_weapon_accuracy, "ACCURACY");
        setText(view, R.id.value_hip_fire, "HIP FIRE");
        setText(view, R.id.value_range, "RANGE");
    }

    private void populateStatistics(View view) {
        setText(view, R.id.value_kills, String.valueOf(weapon.getKills()));
        setText(view, R.id.value_headshots, String.valueOf(weapon.getHeadshotCount()));
        setText(view, R.id.value_shots_fired, String.valueOf(weapon.getShotsFired()));
        setText(view, R.id.value_accuracy, NumberFormatter.format(weapon.getAccuracy()) + "%");
        setText(view, R.id.value_time_equipped, DateTimeUtils.toLiteral(weapon.getTimeEquipped()));
        setText(view, R.id.value_kills_per_shot, calculateKillsPerShot());
    }

    private String calculateKillsPerShot() {
        final double shotsFired = weapon.getShotsFired();
        if (shotsFired == 0) {
            return "0";
        }
        return NumberFormatter.format(weapon.getKills() / shotsFired);
    }
}
