package com.ninetwozero.bf4intel.ui.news;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseLayoutPopulator;
import com.ninetwozero.bf4intel.datatypes.HooahToggleRequest;
import com.ninetwozero.bf4intel.json.news.NewsArticle;
import com.ninetwozero.bf4intel.utils.BusProvider;
import com.ninetwozero.bf4intel.utils.DateTimeUtils;
import com.squareup.picasso.Picasso;

public class NewsArticleLayout extends BaseLayoutPopulator {
    private final static String IFRAME_START = "<p><iframe";
    private final static String IFRAME_END = "</iframe></p>";
    private final static String EMPTY_P = "<p>&nbsp;</p>";

    public static void populate(
        final Context context, final View view,
        final NewsArticle article, final boolean isInDetailedView
    ) {
        populateTop(context, view, article);
        populateContent(context, view, article, isInDetailedView);
        populateBottomMeta(context, view, article);
        populateActionItems(view, article);
    }

    private static void populateTop(
        final Context context, final View view, final NewsArticle article
    ) {
        setText(view, R.id.title, article.getTitle());
        setText(view, R.id.timestamp, DateTimeUtils.toRelative(context, article.getTimestamp()));
        if (article.hasAuthor()) {
            setText(view, R.id.username, article.getAuthor().getUsername());
            setVisibilty(view, R.id.wrap_user, View.VISIBLE);
        } else {
            setVisibilty(view, R.id.wrap_user, View.GONE);
        }
    }

    private static void populateContent(
        final Context context,
        final View parent,
        final NewsArticle article,
        final boolean isInDetailedView
    ) {
        final ImageView previewImageView = (ImageView) parent.findViewById(R.id.preview);
        final WebView articleContentView = (WebView) parent.findViewById(R.id.content);
        StringBuilder content = new StringBuilder(article.getContent().replaceAll(EMPTY_P, ""));

        if (!isInDetailedView) {
            if (removeIframesFromContent(content)) {
                Picasso.with(context).load(article.getThumbnailUrl()).into(previewImageView);
            }

            // TODO: Make WebView non-focusable/clickable
        }

        previewImageView.setVisibility(isInDetailedView ? View.GONE : View.VISIBLE);
        articleContentView.loadDataWithBaseURL(null, content.toString(), "text/html", "UTF-8", null);
    }

    private static boolean removeIframesFromContent(final StringBuilder content) {
        final int positionOfFirst = content.indexOf(IFRAME_START);
        if (positionOfFirst > -1) {
            for (int i = positionOfFirst; i != -1; i=content.indexOf(IFRAME_START)) {
                content.replace(i, content.indexOf(IFRAME_END, i), "");
            }
            return true;
        }
        return false;
    }

    private static void populateBottomMeta(
        final Context context, final View parent, final NewsArticle article
    ) {
        setText(
            parent,
            R.id.num_hooahs,
            String.format(context.getString(R.string.num_hooahs), article.getHooahCount())
        );
        setText(
            parent,
            R.id.num_comments,
            String.format(context.getString(R.string.num_comments), article.getCommentCount())
        );
    }

    private static void populateActionItems(final View parent, final NewsArticle article) {
        // TODO: How do we want to display the the user has already hooah'd a post? Other color?
        final ImageView buttonHooah = (ImageView) parent.findViewById(R.id.button_hooah);
        buttonHooah.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BusProvider.getInstance().post(new HooahToggleRequest(article.getId()));
                }
            }
        );
    }
}
