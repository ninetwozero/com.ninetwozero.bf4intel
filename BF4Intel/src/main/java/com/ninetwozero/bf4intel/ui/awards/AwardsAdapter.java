package com.ninetwozero.bf4intel.ui.awards;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.json.awards.Award;

import java.util.List;

public class AwardsAdapter extends BaseAdapter {

    private final List<Award> awards;
    private final Context context;

    public AwardsAdapter(List<Award> awards, Context context) {
        this.awards = awards;
        this.context = context;
    }

    @Override
    public int getCount() {
        return awards.size();
    }

    @Override
    public Object getItem(int position) {
        return awards.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if(view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_award, parent, false);
        }
        Award award = awards.get(position);

        ImageView medal = (ImageView) view.findViewById(R.id.award_medal);
        medal.setImageResource(MedalImagesMap.medalsMap.get(award.getMedalCode().toLowerCase()));

        ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.award_completion);
        progressBar.setMax(50);
        progressBar.setProgress(award.getMedal().getPresentProgress());
        TextView medalsCount = (TextView) view.findViewById(R.id.medals_count);
        if(award.getMedal().getTimesTaken() > 0) {
            medalsCount.setText(String.format("x%d", award.getMedal().getTimesTaken()));
            medalsCount.setVisibility(View.VISIBLE);
            view.findViewById(R.id.colour_overlay_medal).setVisibility(View.INVISIBLE);
        } else {
            medalsCount.setVisibility(View.INVISIBLE);
            view.findViewById(R.id.colour_overlay_medal).setVisibility(View.VISIBLE);
        }

        if(award.getRibbon().getTimesTaken() > 0) {
            view.findViewById(R.id.colour_overlay_ribbon).setVisibility(View.INVISIBLE);
        } else {
            view.findViewById(R.id.colour_overlay_ribbon).setVisibility(View.VISIBLE);
        }

        ImageView ribbon = (ImageView) view.findViewById(R.id.award_ribbon);
        ribbon.setImageResource(RibbonImagesMap.ribbonsMap.get(award.getRibbonCode().toLowerCase()));

        TextView ribbonsCount = (TextView) view.findViewById(R.id.ribbons_count);
        ribbonsCount.setText(String.format("x%d", award.getRibbon().getTimesTaken()));
        return view;
    }
}
