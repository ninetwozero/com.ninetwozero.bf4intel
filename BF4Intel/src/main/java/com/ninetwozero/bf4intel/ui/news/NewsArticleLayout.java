package com.ninetwozero.bf4intel.ui.news;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.PopupMenu;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseLayoutPopulator;
import com.ninetwozero.bf4intel.datatypes.HooahToggleRequest;
import com.ninetwozero.bf4intel.factories.UrlFactory;
import com.ninetwozero.bf4intel.json.news.NewsArticle;
import com.ninetwozero.bf4intel.utils.BusProvider;
import com.ninetwozero.bf4intel.utils.DateTimeUtils;
import com.squareup.picasso.Picasso;

public class NewsArticleLayout extends BaseLayoutPopulator {
    private final String IFRAME_START = "<p><iframe";
    private final String IFRAME_END = "</iframe></p>";
    private final String EMPTY_P = "<p>&nbsp;</p>";

    private final Context context;
    private final View container;

    public NewsArticleLayout(final Context context, final View container) {
        this.context = context;
        this.container = container;
    }

    public void populate(final NewsArticle article, final boolean isInDetailedView) {
        populateTop(article);
        populateContent(article, isInDetailedView);
        populateBottomMeta(article);
        populateActionItems(article);
    }

    private void populateTop(final NewsArticle article) {
        setText(container, R.id.title, article.getTitle());
        setText(container, R.id.timestamp, DateTimeUtils.toRelative(context, article.getTimestamp()));
        if (article.hasAuthor()) {
            setText(container, R.id.username, article.getAuthor().getUsername());
            setVisibilty(container, R.id.wrap_user, View.VISIBLE);
        } else {
            setVisibilty(container, R.id.wrap_user, View.GONE);
        }
    }

    private void populateContent(final NewsArticle article, final boolean isInDetailedView) {
        final ImageView previewImageView = (ImageView) container.findViewById(R.id.preview);
        final WebView articleContentView = (WebView) container.findViewById(R.id.content);
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

    private boolean removeIframesFromContent(final StringBuilder content) {
        final int positionOfFirst = content.indexOf(IFRAME_START);
        if (positionOfFirst > -1) {
            for (int i = positionOfFirst; i != -1; i=content.indexOf(IFRAME_START)) {
                content.replace(i, content.indexOf(IFRAME_END, i), "");
            }
            return true;
        }
        return false;
    }

    private void populateBottomMeta(final NewsArticle article) {
        setText(
            container,
            R.id.num_hooahs,
            String.format(context.getString(R.string.num_hooahs), article.getHooahCount())
        );
        setText(
            container,
            R.id.num_comments,
            String.format(context.getString(R.string.num_comments), article.getCommentCount())
        );
    }

    private void populateActionItems(final NewsArticle article) {
        // TODO: How do we want to display the the user has already hooah'd a post? Other color?
        final ImageView buttonHooah = (ImageView) container.findViewById(R.id.button_hooah);
        buttonHooah.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BusProvider.getInstance().post(new HooahToggleRequest(article.getId()));
                }
            }
        );
        container.findViewById(R.id.button_overflow).setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setupPopupMenuForActionItems(view, article);
                }
            }
        );
    }

    private void setupPopupMenuForActionItems(final View view, final NewsArticle article) {
        final PopupMenu menu = new PopupMenu(context, view);
        menu.inflate(R.menu.news_article_actions);
        menu.setOnMenuItemClickListener(
            new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    if (menuItem.getItemId() == R.id.ab_action_share) {
                        final Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("text/plain");
                        intent.putExtra(
                            Intent.EXTRA_TEXT,
                            UrlFactory.buildNewsArticleWebURL(article.getUrlSlug()).toString()
                        );
                        intent.putExtra(Intent.EXTRA_SUBJECT, article.getTitle());
                        context.startActivity(
                            Intent.createChooser(
                                intent, context.getString(R.string.label_share)
                            )
                        );
                        return true;
                    }
                    return false;
                }
            }
        );
        menu.show();
    }
}
