package com.ninetwozero.bf4intel.ui.news;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.adapter.BaseIntelAdapter;
import com.ninetwozero.bf4intel.factories.UrlFactory;
import com.ninetwozero.bf4intel.json.Profile;
import com.ninetwozero.bf4intel.json.news.NewsArticleComment;
import com.ninetwozero.bf4intel.json.news.NewsArticleCommentReply;
import com.ninetwozero.bf4intel.utils.DateTimeUtils;

import java.util.List;

public class ArticleCommentListAdapter extends BaseIntelAdapter<NewsArticleComment> {
    public static final int IS_HEADER = 0;

    public ArticleCommentListAdapter(final Context context) {
        super(context);
    }

    @Override
    public long getItemId(final int position) {
        return Long.parseLong(getItem(position).getId());
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        final NewsArticleComment comment = getItem(position);
        final Profile author = comment.getAuthor();

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item_news_comment, parent, false);
        }

        populateBasicInformation(convertView, author, comment.getContent(), comment.getTimestamp());
        setText(
            convertView,
            R.id.num_hooahs,
            String.format(
                context.getString(R.string.num_hooahs),
                comment.getLikeCount()
            )
        );
        addRepliesToView((LinearLayout)convertView.findViewById(R.id.wrap_replies), comment.getReplies());

        return convertView;
    }

    private void addRepliesToView(final LinearLayout container, final List<NewsArticleCommentReply> replies) {
        container.removeAllViews();

        for (NewsArticleCommentReply reply : replies) {
            final Profile author = reply.getAuthor();
            final View view = layoutInflater.inflate(R.layout.list_item_news_comment_reply, null);
            populateBasicInformation(view, author, reply.getContent(), reply.getTimestamp());
            container.addView(view);
        }
    }

    private void populateBasicInformation(View view, Profile author, String content, long timestamp) {
        setText(view, R.id.username, author.getUsername());
        setText(view, R.id.content, content);
        setText(view, R.id.timestamp, DateTimeUtils.toRelative(context, timestamp));
        loadImage(view, R.id.gravatar, UrlFactory.buildGravatarUrl(author.getGravatarHash()));
    }
}
