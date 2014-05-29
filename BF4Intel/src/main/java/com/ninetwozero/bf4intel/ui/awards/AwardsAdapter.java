package com.ninetwozero.bf4intel.ui.awards;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.adapter.BaseIntelAdapter;
import com.ninetwozero.bf4intel.json.awards.Award;

import java.util.ArrayList;
import java.util.List;

public class AwardsAdapter extends BaseIntelAdapter<Award> implements Filterable {
    private AwardFilter filter;
    private List<Award> listWithAllItems;

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

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new AwardFilter();
            listWithAllItems = itemsList;
        }
        return filter;
    }

    private class AwardFilter extends Filter {
        @Override
        protected FilterResults performFiltering(final CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint == null || constraint.length() == 0) {
                results.values = itemsList;
                results.count = itemsList.size();
            } else {
                List<Award> filteredAwards = new ArrayList<Award>();
                for (Award award : listWithAllItems) {
                    if (award.getCategory().equals(constraint)) {
                        filteredAwards.add(award);
                    }
                }
                results.values = filteredAwards;
                results.count = filteredAwards.size();
            }
            return results;
        }

        @Override
        protected void publishResults(final CharSequence constraint, final FilterResults results) {
            itemsList = (List<Award>) results.values;
            if (results.count == 0) {
                notifyDataSetInvalidated();
            } else {
                notifyDataSetChanged();
            }
        }
    }
}
