package com.ninetwozero.bf4intel.menu;

import android.content.Intent;
import android.os.Bundle;

import com.ninetwozero.bf4intel.factories.FragmentFactory;
import com.ninetwozero.bf4intel.interfaces.ListRowElement;

public class NormalListRow implements ListRowElement {
    private ListRowType type;
    private String title;

    private Intent intent;
    private FragmentFactory.Type fragmentType;

    private Bundle stringMappings;
    private Bundle drawableMappings;

    private Bundle data;

    protected NormalListRow(final Builder builder) {
        type = builder.type;
        title = builder.title;

        intent = builder.intent;
        fragmentType = builder.fragmentType;

        stringMappings = builder.stringMappings;
        drawableMappings = builder.drawableMappings;

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

        public NormalListRow build() {
            return new NormalListRow(this);
        }
    }
}
