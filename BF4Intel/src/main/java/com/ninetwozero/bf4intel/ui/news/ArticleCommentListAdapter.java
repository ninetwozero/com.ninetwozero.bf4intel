package com.ninetwozero.bf4intel.ui.news;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.adapter.BaseExpandableIntelAdapter;
import com.ninetwozero.bf4intel.factories.UrlFactory;
import com.ninetwozero.bf4intel.json.Profile;
import com.ninetwozero.bf4intel.json.news.NewsArticleComment;
import com.ninetwozero.bf4intel.json.news.NewsArticleCommentReply;
import com.ninetwozero.bf4intel.utils.DateTimeUtils;

import java.util.List;
import java.util.Map;

public class ArticleCommentListAdapter extends BaseExpandableIntelAdapter<NewsArticleComment> {
    public static final String ID_NO_REAL_COMMENTS = "NO_REAL_COMMENTS";
    private static final int GROUP_TYPE_FAKE = 0;
    private static final int GROUP_TYPE_REAL = 1;

    private List<NewsArticleComment> comments;
    private Map<String, Boolean> hooahs;

    public ArticleCommentListAdapter(final Context context) {
        super(context);
    }

    private void populateBasicInformation(View view, Profile author, String content, long timestamp) {
        setText(view, R.id.username, author.getUsername());
        setText(view, R.id.content, content);
        setText(view, R.id.timestamp, DateTimeUtils.toRelative(timestamp));
        loadImage(view, R.id.gravatar, UrlFactory.buildGravatarUrl(author.getGravatarHash()));
    }

    @Override
    public int getGroupType(final int groupPosition) {
        if (getGroup(groupPosition).getId().equals(ID_NO_REAL_COMMENTS)) {
            return GROUP_TYPE_FAKE;
        } else {
            return GROUP_TYPE_REAL;
        }
    }

    @Override
    public View getGroupView(final int position, final boolean isExpanded, View convertView, final ViewGroup parent) {
        if (getGroupType(position) == GROUP_TYPE_FAKE) {
            return getGroupViewForTheFakeEntry(convertView, parent);
        } else {
            return getGroupViewForRealEntries(position, isExpanded, convertView, parent);
        }
    }

    private View getGroupViewForTheFakeEntry(View convertView, final ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item_news_comment_fake, parent, false);
        }
        return convertView;
    }

    private View getGroupViewForRealEntries(final int position, final boolean isExpanded, View convertView, final ViewGroup parent) {
        final NewsArticleComment comment = getGroup(position);
        final Profile author = comment.getAuthor();

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item_news_comment, parent, false);
        }

        populateBasicInformation(convertView, author, comment.getContent(), comment.getTimestamp());
        setImage(convertView, R.id.group_indicator, fetchImageResourceForGroup(isExpanded));
        setText(
            convertView,
            R.id.num_hooahs,
            String.format(
                context.getString(R.string.num_hooahs),
                comment.getLikeCount()
            )
        );
        setVisibility(
            convertView,
            R.id.wrap_hooah_status,
            hasHooahedThisComment(comment.getId()) ? View.VISIBLE : View.GONE
        );

        return convertView;
    }

    @Override
    public View getChildView(final int group, final int child, final boolean isLastChild, View convertView, final ViewGroup parent) {
        final NewsArticleCommentReply reply = getChild(group, child);
        final Profile author = reply.getAuthor();

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item_news_comment_reply, parent, false);
        }

        populateBasicInformation(convertView, author, reply.getContent(), reply.getTimestamp());
        return convertView;
    }

    @Override
    public int getChildrenCount(final int groupPosition) {
        return getGroup(groupPosition).getReplyCount();
    }

    @Override
    public NewsArticleComment getGroup(final int group) {
        return comments.get(group);
    }

    @Override
    public NewsArticleCommentReply getChild(final int group, final int child) {
        return comments.get(group).getReplies().get(child);
    }

    @Override
    public int getGroupCount() {
        return comments != null ? comments.size() : 0;
    }

    public void setItems(final List<NewsArticleComment> comments, final Map<String, Boolean> hooahStatus) {
        this.comments = comments;
        this.hooahs = hooahStatus;
        this.notifyDataSetChanged();
    }

    private boolean hasHooahedThisComment(final String commentId) {
        return hooahs != null && (hooahs.containsKey(commentId) ? hooahs.get(commentId) : false);
    }

    private int fetchImageResourceForGroup(final boolean isExpanded) {
        return isExpanded ? R.drawable.ic_menu_arrow_up_dark : R.drawable.ic_menu_arrow_down_dark;
    }

    public boolean hasComments() {
        return !getGroup(0).getId().equals(ArticleCommentListAdapter.ID_NO_REAL_COMMENTS);
    }
}
