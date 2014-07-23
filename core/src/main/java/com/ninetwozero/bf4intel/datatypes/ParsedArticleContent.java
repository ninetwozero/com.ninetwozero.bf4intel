package com.ninetwozero.bf4intel.datatypes;

import java.util.List;

public class ParsedArticleContent {
    private final String text;
    private final List<Link> links;

    public ParsedArticleContent(String text, List<Link> links) {
        this.text = text;
        this.links = links;
    }


    public String getText() {
        return text;
    }

    public List<Link> getLinks() {
        return links;
    }
}
