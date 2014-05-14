package com.ninetwozero.bf4intel.ui.awards;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseDialogFragment;
import com.ninetwozero.bf4intel.json.awards.Award;
import com.ninetwozero.bf4intel.resources.maps.assignments.AssignmentCriteriaStringMap;
import com.ninetwozero.bf4intel.resources.maps.assignments.ExpansionIconsImageMap;
import com.ninetwozero.bf4intel.resources.maps.awards.AwardStringMap;

import java.util.Locale;

public class AwardDetailFragment extends BaseDialogFragment {
    public static final String INTENT_AWARD = "award";
    public static final String TAG = "AwardDetailFragment";
    private Award award;

    public static AwardDetailFragment newInstance(final Bundle data) {
        final AwardDetailFragment fragment = new AwardDetailFragment();
        fragment.setArguments(data);
        return fragment;
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        final View view = inflater.inflate(R.layout.fragment_award_details, container, false);
        initialize(view);
        return view;
    }

    private void initialize(final View view) {
        setupData(getArguments());
        setupTitle();
        populateViews(view);
    }

    private void setupData(final Bundle data) {
        award = (Award) data.getSerializable(INTENT_AWARD);
    }

    private void setupTitle() {
        final String title = getString(AwardStringMap.get(award.getMedal().getMedalAward().getUniqueName()));
        setTitle(title);
    }

    private void populateViews(final View view) {
        populateMedalViews(view);
        populateRequiredExpansion(view);
        populateRibbonViews(view);
    }

    private void populateMedalViews(final View view) {
        setImage(view, R.id.award_medal, MedalImagesMap.get(award.getMedalCode()));
        setProgress(view, R.id.award_completion, award.getMedal().getPresentProgress(), 50);
        setText(view, R.id.medal_requirement, AwardStringMap.get(award.getMedal().getMedalAward().getDescriptionId()));
        setText(view, R.id.award_completion_text, formatXofY());

        if (award.getMedal().isTaken()) {
            final TextView medalsCount = (TextView) view.findViewById(R.id.medals_count);
            medalsCount.setText(String.format("x%d", award.getMedal().getTimesTaken()));
            medalsCount.setVisibility(View.VISIBLE);
            setAlpha(view, R.id.award_medal, 1f);
        } else {
            setVisibility(view, R.id.medals_count, View.INVISIBLE);
            setAlpha(view, R.id.award_medal, 0.5f);
        }
    }

    private void populateRequiredExpansion(final View view) {
        if(!award.getMedalCode().contains("xp")) {
            view.findViewById(R.id.expansion_award_container).setVisibility(View.GONE);
        } else {
            view.findViewById(R.id.expansion_award_container).setVisibility(View.VISIBLE);
            String expansionPrefix = award.getMedalCode().substring(0, 3);
            setTextWithDrawable(view, R.id.expansion_label,
                getExpansionLabel(expansionPrefix),
                ExpansionIconsImageMap.get(expansionPrefix)
                );
        }
    }

    private void populateRibbonViews(final View view) {
        setText(view, R.id.ribbon_title, AwardStringMap.get(award.getRibbon().getRibbonAward().getUniqueName()));
        setAlpha(view, R.id.award_ribbon_container, award.getRibbon().isTaken() ? 1f : 0.5f);
        setImage(view, R.id.award_ribbon, RibbonImagesMap.get(award.getRibbonCode()));
        setText(view, R.id.ribbon_requirement, AwardStringMap.get(award.getRibbon().getRibbonAward().getDescriptionId()));
        setText(view, R.id.ribbons_count, String.format("x%d", award.getRibbon().getTimesTaken()));
    }

    private String formatXofY() {
        return String.format(
            Locale.getDefault(),
            getString(R.string.generic_x_of_y),
            award.getMedal().getPresentProgress(),
            award.getMedal().getMaxProgress()
        );
    }

    private String getExpansionLabel(final String expansionPrefix) {
        String expansion;
        if(expansionPrefix.equals("xp0")) {
            expansion = getString(AssignmentCriteriaStringMap.get("WARSAW_ID_P_AWARD_XP0"));
        } else if (expansionPrefix.equals("xp1")) {
            expansion = getString(AssignmentCriteriaStringMap.get("WARSAW_ID_P_AWARD_XP1"));
        } else if (expansionPrefix.equals("xp2")) {
            expansion = getString(AssignmentCriteriaStringMap.get("WARSAW_ID_P_AWARD_XP2"));
        } else if (expansionPrefix.equals("xp3")) {
            expansion = getString(AssignmentCriteriaStringMap.get("WARSAW_ID_P_AWARD_XP3"));
        } else if (expansionPrefix.equals("xp4")) {
            expansion = getString(AssignmentCriteriaStringMap.get("WARSAW_ID_P_AWARD_XP4"));
        } else {
            expansion = getString(R.string.na);
        }
        return getString(R.string.award_available) + expansion;
    }
}
