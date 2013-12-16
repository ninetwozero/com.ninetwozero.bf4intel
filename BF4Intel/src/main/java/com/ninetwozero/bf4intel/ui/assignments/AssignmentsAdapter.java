package com.ninetwozero.bf4intel.ui.assignments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.json.assignments.Assignment;
import com.ninetwozero.bf4intel.json.assignments.AssignmentPrerequirement;
import com.ninetwozero.bf4intel.resources.maps.assignments.AssignmentImageMap;
import com.ninetwozero.bf4intel.resources.maps.assignments.ExpansionIconsImageMap;

import java.util.List;

public class AssignmentsAdapter extends BaseAdapter {

    private final List<Assignment> assignments;
    private final Context context;

    public AssignmentsAdapter(List<Assignment> assignments, Context context) {
        this.assignments = assignments;
        this.context = context;
    }

    @Override
    public int getCount() {
        return assignments.size();
    }

    @Override
    public Object getItem(int position) {
        return assignments.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_assignment, parent, false);
        }
        Assignment assignment = assignments.get(position);

        ImageView imgAssignment = (ImageView) view.findViewById(R.id.img_assignment);
        imgAssignment.setImageResource(AssignmentImageMap.get(assignment.getAward().getUniqueName()));

        ImageView expansionIcon = (ImageView) view.findViewById(R.id.expansion_icon);
        if (assignment.getAward().getExpansionPack().length() != 0) {
            expansionIcon.setImageResource(ExpansionIconsImageMap.get(assignment.getAward().getExpansionPack()));
            expansionIcon.setVisibility(View.VISIBLE);
        } else {
            expansionIcon.setVisibility(View.INVISIBLE);
        }

        ImageView imgPrerequirement = (ImageView) view.findViewById(R.id.img_assignment_pre_requirement);
        imgPrerequirement.setImageResource(prerequirementImgResource(assignment.getDependencyGroup()));

        ProgressBar completionProgress = (ProgressBar) view.findViewById(R.id.assignment_completion);
        completionProgress.setProgress(assignment.getCompletion());

        if (assignment.isTracking()) {
            imgPrerequirement.setVisibility(View.INVISIBLE);
            completionProgress.setVisibility(View.VISIBLE);
        } else {
            imgPrerequirement.setVisibility(View.VISIBLE);
            completionProgress.setVisibility(View.INVISIBLE);
        }

        if(assignment.getCompletion() == 100) {
            view.setAlpha(1f);
        } else {
            view.setAlpha(0.5f);
        }
        return view;
    }

    private int prerequirementImgResource(String group) {
        if (group.equalsIgnoreCase(AssignmentPrerequirement.RANK.toString())) {
            return R.drawable.rank_prerequirement;
        } else if (group.equalsIgnoreCase(AssignmentPrerequirement.MISSION.toString())) {
            return R.drawable.group_prerequirement;
        } else {
            return R.drawable.empty;
        }
    }
}
