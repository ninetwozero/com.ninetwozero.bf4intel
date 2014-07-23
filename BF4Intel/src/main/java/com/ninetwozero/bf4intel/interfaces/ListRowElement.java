package com.ninetwozero.bf4intel.interfaces;

import android.content.Intent;

import com.ninetwozero.bf4intel.menu.ListRowType;

public interface ListRowElement {
    public String getTitle();
    public ListRowType getType();
    public int getLayout();
    public Intent getIntent();
    public boolean hasIntent();
    public boolean hasFragmentType();
}
