package com.ninetwozero.bf4intel.utils;

import com.ninetwozero.bf4intel.datatypes.Link;
import com.ninetwozero.bf4intel.datatypes.ParsedArticleContent;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class NewsUtils {
    public static final char NBSP = '\u00A0';
    public static final String BR = "<br />";

    private NewsUtils() {
    }

    public static ParsedArticleContent parseContent(final String content, final boolean includeLinksInText) {
        final StringBuilder stringBuilder = new StringBuilder();
        final List<Link> links = new ArrayList<Link>();
        final Document document = Jsoup.parse(content);

        int counter = 0;
        int elementSkipCount = 0;
        int lastPosition;
        String linkText;
        String url;

        int elementCounter = 0;
        Elements elements = document.select("p,ul");
        for (Element element : elements) {
            elementCounter++;

            final String text = element.text();
            final int currentBuilderSize = stringBuilder.length();

            if (text.length() == 0 || (text.length() == 1 && text.charAt(0) == NBSP)) {
                elementSkipCount++;
                continue;
            }

            if (!includeLinksInText && currentBuilderSize == 0 && !text.contains("iframe")) {
                stringBuilder.append(text);
                if (elementCounter < (elements.size() - elementSkipCount)) {
                    stringBuilder.append(BR).append(BR).append("...");
                }
                break;
            }

            if ("p".equals(element.nodeName()) && !text.contains("iframe")) {
                if (stringBuilder.length() > 0) {
                    stringBuilder.append(BR).append(BR);
                }
                stringBuilder.append(text);
            } else if ("ul".equals(element.nodeName())) {
                for (Element li : element.children()) {
                    stringBuilder.append(BR).append(BR).append("* ").append(li.text());
                }
            }
        }

        for (Element element : document.select("iframe,a")) {
            counter++;
            if ("iframe".equals(element.nodeName())) {
                url = element.attr("src");
                linkText = fetchDomainNameFromLink(url);
            } else {
                url = element.attr("href");
                linkText = element.text();
                lastPosition = stringBuilder.indexOf(linkText) + linkText.length();

                if (includeLinksInText && lastPosition < stringBuilder.length()) {
                    stringBuilder.insert(lastPosition, " [" + counter + "]");
                }
            }
            links.add(new Link(linkText, url));
        }
        return new ParsedArticleContent(stringBuilder.toString(), links);
    }

    public static String fetchDomainNameFromLink(final String link) {
        try {
            final URI uri = new URI(link);
            final String host = uri.getHost();
            return host.startsWith("www.") ? host.substring(4) : host;
        } catch (URISyntaxException ex) {
            return "...";
        }
    }
}
