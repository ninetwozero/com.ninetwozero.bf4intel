package com.ninetwozero.bf4intel.ui.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.adapter.BaseIntelAdapter;
import com.ninetwozero.bf4intel.factories.UrlFactory;
import com.ninetwozero.bf4intel.json.Profile;
import com.ninetwozero.bf4intel.json.search.ProfileSearchResult;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProfileSearchAdapter extends BaseIntelAdapter<ProfileSearchResult> {

    public ProfileSearchAdapter(List<ProfileSearchResult> itemsList, Context context) {
        super(itemsList, context);
    }

    @Override
    public long getItemId(int position) {
        return Long.parseLong(getItem(position).getProfile().getId());
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final ProfileSearchResult searchResult = itemsList.get(position);
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item_profile_search, parent, false);
        }

        final Profile profile = searchResult.getProfile();
        final ImageView imageView = (ImageView) view.findViewById(R.id.gravatar);

        ((TextView) view.findViewById(R.id.username)).setText(profile.getUsername());
        ((TextView) view.findViewById(R.id.persona)).setText(
            String.format(
                context.getString(R.string.x_on_y),
                searchResult.getPersonaName(),
                fetchPlatformNameFromCrypticText(searchResult.getPlatform())
            )
        );

        Picasso.with(context).load(
            UrlFactory.buildGravatarUrl(profile.getGravatarHash())
        ).placeholder(R.drawable.default_gravatar).into(imageView);
        return view;
    }

    private String fetchPlatformNameFromCrypticText(final String crypticPlatformName) {
        if (crypticPlatformName.equals("cem_ea_id")) {
            return "PC";
        } else {
            return crypticPlatformName.toUpperCase();
        }
    }
}