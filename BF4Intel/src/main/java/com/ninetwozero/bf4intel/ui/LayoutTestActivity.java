package com.ninetwozero.bf4intel.ui;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.adapter.BaseIntelAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LayoutTestActivity extends FragmentActivity {
    private static final List<TestLayout> LAYOUTS = new ArrayList<TestLayout>() {
        {
            addAll(Arrays.asList(TestLayout.values()));
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_test);
        setupActionBarOptions();
    }

    private void setupActionBarOptions() {
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        actionBar.setListNavigationCallbacks(
            new BaseIntelAdapter<TestLayout>(this, LAYOUTS) {
                @Override
                public long getItemId(int position) {
                    return getItem(position).getLayout();
                }

                @Override
                public View getDropDownView(int position, View convertView, ViewGroup parent) {
                    if (convertView == null) {
                        convertView = layoutInflater.inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
                    }

                    final TextView textView = (TextView) convertView.findViewById(android.R.id.text1);
                    textView.setText(String.valueOf(getItem(position)));
                    textView.setTextColor(getResources().getColor(R.color.white));
                    return convertView;
                }

                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    if (convertView == null) {
                        convertView = layoutInflater.inflate(android.R.layout.simple_spinner_item, parent, false);
                    }

                    final TextView textView = (TextView) convertView.findViewById(android.R.id.text1);
                    textView.setText(String.valueOf(getItem(position)));
                    textView.setTextColor(getResources().getColor(R.color.white));
                    return convertView;
                }
            },
            new ActionBar.OnNavigationListener() {
                @Override
                public boolean onNavigationItemSelected(int itemPosition, long itemId) {
                    setContentView((int)itemId);
                    return true;
                }
            }
        );
    }

    private enum TestLayout {
        NONE(R.layout.activity_layout_test);
        private int layout;
        TestLayout(final int layout) {
            this.layout = layout;
        }

        public int getLayout() {
            return this.layout;
        }
    }
}
