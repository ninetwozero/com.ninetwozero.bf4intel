package com.ninetwozero.bf4intel.ui.news;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.adapter.BaseIntelAdapter;
import com.ninetwozero.bf4intel.factories.UrlFactory;
import com.ninetwozero.bf4intel.json.Profile;
import com.ninetwozero.bf4intel.json.news.NewsArticleComment;
import com.ninetwozero.bf4intel.utils.DateTimeUtils;

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

        setText(convertView, R.id.content, comment.getContent());
        setText(convertView, R.id.timestamp, DateTimeUtils.toRelative(context, comment.getTimestamp()));
        setText(convertView, R.id.username, author.getUsername());
        loadImage(
            convertView, R.id.gravatar, UrlFactory.buildGravatarUrl(author.getGravatarHash())
        );
        return convertView;
    }
}
