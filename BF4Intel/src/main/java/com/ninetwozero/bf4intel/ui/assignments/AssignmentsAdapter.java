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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        imgAssignment.setImageResource(getAssignmentImg(assignment.getAward().getAssignmentKey().toLowerCase()));

        ImageView expansionIcon = (ImageView) view.findViewById(R.id.expansion_icon);
        if(assignment.getAward().getExpansionPack().length() != 0) {
            expansionIcon.setImageResource(expansionResources.get(assignment.getAward().getExpansionPack()));
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
            //TODO would like to be able to give overlay to whole view when assignment is not tracked (unlocked)
            //imgAssignment.setColorFilter(R.color.almostblack);
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

    private int getAssignmentImg(String assignmentKey) {
        return imageResources.containsKey(assignmentKey) ? imageResources.get(assignmentKey) : R.drawable.as_unknown;
    }

    private static final Map<String, Integer> imageResources = new HashMap<String, Integer>() {
        {
            put("as01a", R.drawable.as01a);
            put("as02", R.drawable.as02);
            put("as03", R.drawable.as03);
            put("as04", R.drawable.as04);
            put("as05", R.drawable.as05);
            put("as06", R.drawable.as06);
            put("as07", R.drawable.as07);
            put("as08", R.drawable.as08);
            put("as09", R.drawable.as09);
            put("as10", R.drawable.as10);
            put("as11", R.drawable.as11);
            put("as12", R.drawable.as12);
            put("as13", R.drawable.as13);
            put("as14", R.drawable.as14);
            put("as15", R.drawable.as15);
            put("as16", R.drawable.as16);
            put("as17", R.drawable.as17);
            put("as18", R.drawable.as18);
            put("as19", R.drawable.as19);
            put("as20", R.drawable.as20);
            put("as21", R.drawable.as21);
            put("as22", R.drawable.as22);
            put("as23", R.drawable.as23);
            put("as24", R.drawable.as24);
            put("as25", R.drawable.as25);
            put("as26", R.drawable.as26);
            put("as27", R.drawable.as27);
            put("as28", R.drawable.as28);
            put("as29", R.drawable.as29);
            put("as30", R.drawable.as30);
            put("as31", R.drawable.as31);
            put("as32", R.drawable.as32);
            put("as33", R.drawable.as33);
            put("as34", R.drawable.as34);
            put("as35", R.drawable.as35);
            put("as36", R.drawable.as36);
            put("as37", R.drawable.as37);
            put("as38", R.drawable.as38);
            put("as39", R.drawable.as39);
            put("as40", R.drawable.as40);
            put("as41", R.drawable.as41);
            put("as42", R.drawable.as42);
            put("as43", R.drawable.as43);
            put("as44", R.drawable.as44);
            put("as45", R.drawable.as45);
            /*Single player assignments*/
            put("sp_as01", R.drawable.sp_as01);
            put("sp_as02", R.drawable.sp_as02);
            put("sp_as03", R.drawable.sp_as03);
            put("sp_as04", R.drawable.sp_as04);
            put("sp_as05", R.drawable.sp_as05);
            put("sp_as06", R.drawable.sp_as06);
            /*Expansion pack 1 assignments*/
            put("xp1as01", R.drawable.xp1as01);
            put("xp1as02", R.drawable.xp1as02);
            put("xp1as03", R.drawable.xp1as03);
            put("xp1as04", R.drawable.xp1as04);
            put("xp1as05", R.drawable.xp1as05);
            put("xp1as06", R.drawable.xp1as06);
            put("xp1as07", R.drawable.xp1as07);
            put("xp1as08", R.drawable.xp1as08);
            put("xp1as09", R.drawable.xp1as09);
            put("xp1as10", R.drawable.xp1as10);
        }
    };

    private static final Map<String, Integer> expansionResources = new HashMap<String, Integer>() {
        {
            put("xp1", R.drawable.icon_xp1);
        }
    };
}
