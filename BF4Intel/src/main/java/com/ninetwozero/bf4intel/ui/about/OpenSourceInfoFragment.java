package com.ninetwozero.bf4intel.ui.about;

import android.os.Bundle;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.ui.about.datatypes.HeaderAboutRow;
import com.ninetwozero.bf4intel.ui.about.datatypes.SimpleAboutRow;

import java.util.ArrayList;
import java.util.List;

public class OpenSourceInfoFragment extends BaseAboutFragment {

    public static OpenSourceInfoFragment newInstance() {
        final OpenSourceInfoFragment fragment = new OpenSourceInfoFragment();
        fragment.setArguments(new Bundle());
        return fragment;
    }

    public OpenSourceInfoFragment() {

    }

    @Override
    protected List<HeaderAboutRow> getHeadersForList() {
        final List<HeaderAboutRow> items = new ArrayList<HeaderAboutRow>();
        items.add(new HeaderAboutRow(R.string.opensource_label_self, 1));
        items.add(new HeaderAboutRow(R.string.opensource_label_libraries, 9));
        return items;
    }

    @Override
    protected List<SimpleAboutRow> getItemsForList() {
        final List<SimpleAboutRow> items = new ArrayList<SimpleAboutRow>();
        items.add(
            new SimpleAboutRow(
                R.string.opensource_title_self,
                R.string.opensource_subtitle_self,
                "https://github.com/karllindmark/com.ninetwozero.bf4intel"
            )
        );

        items.add(
            new SimpleAboutRow(
                R.string.opensource_title_bugsense,
                R.string.opensource_subtitle_bugsense,
                "http://www.bugsense.com"
            )
        );
        items.add(
            new SimpleAboutRow(
                R.string.opensource_title_cupboard,
                R.string.opensource_subtitle_cupboard,
                "https://bitbucket.org/qbusict/cupboard/"
            )
        );
        items.add(
            new SimpleAboutRow(
                R.string.opensource_title_gson,
                R.string.opensource_subtitle_gson,
                "https://code.google.com/p/google-gson"
            )
        );
        items.add(
            new SimpleAboutRow(
                R.string.opensource_title_httprequest,
                R.string.opensource_subtitle_httprequest,
                "https://github.com/kevinsawicki/http-request"
            )
        );
        items.add(
            new SimpleAboutRow(
                R.string.opensource_title_jsoup,
                R.string.opensource_subtitle_jsoup,
                "http://jsoup.org/"
            )
        );
        items.add(
            new SimpleAboutRow(
                R.string.opensource_title_otto,
                R.string.opensource_subtitle_otto,
                "http://square.github.io/otto/"
            )
        );
        items.add(
            new SimpleAboutRow(
                R.string.opensource_title_pagerslidingtabstrip,
                R.string.opensource_subtitle_pagerslidingtabstrip,
                "https://github.com/astuetz/PagerSlidingTabStrip"
            )
        );
        items.add(
            new SimpleAboutRow(
                R.string.opensource_title_picasso,
                R.string.opensource_subtitle_picasso,
                "http://square.github.io/picasso/"
            )
        );
        items.add(
            new SimpleAboutRow(
                R.string.opensource_title_stickygridheaders,
                R.string.opensource_subtitle_stickygridheaders,
                "https://github.com/TonicArtos/StickyGridHeaders"
            )
        );
        return items;
    }
}
