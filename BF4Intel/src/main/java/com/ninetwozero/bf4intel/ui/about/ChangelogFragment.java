package com.ninetwozero.bf4intel.ui.about;

import android.os.Bundle;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.ui.about.datatypes.HeaderAboutRow;
import com.ninetwozero.bf4intel.ui.about.datatypes.SimpleAboutRow;

import java.util.ArrayList;
import java.util.List;

public class ChangelogFragment extends BaseAboutFragment {

    public static ChangelogFragment newInstance() {
        final ChangelogFragment fragment = new ChangelogFragment();
        fragment.setArguments(new Bundle());
        return fragment;
    }

    public ChangelogFragment() {

    }

    @Override
    protected List<HeaderAboutRow> getHeadersForList() {
        final List<HeaderAboutRow> headers = new ArrayList<HeaderAboutRow>();
        headers.add(new HeaderAboutRow(R.string.changelog_label_recent_changes, 1));
        return headers;
    }

    @Override
    protected List<SimpleAboutRow> getItemsForList() {
        final List<SimpleAboutRow> items = new ArrayList<SimpleAboutRow>();
        items.add(new SimpleAboutRow(R.string.changelog_title_1_0, R.string.changelog_content_1_0));
        return items;
    }
}
