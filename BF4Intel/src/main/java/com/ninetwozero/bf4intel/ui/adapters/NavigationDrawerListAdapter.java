package com.ninetwozero.bf4intel.ui.adapters;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.adapter.BaseIntelAdapter;
import com.ninetwozero.bf4intel.interfaces.ListRowElement;
import com.ninetwozero.bf4intel.menu.ListRowType;
import com.ninetwozero.bf4intel.menu.SimpleListRow;
import com.ninetwozero.bf4intel.menu.SoldierSpinnerRow;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.ui.datatypes.ActiveSoldierChangedEvent;
import com.ninetwozero.bf4intel.utils.BusProvider;

import java.io.File;
import java.util.List;

import static com.ninetwozero.bf4intel.menu.ListRowType.HEADING;
import static com.ninetwozero.bf4intel.menu.ListRowType.SIDE_HEADING;
import static com.ninetwozero.bf4intel.menu.ListRowType.SIDE_REGULAR;
import static com.ninetwozero.bf4intel.menu.ListRowType.SIDE_REGULAR_CHILD;
import static com.ninetwozero.bf4intel.menu.ListRowType.SIDE_SOLDIER;

public class NavigationDrawerListAdapter extends BaseIntelAdapter<ListRowElement> {
    public NavigationDrawerListAdapter(final Context context, final List<ListRowElement> items) {
        super(context, items);
    }

    @Override
    public int getViewTypeCount() {
        return ListRowType.SIZE;
    }

    @Override
    public int getItemViewType(final int position) {
        return getItem(position).getType().ordinal();
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
        final ListRowElement item = getItem(position);
        if (view == null) {
            view = LayoutInflater.from(context).inflate(item.getLayout(), viewGroup, false);
        }
        return populateViewFromItem(view, item);
    }

    private View populateViewFromItem(final View view, final ListRowElement item) {
        final ListRowType type = item.getType();
        final boolean isRegular = type == SIDE_REGULAR || type == SIDE_REGULAR_CHILD;
        final boolean isHeading = type == HEADING || type == SIDE_HEADING;

        if (isRegular || isHeading) {
            setText(view, R.id.text1, item.getTitle());
        } else if (type == SIDE_SOLDIER) {
            populateSoldierBox(view, item);
        } else {
            populateSpecialLayouts(view, item);
        }
        return view;
    }

    private void populateSoldierBox(final View view, final ListRowElement item) {
        if (item instanceof SoldierSpinnerRow) {
            final SoldierSpinnerRow row = (SoldierSpinnerRow) item;
            final SoldierSpinnerAdapter adapter = new SoldierSpinnerAdapter(context, row.getSoldierStats());
            final Spinner spinner = (Spinner) view.findViewById(R.id.spinner_soldier);

            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        PreferenceManager.getDefaultSharedPreferences(context).edit().putLong(
                            Keys.Menu.LATEST_PERSONA,
                            id
                        ).commit();
                        BusProvider.getInstance().post(new ActiveSoldierChangedEvent(id));
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                }
            );
        }
    }

    private void populateSpecialLayouts(final View view, final ListRowElement item) {
        if (!(item instanceof SimpleListRow)) {
            return;
        }

        final SimpleListRow row = (SimpleListRow) item;
        final Bundle stringMappings = row.getStringMappings();
        final Bundle drawableMappings = row.getDrawableMappings();

        populateTextViews(view, stringMappings);
        populateImageViews(view, drawableMappings);
    }

    private void populateTextViews(final View view, final Bundle mappings) {
        int resourceId;
        for (String key : mappings.keySet()) {
            resourceId = Integer.parseInt(key);
            setText(view, resourceId, mappings.getString(key));
        }
    }

    private void populateImageViews(final View view, final Bundle mappings) {
        Object drawable;
        ImageView imageView;
        int resourceId;

        for (String key : mappings.keySet()) {
            drawable = mappings.get(key);
            if (drawable == null) {
                continue;
            }

            resourceId = Integer.parseInt(key);
            imageView = (ImageView) view.findViewById(resourceId);

            if (drawable instanceof String) {
                populateImageViewFromString(imageView, String.valueOf(drawable));
            } else if (drawable instanceof Integer) {
                imageView.setImageResource((Integer) drawable);
            }
        }
    }

    private void populateImageViewFromString(final ImageView imageView, final String filename) {
        final String path = context.getExternalFilesDir(null) + "/" + filename + ".png";
        final File image = new File(path);
        if (image.exists()) {
            imageView.setImageURI(Uri.fromFile(image));
        } else {
            imageView.setImageResource(R.drawable.ic_launcher);
        }
    }
}
