package com.ninetwozero.bf4intel.datatypes;

import android.content.Intent;
import android.os.Bundle;

import com.ninetwozero.bf4intel.factories.FragmentFactory;

import java.util.List;

public class ListRow {
    private ListRowType type;
    private String title;

    private Intent intent;
    private FragmentFactory.Type fragmentType;

    private Bundle stringMappings;
    private Bundle drawableMappings;

    private List<ListRow> children;

    private Bundle data;

    protected ListRow(final Builder builder) {
        type = builder.type;
        title = builder.title;

        intent = builder.intent;
        fragmentType = builder.fragmentType;

        stringMappings = builder.stringMappings;
        drawableMappings = builder.drawableMappings;

        children = builder.children;

        data = builder.data;
    }

    public String getTitle() {
        return title;
    }

    public ListRowType getType() {
        return type;
    }

    public int getLayout() {
        return ListRowType.getResource(type);
    }

    public Intent getIntent() {
        return intent;
    }

    public FragmentFactory.Type getFragmentType() {
        return fragmentType;
    }

    public Bundle getStringMappings() {
        return stringMappings;
    }

    public Bundle getDrawableMappings() {
        return drawableMappings;
    }

    public boolean hasIntent() {
        return intent != null;
    }

    public boolean hasFragmentType() {
        return fragmentType != null;
    }

    public Bundle getData() {
        return data;
    }

    public int getChildCount() {
        return children == null ? 0 : children.size();
    }

    public ListRow getChild(final int position) {
        return children.get(position);
    }

    public List<ListRow> getChildren() {
        return children;
    }

    public boolean hasChildren() {
        return children == null? false : children.size() > 0;
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
        private Bundle data;
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

        public Builder data(final Bundle b) {
            data = b;
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
