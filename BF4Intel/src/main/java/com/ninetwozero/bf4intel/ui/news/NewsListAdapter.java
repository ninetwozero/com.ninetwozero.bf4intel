package com.ninetwozero.bf4intel.ui.news;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.adapter.BaseIntelAdapter;
import com.ninetwozero.bf4intel.json.news.NewsArticle;

public class NewsListAdapter extends BaseIntelAdapter<NewsArticle> {

    public NewsListAdapter(final Context context) {
        super(context);
    }

    @Override
    public long getItemId(final int position) {
        return Long.parseLong(getItem(position).getId());
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        final NewsArticle article = getItem(position);

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_header_news_article, parent, false);
        }

        new NewsArticleLayout(context, convertView).populate(article, false);
        return convertView;
    }
}
