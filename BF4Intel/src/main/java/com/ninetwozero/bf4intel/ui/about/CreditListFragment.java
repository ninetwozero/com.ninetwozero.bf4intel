package com.ninetwozero.bf4intel.ui.about;

import android.os.Bundle;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.ui.about.datatypes.HeaderAboutRow;
import com.ninetwozero.bf4intel.ui.about.datatypes.SimpleAboutRow;

import java.util.ArrayList;
import java.util.List;

public class CreditListFragment extends BaseAboutFragment {

    public static CreditListFragment newInstance() {
        final CreditListFragment fragment = new CreditListFragment();
        fragment.setArguments(new Bundle());
        return fragment;
    }

    public CreditListFragment() {

    }

    @Override
    protected List<HeaderAboutRow> getHeadersForList() {
        final List<HeaderAboutRow> items = new ArrayList<HeaderAboutRow>();
        items.add(new HeaderAboutRow(R.string.credits_label_authors, 2));
        items.add(new HeaderAboutRow(R.string.credits_label_design, 1));
        return items;
    }

    @Override
    protected List<SimpleAboutRow> getItemsForList() {
        final List<SimpleAboutRow> items = new ArrayList<SimpleAboutRow>();
        items.add(
            new SimpleAboutRow(
                R.string.credits_name_karl,
                R.string.credits_desc_karl,
                "http://ninetwozero.com"
            )
        );
        items.add(
            new SimpleAboutRow(
                R.string.credits_name_peter,
                R.string.credits_desc_peter,
                "http://peterscorner.co.uk"
            )
        );
        items.add(
            new SimpleAboutRow(
                R.string.credits_name_taylorling,
                R.string.credits_desc_taylorling,
                "http://www.androiduiux.com"
            )
        );
        return items;
    }
}
