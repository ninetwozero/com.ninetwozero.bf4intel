package com.ninetwozero.bf4intel.assignments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.ninetwozero.bf4intel.R;

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
        imgAssignment.setImageResource(assignment.getImage());
        ImageView imgPrerequirement = (ImageView) view.findViewById(R.id.img_assignment_pre_requirement);

        if (assignment.equals(AssignmentPrerequirement.RANK)) {
            imgPrerequirement.setImageResource(R.drawable.rank_prerequirement);
        } else {
            imgPrerequirement.setImageResource(R.drawable.group_prerequirement);
        }

        if (assignment.isTracking()) {
            imgPrerequirement.setVisibility(View.VISIBLE);
        } else {
            imgPrerequirement.setVisibility(View.INVISIBLE);
        }
        return view;
    }
}
