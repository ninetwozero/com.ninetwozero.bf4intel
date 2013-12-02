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
        TextView medalsCount = (TextView) view.findViewById(R.id.medals_count);
        if(award.getMedal().getUnlocked() < 100) {
            progressBar.setMax(100);
            progressBar.setProgress(award.getMedal().getUnlocked());
            progressBar.setVisibility(View.VISIBLE);
            medalsCount.setVisibility(View.INVISIBLE);
        } else {
            progressBar.setVisibility(View.INVISIBLE);
            medalsCount.setText(String.format("x%d", award.getMedal().getTimesTaken()));
            medalsCount.setVisibility(View.VISIBLE);
        }

        ImageView ribbon = (ImageView) view.findViewById(R.id.award_ribbon);
        ribbon.setImageResource(RibbonImagesMap.ribbonsMap.get(award.getRibbonCode().toLowerCase()));

        TextView ribbonsCount = (TextView) view.findViewById(R.id.ribbons_count);
        ribbonsCount.setText(String.format("x%d", award.getRibbon().getTimesTaken()));
        return view;
    }
}
