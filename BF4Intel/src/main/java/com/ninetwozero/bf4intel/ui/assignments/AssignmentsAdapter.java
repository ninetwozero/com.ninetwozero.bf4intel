package com.ninetwozero.bf4intel.ui.assignments;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.adapter.BaseIntelAdapter;
import com.ninetwozero.bf4intel.json.assignments.Assignment;
import com.ninetwozero.bf4intel.json.assignments.AssignmentAward;
import com.ninetwozero.bf4intel.json.assignments.AssignmentPrerequirement;
import com.ninetwozero.bf4intel.resources.maps.assignments.AssignmentImageMap;
import com.ninetwozero.bf4intel.resources.maps.assignments.ExpansionIconsImageMap;

import java.util.List;

public class AssignmentsAdapter extends BaseIntelAdapter<Assignment> {
    public AssignmentsAdapter(final Context context, final List<Assignment> assignments) {
        super(context, assignments);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final Assignment assignment = getItem(position);
        final AssignmentAward award = assignment.getAward();

        if (view == null) {
            view = layoutInflater.inflate(R.layout.item_assignment, parent, false);
        }

        setImage(view, R.id.img_assignment, AssignmentImageMap.get(assignment.getAward().getUniqueName()));

        if (award.hasExpansionPack()) {
            showExpansionPackIcon(view, award);
        } else {
            setVisibility(view, R.id.expansion_icon, View.INVISIBLE);
        }

        if (assignment.isTracking()) {
            populateViewForTracking(view, assignment);
        } else {
            setVisibility(view, R.id.img_assignment_pre_requirement, View.VISIBLE);
            setVisibility(view, R.id.assignment_completion, View.INVISIBLE);
        }

        view.setAlpha(assignment.isCompleted() ? 1f : 0.5f);
        return view;
    }

    private void showExpansionPackIcon(final View view, final AssignmentAward award) {
        final ImageView expansionIcon = (ImageView) view.findViewById(R.id.expansion_icon);
        expansionIcon.setImageResource(ExpansionIconsImageMap.get(award.getExpansionPack()));
        expansionIcon.setVisibility(View.VISIBLE);
    }

    private void populateViewForTracking(final View view, final Assignment assignment) {
        final ImageView imgPreRequirement = (ImageView) view.findViewById(R.id.img_assignment_pre_requirement);
        imgPreRequirement.setImageResource(fetchPreRequirementImgResource(assignment.getDependencyGroup()));
        imgPreRequirement.setVisibility(View.INVISIBLE);

        final ProgressBar completionProgress = (ProgressBar) view.findViewById(R.id.assignment_completion);
        completionProgress.setProgress(assignment.getCompletion());
        completionProgress.setMax(100);
        completionProgress.setVisibility(View.VISIBLE);
    }

    private int fetchPreRequirementImgResource(final String group) {
        if (group.equalsIgnoreCase(AssignmentPrerequirement.RANK.toString())) {
            return R.drawable.rank_prerequirement;
        } else if (group.equalsIgnoreCase(AssignmentPrerequirement.MISSION.toString())) {
            return R.drawable.group_prerequirement;
        } else {
            return R.drawable.empty;
        }
    }
}
