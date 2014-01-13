package com.ninetwozero.bf4intel.ui.news;

import android.content.Context;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseLayoutPopulator;
import com.ninetwozero.bf4intel.datatypes.HooahToggleRequest;
import com.ninetwozero.bf4intel.json.news.NewsArticle;
import com.ninetwozero.bf4intel.utils.BusProvider;
import com.ninetwozero.bf4intel.utils.DateTimeUtils;

public class NewsArticleLayout extends BaseLayoutPopulator {
    public static void populate(
        final Context context, final View view,
        final NewsArticle article, final boolean enableLinkClicking
    ) {
        setText(view, R.id.title, article.getTitle());
        setText(view, R.id.timestamp, DateTimeUtils.toRelative(context, article.getTimestamp()));

        final TextView contentTextView = (TextView) view.findViewById(R.id.content);
        if (enableLinkClicking) {
            contentTextView.setMovementMethod(LinkMovementMethod.getInstance());
            contentTextView.setAutoLinkMask(Linkify.WEB_URLS | Linkify.EMAIL_ADDRESSES);
        } else {
            contentTextView.setMovementMethod(null);
            contentTextView.setAutoLinkMask(0);
        }
        contentTextView.setText(Html.fromHtml(article.fetchTrimmedContent()).toString());

        if (article.hasAuthor()) {
            setText(view, R.id.username, article.getAuthor().getUsername());
            setVisibilty(view, R.id.wrap_user, View.VISIBLE);
        } else {
            setVisibilty(view, R.id.wrap_user, View.GONE);
        }

        setText(
            view,
            R.id.num_hooahs,
            String.format(
                context.getString(R.string.num_hooahs),
                article.getHooahCount()
            )
        );
        setText(
            view,
            R.id.num_comments,
            String.format(
                context.getString(R.string.num_comments),
                article.getCommentCount()
            )
        );

        // TODO: How do we want to display the the user has already hooah'd a post? Other color?
        final ImageView buttonHooah = (ImageView) view.findViewById(R.id.button_hooah);
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
