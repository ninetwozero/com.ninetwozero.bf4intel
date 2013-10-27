package com.ninetwozero.bf4intel.datatypes;

import android.content.Intent;
import android.os.Bundle;

import com.ninetwozero.bf4intel.factories.FragmentFactory;

import java.util.List;

public class ListRow {
    private ListRowType mType;
    private String mTitle;

    private Intent mIntent;
    private FragmentFactory.Type mFragmentType;

    private Bundle mStringMappings;
    private Bundle mDrawableMappings;

    private List<ListRow> mChildren;

    protected ListRow(final Builder builder) {
        mType = builder.type;
        mTitle = builder.title;

        mIntent = builder.intent;
        mFragmentType = builder.fragmentType;

        mStringMappings = builder.stringMappings;
        mDrawableMappings = builder.drawableMappings;

        mChildren = builder.children;
    }

    public String getTitle() {
        return mTitle;
    }

    public ListRowType getType() {
        return mType;
    }

    public int getLayout() {
        return ListRowType.getResource(mType);
    }

    public Intent getIntent() {
        return mIntent;
    }

    public FragmentFactory.Type getFragmentType() {
        return mFragmentType;
    }

    public Bundle getStringMappings() {
        return mStringMappings;
    }

    public Bundle getDrawableMappings() {
        return mDrawableMappings;
    }

    public boolean hasIntent() {
        return mIntent != null;
    }

    public boolean hasFragmentType() {
        return mFragmentType != null;
    }

    public int getChildCount() {
        return mChildren == null ? 0 : mChildren.size();
    }

    public ListRow getChild(final int position) {
        return mChildren.get(position);
    }

    public List<ListRow> getChildren() {
        return mChildren;
    }

    public static class Builder {
        // Required
        private final ListRowType type;

        // Optional
        private String title;
        private Intent intent;
        private FragmentFactory.Type fragmentType;
        private Bundle stringMappings;
        private Bundle drawableMappings;
        private List<ListRow> children;

        public Builder(final ListRowType t) {
            type = t;
        }

        public Builder title(final String t) {
            title = t;
            return this;
        }

        public Builder intent(final Intent i) {
            intent = i;
            return this;
        }

        public Builder fragmentType(final FragmentFactory.Type ft) {
            fragmentType = ft;
            return this;
        }

        public Builder stringMappings(final Bundle b) {
            stringMappings = b;
            return this;
        }

        public Builder drawableMappings(final Bundle b) {
            drawableMappings = b;
            return this;
        }

        public Builder children(final List<ListRow> c) {
            children = c;
            return this;
        }

        public ListRow build() {
            return new ListRow(this);
        }
    }
}
