package com.ninetwozero.bf4intel.ui.stats.weapons;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseFragment;
import com.ninetwozero.bf4intel.datatypes.WeaponInfo;
import com.ninetwozero.bf4intel.json.stats.weapons.Weapon;
import com.ninetwozero.bf4intel.resources.maps.WeaponInfoStringMap;
import com.ninetwozero.bf4intel.resources.maps.weapons.WeaponImageMap;
import com.ninetwozero.bf4intel.resources.maps.weapons.WeaponStringMap;
import com.ninetwozero.bf4intel.utils.DateTimeUtils;
import com.ninetwozero.bf4intel.utils.NumberFormatter;

import java.util.Locale;

public class WeaponDetailsFragment extends BaseFragment {
    public static final String INTENT_WEAPON = "weapon";
    public static final String TAG = "WeaponDetailsFragment";

    private Weapon weapon;
    private final WeaponInfoStringMap weaponInfoStringMap = new WeaponInfoStringMap();

    public static WeaponDetailsFragment newInstance(final Bundle data) {
        final WeaponDetailsFragment fragment = new WeaponDetailsFragment();
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
                    "Viewing %s",
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

    private void populateInformation(View view) {
        final WeaponInfo weaponInfo = weaponInfoStringMap.get(weapon.getUniqueName());
        if (weaponInfo.getDamage() == WeaponInfo.NONE) {
            view.findViewById(R.id.wrap_information_box).setVisibility(View.GONE);
            return;
        }

        setText(view, R.id.value_damage, String.valueOf(weaponInfo.getDamage()));
        setText(view, R.id.value_weapon_accuracy, String.valueOf(weaponInfo.getAccuracy()));
        setText(view, R.id.value_hip_fire, String.valueOf(weaponInfo.getHipFire()));
        setText(view, R.id.value_range, String.valueOf(weaponInfo.getRange()));

        setProgress(view, R.id.progress_damage, weaponInfo.getDamage());
        setProgress(view, R.id.progress_weapon_accuracy, weaponInfo.getAccuracy());
        setProgress(view, R.id.progress_hip_fire, weaponInfo.getHipFire());
        setProgress(view, R.id.progress_range, weaponInfo.getRange());

        ((TextView) view.findViewById(R.id.fire_mode_single)).setTypeface(
            weaponInfo.isSingleFire() ? Typeface.DEFAULT_BOLD : Typeface.DEFAULT
        );
        ((TextView) view.findViewById(R.id.fire_mode_burst)).setTypeface(
            weaponInfo.isBurstFire() ? Typeface.DEFAULT_BOLD : Typeface.DEFAULT
        );
        ((TextView) view.findViewById(R.id.fire_mode_auto)).setTypeface(
            weaponInfo.isAutoFire() ? Typeface.DEFAULT_BOLD : Typeface.DEFAULT
        );
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
