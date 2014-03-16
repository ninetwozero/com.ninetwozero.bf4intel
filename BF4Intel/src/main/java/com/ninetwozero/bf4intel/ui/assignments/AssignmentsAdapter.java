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
import com.ninetwozero.bf4intel.json.assignments.AssignmentPrerequisite;
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

        showAssignmentImage(view, award, assignment.isCompleted());
        if (award.hasExpansionPack()) {
            showExpansionPackIcon(view, award);
        } else {
            setVisibility(view, R.id.expansion_icon, View.INVISIBLE);
        }

        if (assignment.isTracking()) {
            populateViewForTracking(view, assignment);
        } else {
            setVisibility(view, R.id.img_assignment_prerequisite, View.VISIBLE);
        }

        return view;
    }

    private void showAssignmentImage(final View view, final AssignmentAward award, final boolean completed) {
        final ImageView imageView = (ImageView) view.findViewById(R.id.img_assignment);
        imageView.setImageResource(AssignmentImageMap.get(award.getUniqueName()));
        imageView.setAlpha(completed ? 1f : 0.5f);
    }

    private void showExpansionPackIcon(final View view, final AssignmentAward award) {
        final ImageView expansionIcon = (ImageView) view.findViewById(R.id.expansion_icon);
        expansionIcon.setImageResource(ExpansionIconsImageMap.get(award.getExpansionPack()));
        expansionIcon.setVisibility(View.VISIBLE);
    }

    private void populateViewForTracking(final View view, final Assignment assignment) {
        final ImageView imagePrerequisite = (ImageView) view.findViewById(R.id.img_assignment_prerequisite);
        imagePrerequisite.setImageResource(fetchPrerequisiteImageResource(assignment.getDependencyGroup()));
        imagePrerequisite.setVisibility(View.INVISIBLE);

        final ProgressBar completionProgress = (ProgressBar) view.findViewById(R.id.assignment_completion);
        completionProgress.setProgress(assignment.getCompletion());
        completionProgress.setMax(100);
        completionProgress.setVisibility(View.VISIBLE);
    }

    private int fetchPrerequisiteImageResource(final String group) {
        if (group.equalsIgnoreCase(AssignmentPrerequisite.RANK.toString())) {
            return R.drawable.rank_prerequisite;
        } else if (group.equalsIgnoreCase(AssignmentPrerequisite.MISSION.toString())) {
            return R.drawable.group_prerequisite;
        } else {
            return R.drawable.empty;
        }
    }
}
