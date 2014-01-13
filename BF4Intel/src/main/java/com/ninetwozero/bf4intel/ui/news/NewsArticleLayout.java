package com.ninetwozero.bf4intel.ui.news;

import android.content.Context;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseLayoutPopulator;
import com.ninetwozero.bf4intel.datatypes.HooahToggleRequest;
import com.ninetwozero.bf4intel.json.news.NewsArticle;
import com.ninetwozero.bf4intel.utils.BusProvider;
import com.ninetwozero.bf4intel.utils.DateTimeUtils;
import com.ninetwozero.bf4intel.utils.HtmlUtils;

public class NewsArticleLayout extends BaseLayoutPopulator {
    private final static int COUNT_DEFAULT = 3;
    private final static int COUNT_ALL = -1;

    public static void populate(
        final Context context, final View view,
        final NewsArticle article, final boolean isInDetailedView
    ) {
        populateTop(context, view, article);
        populateContent(view, article.getContent(), isInDetailedView);
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
        final View parent,
        final String content,
        final boolean isInDetailedView
    ) {
        final int paragraphCount = isInDetailedView ? COUNT_ALL : COUNT_DEFAULT;
        final String theContent = HtmlUtils.trimContentForNews(content, paragraphCount);

        final TextView contentTextView = (TextView) parent.findViewById(R.id.content);
        if (isInDetailedView) {
            contentTextView.setMovementMethod(LinkMovementMethod.getInstance());
            contentTextView.setAutoLinkMask(Linkify.WEB_URLS | Linkify.EMAIL_ADDRESSES);
        } else {
            contentTextView.setMovementMethod(null);
            contentTextView.setAutoLinkMask(0);
        }
        contentTextView.setText(theContent);

        Log.d("YOLO", "content => " + theContent);
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
