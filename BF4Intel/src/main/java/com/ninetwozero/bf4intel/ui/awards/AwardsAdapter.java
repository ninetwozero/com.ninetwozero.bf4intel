package com.ninetwozero.bf4intel.ui.awards;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.adapter.BaseIntelAdapter;
import com.ninetwozero.bf4intel.json.awards.Award;

public class AwardsAdapter extends BaseIntelAdapter<Award> {
    public AwardsAdapter(final Context context) {
        super(context);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Award award = getItem(position);

        if (view == null) {
            view = layoutInflater.inflate(R.layout.item_award, parent, false);
        }

        populateMedalViews(view, award);
        populateRibbonViews(view, award);

        return view;
    }

    private void populateMedalViews(final View view, final Award award) {
        setImage(view, R.id.award_medal, MedalImagesMap.get(award.getMedalCode()));
        setProgress(view, R.id.award_completion, award.getMedal().getPresentProgress(), 50);

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

    private void populateRibbonViews(final View view, final Award award) {
        setAlpha(view, R.id.award_ribbon_container, award.getRibbon().isTaken() ? 1f : 0.5f);
        setImage(view, R.id.award_ribbon, RibbonImagesMap.get(award.getRibbonCode()));
        setText(view, R.id.ribbons_count, String.format("x%d", award.getRibbon().getTimesTaken()));
    }
}
