package com.ninetwozero.bf4intel.assignments;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.ninetwozero.bf4intel.R;

public class AssignmentsActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignments);
        AssignmentsFragment assignmentsFragment = new AssignmentsFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.container_assignments, assignmentsFragment).commit();
    }
}
