package com.ninetwozero.bf4intel.ui.about;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.ui.about.datatypes.BaseAboutRow;
import com.ninetwozero.bf4intel.ui.about.datatypes.HeaderAboutRow;
import com.ninetwozero.bf4intel.ui.about.datatypes.SimpleAboutRow;
import com.tonicartos.widget.stickygridheaders.StickyGridHeadersBaseAdapterWrapper;
import com.tonicartos.widget.stickygridheaders.StickyGridHeadersGridView;

import java.util.List;

public abstract class BaseAboutFragment
    extends Fragment
    implements StickyGridHeadersGridView.OnItemClickListener
{
    private StickyGridHeadersGridView gridView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        final View view = inflater.inflate(R.layout.fragment_about, container, false);
        initialize(view);
        return view;
    }

    @Override
    public void onItemClick(final AdapterView<?> av, final View v, final int position, final long id) {
        final StickyGridHeadersBaseAdapterWrapper adapter = (StickyGridHeadersBaseAdapterWrapper) gridView.getAdapter();
        final SimpleAboutRow row = ((AboutListAdapter) adapter.getWrappedAdapter()).getItem(position);
        if (BaseAboutRow.Type.HEADER == row.getType() || !row.hasUrl()) {
            return;
        }

        startActivity(
            Intent.createChooser(
                new Intent(Intent.ACTION_VIEW).setData(Uri.parse(row.getUrl())),
                "Select an app"
            )
        );
    }

    private void initialize(final View view) {
        setupGridView(view);
        setListAdapter(new AboutListAdapter(getActivity(), getHeadersForList(), getItemsForList()));
    }

    private void setupGridView(final View view) {
        gridView = (StickyGridHeadersGridView) view.findViewById(android.R.id.list);
        gridView.setAreHeadersSticky(false);
        gridView.setOnItemClickListener(this);
    }

    protected abstract List<HeaderAboutRow> getHeadersForList();
    protected abstract List<SimpleAboutRow> getItemsForList();

    public AboutListAdapter getListAdapter() {
        return (AboutListAdapter) gridView.getAdapter();
    }

    public void setListAdapter(final AboutListAdapter listAdapter) {
        gridView.setAdapter(listAdapter);
    }
}
