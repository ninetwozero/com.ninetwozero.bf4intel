package com.ninetwozero.bf4intel.ui.news;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Html;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseLayoutPopulator;
import com.ninetwozero.bf4intel.datatypes.HooahToggleRequest;
import com.ninetwozero.bf4intel.datatypes.Link;
import com.ninetwozero.bf4intel.datatypes.ParsedArticleContent;
import com.ninetwozero.bf4intel.factories.UrlFactory;
import com.ninetwozero.bf4intel.json.news.NewsArticle;
import com.ninetwozero.bf4intel.utils.BusProvider;
import com.ninetwozero.bf4intel.utils.DateTimeUtils;
import com.ninetwozero.bf4intel.utils.NewsUtils;

public class NewsArticleLayout extends BaseLayoutPopulator implements View.OnClickListener {
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
        setText(container, R.id.title, Html.fromHtml(article.getTitle()).toString());
        setText(container, R.id.timestamp, DateTimeUtils.toRelative(article.getTimestamp()));
        if (article.hasAuthor()) {
            setText(container, R.id.username, article.getAuthor().getUsername());
            setVisibilty(container, R.id.wrap_user, View.VISIBLE);
        } else {
            setVisibilty(container, R.id.wrap_user, View.GONE);
        }
    }

    private void populateContent(final NewsArticle article, final boolean isInDetailedView) {
        final TextView contentView = (TextView) container.findViewById(R.id.content);
        final LinearLayout linkContainer = (LinearLayout) container.findViewById(R.id.link_wrap);

        final ParsedArticleContent parsedContent = NewsUtils.parseContent(article.getContent(), isInDetailedView);
        contentView.setText(Html.fromHtml(parsedContent.getText()));

        linkContainer.removeAllViews();
        if (isInDetailedView) {
            final LayoutInflater inflater = LayoutInflater.from(context);
            for (int i = 0, max = parsedContent.getLinks().size(); i < max; i++) {
                final Link link = parsedContent.getLinks().get(i);
                final View linkView = inflater.inflate(R.layout.news_link_item, null);
                final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                );

                ((TextView) linkView.findViewById(R.id.link_url)).setText(link.getUrl());
                ((TextView) linkView.findViewById(R.id.link_title)).setText(
                    "[" + (i+1) +"] " + link.getTitle()
                );

                layoutParams.setMargins(0, 24, 0, 0);
                linkView.setLayoutParams(layoutParams);
                linkView.setOnClickListener(this);
                linkView.setTag(link.getUrl());
                linkContainer.addView(linkView);
            }
        }
        contentView.setAutoLinkMask(isInDetailedView ? Linkify.WEB_URLS : 0);
        linkContainer.setVisibility(isInDetailedView ? View.VISIBLE : View.GONE);
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
        final ImageView buttonHooah = (ImageView) container.findViewById(R.id.button_hooah);
        buttonHooah.setImageResource(fetchImageForHooah(article.hasUserSaidHooah()));
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

    private int fetchImageForHooah(final boolean hasSaidHooah) {
        return hasSaidHooah ? R.drawable.ic_menu_hooah_ok : R.drawable.ic_menu_hooah;
    }

    private void setupPopupMenuForActionItems(final View view, final NewsArticle article) {
        final PopupMenu menu = new PopupMenu(context, view);
        menu.inflate(R.menu.news_article_popup_actions);
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

    public void updateHooahForArticle(final int count, final boolean voted) {
        ((ImageView) container.findViewById(R.id.button_hooah)).setImageResource(
            voted ? R.drawable.ic_menu_hooah_ok : R.drawable.ic_menu_hooah
        );
        ((TextView) container.findViewById(R.id.num_hooahs)).setText(
            String.format(
                context.getString(R.string.num_hooahs),
                count
            )
        );
    }

    @Override
    public void onClick(View v) {
        final String url = v.getTag().toString();
        final Context context = v.getContext();
        if (context == null) {
            return;
        }

        context.startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(url)));
    }
}
