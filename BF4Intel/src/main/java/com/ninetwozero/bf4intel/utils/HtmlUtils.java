package com.ninetwozero.bf4intel.utils;

import android.text.Html;

public class HtmlUtils {
    public static String trimContentForNews(final String content, final int paragraphCount) {
        final StringBuilder builder = new StringBuilder(content.replaceAll("<p>&nbsp;</p>", ""));

        removeImages(builder);
        removeUnwantedParagraphs(builder, paragraphCount);
        removeLastParagraphTags(builder);

        return Html.fromHtml(builder.toString()).toString();
    }

    private static void removeImages(final StringBuilder builder) {
        final int positionOfImage = builder.lastIndexOf("<img");
        if (positionOfImage != -1) {
            int endTagOffset = builder.substring(positionOfImage).indexOf("/>");
            int endTagPosition = endTagOffset + positionOfImage + 2;
            if (endTagPosition != -1) {
                builder.replace(
                    positionOfImage,
                    endTagPosition,
                    ""
                );
            }
        }
    }

    private static void removeUnwantedParagraphs(final StringBuilder builder, final int count) {
        if (count > -1) {
            int positionToRemoveFrom = 0;
            for (int i = 0; i < count; i++) {
                positionToRemoveFrom = builder.indexOf("</p>", positionToRemoveFrom)+4;
                findAndRemoveIFrame(builder, positionToRemoveFrom);
            }
            builder.replace(positionToRemoveFrom, builder.length()-1, "");
        }
    }

    private static void findAndRemoveIFrame(final StringBuilder builder, final int position) {
        final int iframePositionStart = builder.indexOf("<iframe", position);
        if (iframePositionStart == -1) {
            return;
        }

        final int wrappingParagraphStart = iframePositionStart - 3;
        final int wrappingParagraphEnd = builder.indexOf("</p>", wrappingParagraphStart)+4;
        builder.replace(wrappingParagraphStart, wrappingParagraphEnd, "");
    }

    private static void removeLastParagraphTags(final StringBuilder builder) {
        final int positionOfLastParagraph = builder.lastIndexOf("<p>");
        if (positionOfLastParagraph != -1) {
            builder.replace(
                positionOfLastParagraph,
                positionOfLastParagraph + 3,
                ""
            );
        }
    }
}
