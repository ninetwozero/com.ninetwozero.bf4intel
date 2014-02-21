
package com.ninetwozero.bf4intel.ui.about.datatypes;

public class SimpleAboutRow extends BaseAboutRow {
    private int subTitle;
    private String url;

    public SimpleAboutRow(final int title, final int subTitle) {
        super(title, Type.ITEM);
        this.subTitle = subTitle;
    }

    public SimpleAboutRow(final int title, final int subTitle, final String url) {
        super(title, Type.ITEM);
        this.subTitle = subTitle;
        this.url = url;
    }

    public boolean hasSubtitle() {
        return subTitle != 0;
    }

    public int getSubTitle() {
        return subTitle;
    }

    public boolean hasUrl() {
        return url != null;
    }

    public String getUrl() { return url; }
}
