package com.ninetwozero.bf4intel.ui.unlocks;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseLoadingFragment;
import com.ninetwozero.bf4intel.json.unlocks.UnlockContainer;
import com.ninetwozero.bf4intel.json.unlocks.UnlockCriteria;
import com.ninetwozero.bf4intel.resources.maps.UnlockCriteriaStringMap;
import com.ninetwozero.bf4intel.resources.maps.assignments.AssignmentStringMap;
import com.ninetwozero.bf4intel.utils.NumberFormatter;

import java.util.Locale;

public abstract class BaseUnlockFragment extends BaseLoadingFragment {

    protected GridView gridView;
    protected TextView emptyTextView;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup parent, final Bundle state) {
        super.onCreateView(inflater, parent, state);
        return layoutInflater.inflate(R.layout.fragment_unlocks, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialize(view);
    }

    @Override
    public void onResume() {
        showLoadingState(false);
        super.onResume();
    }

    @Override
    protected void startLoadingData() {

    }

    protected String resolveCriteriaLabel(final UnlockCriteria criteria) {
        if (criteria.isScoreCriteria()) {
            final int resource = UnlockCriteriaStringMap.get(criteria.getLabel());
            final int scoreLeft = criteria.getTargetValue()-criteria.getCurrentValue();
            if (criteria.isCompleted()) {
                return getString(R.string.label_completed);
            } else {
                return String.format(getString(resource), NumberFormatter.format(scoreLeft));
            }
        } else {
            final String label = criteria.getLabel().toLowerCase(Locale.getDefault());
            final String labelPrefix = label.substring(0, label.lastIndexOf("_"));
            if (labelPrefix.contains("as") || labelPrefix.contains("xp23")) {
                return String.format(
                    getString(R.string.unlock_complete_as),
                    getString(AssignmentStringMap.get(criteria.getAward().getName()))
                );
            }
        }
        return criteria.getLabel();
    }

    private void initialize(final View view) {
        setupListView(view);
    }

    private void setupListView(final View view) {
        emptyTextView = (TextView) view.findViewById(android.R.id.empty);
        emptyTextView.setText(R.string.msg_no_unlocks);

        gridView = (GridView) view.findViewById(android.R.id.list);
        gridView.setChoiceMode(AbsListView.CHOICE_MODE_NONE);
        gridView.setEmptyView(emptyTextView);
        gridView.setOnItemClickListener(
            new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    final BaseUnlockAdapter adapter = (BaseUnlockAdapter) parent.getAdapter();
                    final UnlockCriteria criteria = ((UnlockContainer) adapter.getItem(position)).getCriteria();
                    showToast(resolveCriteriaLabel(criteria));
                }
            }
        );
    }
}
