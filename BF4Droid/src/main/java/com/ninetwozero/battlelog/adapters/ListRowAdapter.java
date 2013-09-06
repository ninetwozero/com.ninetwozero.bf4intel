package com.ninetwozero.battlelog.adapters;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ninetwozero.battlelog.R;
import com.ninetwozero.battlelog.datatypes.ListRow;
import com.ninetwozero.battlelog.datatypes.ListRowType;

import java.io.File;
import java.util.List;

import static com.ninetwozero.battlelog.datatypes.ListRowType.*;

/* TODO:
    Implement the different view types so that the adapter can recycle accordingly
 */

public class ListRowAdapter extends BaseAdapter {
    private Context mContext;
    private List<ListRow> mItems;

    public ListRowAdapter(final Context context, final List<ListRow> items) {
        mContext = context;
        mItems = items;
    }

    @Override
    public int getCount() {
        return mItems == null? 0 : mItems.size();
    }

    @Override
    public ListRow getItem(int i) {
        return mItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(final int position) {
        return getItem(position).getType().isEnabled();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        final ListRow item = mItems.get(position);
        final ListRowType type = item.getType();

        if( view == null ) {
            view = LayoutInflater.from(mContext).inflate(item.getLayout(), null, false);
        }

        return populateViewFromItem(view, item);
    }

    private View populateViewFromItem(final View view, final ListRow item) {
        final Bundle stringMappings = item.getStringMappings();
        final Bundle drawableMappings = item.getDrawableMappings();
        final ListRowType type = item.getType();
        final boolean isRegular = type == SIDE_REGULAR || type == SIDE_REGULAR_OPTION;
        final boolean isHeading = type == HEADING || type == SIDE_HEADING;

        if( isRegular || isHeading ) {
            ((TextView) view.findViewById(R.id.text1)).setText(item.getTitle());
        } else {
            int resourceId = 0;
            Object drawable = null;
            ImageView imageView = null;

            for( String key : stringMappings.keySet() ) {
                resourceId = Integer.parseInt(key);
                ((TextView) view.findViewById(resourceId)).setText(stringMappings.getString(key));
            }

            for( String key : drawableMappings.keySet() ) {
                drawable = drawableMappings.get(key);
                if( drawable == null ) {
                    continue;
                }
                resourceId = Integer.parseInt(key);
                imageView = (ImageView) view.findViewById(resourceId);

                if( drawable instanceof String ) {
                    final String path = mContext.getExternalFilesDir(null) + "/" + drawable + ".png";
                    final File image = new File(path);

                    if( image.exists() ) {
                        imageView.setImageURI(Uri.fromFile(image));
                    } else {
                        imageView.setImageResource(R.drawable.ic_launcher);
                    }
                } else if( drawable instanceof Integer ) {
                    imageView.setImageResource((Integer) drawable);
                }
            }
        }
        return view;
    }
}
