package com.ninetwozero.bf4intel.ui.assignments;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.adapter.BaseFilterableIntelAdapter;
import com.ninetwozero.bf4intel.json.assignments.Assignment;
import com.ninetwozero.bf4intel.json.assignments.AssignmentAward;
import com.ninetwozero.bf4intel.json.assignments.AssignmentPrerequisite;
import com.ninetwozero.bf4intel.resources.maps.assignments.AssignmentImageMap;
import com.ninetwozero.bf4intel.resources.maps.assignments.ExpansionIconsImageMap;

import java.util.ArrayList;
import java.util.List;

public class AssignmentsAdapter extends BaseFilterableIntelAdapter<Assignment> {

    public AssignmentsAdapter(final Context context) {
        super(context);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final Assignment assignment = getItem(position);
        final AssignmentAward award = assignment.getAward();
        AssignmentHolder holder;

        if (view == null) {
            view = layoutInflater.inflate(R.layout.item_assignment, parent, false);
            holder = getAssignmentHolder(view);

            view.setTag(holder);
        } else {
            holder = (AssignmentHolder) view.getTag();
        }

        holder.assignmentImage.setAlpha(assignment.isCompleted() ? 1f : 0.5f);
        setImage(holder.assignmentImage, AssignmentImageMap.get(award.getUniqueName()));

        if (award.hasExpansionPack()) {
            holder.expansionIcon.setVisibility(View.VISIBLE);
            setImage(holder.expansionIcon, ExpansionIconsImageMap.get(award.getExpansionPack()));
        } else {
            holder.expansionIcon.setVisibility(View.INVISIBLE);
        }

        setImage(holder.assignmentPrerequisite, fetchPrerequisiteImageResource(assignment.getDependencyGroup()));
        holder.assignmentPrerequisite.setVisibility(View.INVISIBLE);

        holder.assignmentCompletion.setProgress(assignment.getCompletion());
        holder.assignmentCompletion.setVisibility(View.VISIBLE);

        if (!assignment.isTracking()) {
            holder.assignmentPrerequisite.setVisibility(View.VISIBLE);
        }

        return view;
    }

    private AssignmentHolder getAssignmentHolder(View view) {
        AssignmentHolder holder = new AssignmentHolder();
        holder.assignmentImage = (ImageView) view.findViewById(R.id.img_assignment);
        holder.expansionIcon = (ImageView) view.findViewById(R.id.expansion_icon);
        holder.assignmentPrerequisite = (ImageView) view.findViewById(R.id.img_assignment_prerequisite);
        holder.assignmentCompletion = (ProgressBar) view.findViewById(R.id.assignment_completion);
        holder.assignmentCompletion.setMax(100);
        return holder;
    }

    @Override
    protected List<Assignment> filterItems(CharSequence constraint) {
        List<Assignment> filteredAssignments = new ArrayList<Assignment>();
        for (Assignment assignment : listWithAllItems) {
            if (assignment.getGroup().equals(constraint)){
                filteredAssignments.add(assignment);
            }
        }
        return filteredAssignments;
    }

    private int fetchPrerequisiteImageResource(final String group) {
        if (group.equalsIgnoreCase(AssignmentPrerequisite.Type.RANK.toString())) {
            return R.drawable.ic_statistics_rank;
        } else if (group.equalsIgnoreCase(AssignmentPrerequisite.Type.MISSION.toString())) {
            return R.drawable.ic_statistics_award;
        } else {
            return R.drawable.empty;
        }
    }

    private static class AssignmentHolder {

        public ImageView assignmentImage;
        public ImageView expansionIcon;
        public ImageView assignmentPrerequisite;
        public ProgressBar assignmentCompletion;
    }
}
