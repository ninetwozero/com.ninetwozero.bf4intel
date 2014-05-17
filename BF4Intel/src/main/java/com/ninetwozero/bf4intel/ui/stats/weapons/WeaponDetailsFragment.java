package com.ninetwozero.bf4intel.ui.stats.weapons;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseDialogFragment;
import com.ninetwozero.bf4intel.datatypes.WeaponInfo;
import com.ninetwozero.bf4intel.json.stats.weapons.Weapon;
import com.ninetwozero.bf4intel.resources.maps.WeaponInfoMap;
import com.ninetwozero.bf4intel.resources.maps.weapons.GadgetStringMap;
import com.ninetwozero.bf4intel.resources.maps.weapons.WeaponImageMap;
import com.ninetwozero.bf4intel.resources.maps.weapons.WeaponStringMap;
import com.ninetwozero.bf4intel.utils.DateTimeUtils;
import com.ninetwozero.bf4intel.utils.NumberFormatter;

public class WeaponDetailsFragment extends BaseDialogFragment {
    public static final String INTENT_WEAPON = "weapon";
    public static final String TAG = WeaponDetailsFragment.class.getSimpleName();

    private Weapon weapon;
    private final WeaponInfoMap weaponInfoMap = new WeaponInfoMap();
    private WeaponCategory weaponCategory;

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
        setupTitle();
        populateViews(view);
    }

    private void setupData(final Bundle data) {
        weapon = (Weapon) data.getSerializable(INTENT_WEAPON);
        weaponCategory = fetchCategoryForWeapon();
    }

    private WeaponCategory fetchCategoryForWeapon() {
        return WeaponCategory.from(weapon.getCategorySID());
    }

    private void setupTitle() {
        final String key = weapon.getUniqueName();
        setTitle(getString(WeaponStringMap.get(key)));
    }

    private void populateViews(final View view) {
        populateOverviewBox(view);
        populateDescriptionBox(view);
        populateInformation(view);
        populateStatistics(view);
    }

    private void populateDescriptionBox(View view) {
        final String nameKey = weapon.getUniqueName();
        final boolean hasDescription = nameKey.contains("_INAME_");
        final String descriptionKey = nameKey.replace("_INAME_", "_IDESC_");
        if (hasDescription) {
            setText(view, R.id.value_description, GadgetStringMap.get(descriptionKey));
        }
        setVisibility(view, R.id.wrap_description_box, hasDescription ? View.VISIBLE : View.GONE);
    }

    private void populateOverviewBox(View view) {
        setImage(view, R.id.item_image, WeaponImageMap.get(weapon.getUniqueName()));
        setText(view, R.id.service_star_count, String.valueOf(weapon.getServiceStarsCount()));
        setProgress(view, R.id.item_progress, weapon.getServiceStarsProgress());
        setText(view, R.id.item_progress_value, weapon.getServiceStarsProgress() + "%");
    }

    private void populateInformation(View view) {
        final WeaponInfo weaponInfo = weaponInfoMap.get(weapon.getUniqueName());
        if (weaponInfo.getDamage() == WeaponInfo.NONE || weaponInfo.getDamage() == 0) {
            setVisibility(view, R.id.wrap_information_box, View.GONE);
            return;
        }

        setText(view, R.id.value_damage, String.valueOf(weaponInfo.getDamage()));
        setText(view, R.id.value_weapon_accuracy, String.valueOf(weaponInfo.getAccuracy()));
        setText(view, R.id.value_hip_fire, String.valueOf(weaponInfo.getHipFire()));
        setText(view, R.id.value_range, String.valueOf(weaponInfo.getRange()));

        if (weaponInfo.getRateOfFire() > 0) {
            setText(view, R.id.value_rate_of_fire, String.valueOf(weaponInfo.getRateOfFire()));
            setProgress(view, R.id.progress_rate_of_fire, weaponInfo.getRateOfFire(), 1000);
            setVisibility(view, R.id.wrap_rate_of_fire, View.VISIBLE);
        } else {
            setVisibility(view, R.id.wrap_rate_of_fire, View.GONE);
        }

        setProgress(view, R.id.progress_damage, weaponInfo.getDamage());
        setProgress(view, R.id.progress_weapon_accuracy, weaponInfo.getAccuracy());
        setProgress(view, R.id.progress_hip_fire, weaponInfo.getHipFire());
        setProgress(view, R.id.progress_range, weaponInfo.getRange());

        setAlpha(view, R.id.fire_mode_single, weaponInfo.isSingleFire() ? ALPHA_ENABLED : ALPHA_DISABLED);
        setAlpha(view, R.id.fire_mode_burst, weaponInfo.isBurstFire() ? ALPHA_ENABLED : ALPHA_DISABLED);
        setAlpha(view, R.id.fire_mode_auto, weaponInfo.isAutoFire() ? ALPHA_ENABLED : ALPHA_DISABLED);
    }

    private void populateStatistics(View view) {
        setText(view, R.id.value_kills, String.valueOf(weapon.getKills()));
        setText(view, R.id.value_headshots, String.valueOf(weapon.getHeadshotCount()));
        setText(view, R.id.value_shots_fired, String.valueOf(weapon.getShotsFired()));
        setText(view, R.id.value_accuracy, NumberFormatter.format(weapon.getAccuracy()*100) + "%");
        setText(view, R.id.value_time_equipped, DateTimeUtils.toLiteral(weapon.getTimeEquipped()));
        setText(view, R.id.value_kills_per_shot, calculateKillsPerShot());

        switch (weaponCategory) {
            case GADGETS:
                setVisibility(view, R.id.wrap_shots_fired, View.VISIBLE);
                setVisibility(view, R.id.wrap_accuracy, View.VISIBLE);
                break;
            case GRENADE:
                setVisibility(view, R.id.wrap_shots_fired, View.VISIBLE);
                setVisibility(view, R.id.wrap_accuracy, View.VISIBLE);
                break;
            case ASSAULT_RIFLE:
            case CARBINE:
            case SNIPER:
            case SHOTGUN:
            case HANDGUN:
            case DMR:
            case LMG:
                setVisibility(view, R.id.wrap_headshots, View.VISIBLE);
                setVisibility(view, R.id.wrap_shots_fired, View.VISIBLE);
                setVisibility(view, R.id.wrap_accuracy, View.VISIBLE);
                break;

            default:
                break;

        }
    }

    private String calculateKillsPerShot() {
        final double shotsFired = weapon.getShotsFired();
        if (shotsFired == 0) {
            return "0";
        }
        return NumberFormatter.format(weapon.getKills() / shotsFired);
    }
}
