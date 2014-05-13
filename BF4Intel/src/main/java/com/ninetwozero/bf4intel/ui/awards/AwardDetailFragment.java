package com.ninetwozero.bf4intel.ui.awards;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseDialogFragment;
import com.ninetwozero.bf4intel.json.awards.Award;
import com.ninetwozero.bf4intel.resources.maps.awards.AwardStringMap;

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
        populateRibbonViews(view);
    }

    private void populateMedalViews(final View view) {
        setImage(view, R.id.award_medal, MedalImagesMap.get(award.getMedalCode()));
        setProgress(view, R.id.award_completion, award.getMedal().getPresentProgress(), 50);
        setText(view, R.id.medal_requirement, AwardStringMap.get(award.getMedal().getMedalAward().getDescriptionId()));

        if (award.getMedal().isTaken()) {
            final TextView medalsCount = (TextView) view.findViewById(R.id.medals_count);
            medalsCount.setText(String.format("x%d", award.getMedal().getTimesTaken()));
            medalsCount.setVisibility(View.VISIBLE);
            setAlpha(view, R.id.award_medal_container, 1f);
        } else {
            setVisibility(view, R.id.medals_count, View.INVISIBLE);
            setAlpha(view, R.id.award_medal_container, 0.5f);
        }
    }

    private void populateRibbonViews(final View view) {
        setAlpha(view, R.id.award_ribbon_container, award.getRibbon().isTaken() ? 1f : 0.5f);
        setImage(view, R.id.award_ribbon, RibbonImagesMap.get(award.getRibbonCode()));
        setText(view, R.id.ribbons_count, String.format("x%d", award.getRibbon().getTimesTaken()));
    }
}
