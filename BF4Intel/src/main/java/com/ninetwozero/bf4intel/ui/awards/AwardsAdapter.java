package com.ninetwozero.bf4intel.ui.awards;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.adapter.BaseFilterableIntelAdapter;
import com.ninetwozero.bf4intel.json.awards.Award;

import java.util.ArrayList;
import java.util.List;

public class AwardsAdapter extends BaseFilterableIntelAdapter<Award> {
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
        AwardHolder holder;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.item_award, parent, false);
            holder = getAwardHolder(view);

            view.setTag(holder);
        } else {
            holder = (AwardHolder) view.getTag();
        }

        setImage(holder.awardMedal, MedalImagesMap.get(award.getMedalCode()));
        holder.awardCompletion.setProgress(award.getMedal().getPresentProgress());

        if (award.getMedal().isTaken()) {
            holder.medalsCount.setText(String.format("x%d", award.getMedal().getTimesTaken()));
            holder.medalsCount.setVisibility(View.VISIBLE);
            holder.awardMedalContainer.setAlpha(1f);
        } else {
            holder.medalsCount.setVisibility(View.INVISIBLE);
            holder.awardMedalContainer.setAlpha(0.5f);
        }

        holder.awardRibbonContainer.setAlpha(award.getRibbon().isTaken() ? 1f : 0.5f);
        setImage(holder.awardRibbon, RibbonImagesMap.get(award.getRibbonCode()));
        holder.ribbonsCount.setText(String.format("x%d", award.getRibbon().getTimesTaken()));

        return view;
    }

    private AwardHolder getAwardHolder(View view) {
        AwardHolder holder = new AwardHolder();
        holder.awardMedal = (ImageView) view.findViewById(R.id.award_medal);
        holder.awardCompletion = (ProgressBar) view.findViewById(R.id.award_completion);
        holder.awardCompletion.setMax(50);
        holder.medalsCount = (TextView) view.findViewById(R.id.medals_count);
        holder.awardMedalContainer = (ViewGroup) view.findViewById(R.id.award_medal_container);
        holder.awardRibbonContainer = (ViewGroup) view.findViewById(R.id.award_ribbon_container);
        holder.awardRibbon = (ImageView) view.findViewById(R.id.award_ribbon);
        holder.ribbonsCount = (TextView) view.findViewById(R.id.ribbons_count);
        return holder;
    }

    @Override
    protected List<Award> filterItems(final CharSequence constraint) {
        List<Award> filteredAwards = new ArrayList<Award>();
        for (Award award : listWithAllItems) {
            if (award.getCategory().equals(constraint)) {
                filteredAwards.add(award);
            }
        }
        return filteredAwards;
    }

    private static class AwardHolder {

        public ImageView awardMedal;
        public ProgressBar awardCompletion;
        public TextView medalsCount;
        public ViewGroup awardMedalContainer;
        public ViewGroup awardRibbonContainer;
        public ImageView awardRibbon;
        public TextView ribbonsCount;
    }
}
