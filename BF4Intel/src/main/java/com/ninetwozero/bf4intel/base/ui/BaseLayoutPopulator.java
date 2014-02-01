package com.ninetwozero.bf4intel.base.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public abstract class BaseLayoutPopulator {

    public static void setText(final View view, final int resourceId, final int stringResource) {
        ((TextView) view.findViewById(resourceId)).setText(stringResource);
    }

    public static void setText(final View view, final int resourceId, final String string) {
        ((TextView) view.findViewById(resourceId)).setText(string);
    }

    public static void setImage(final View view, final int resourceId, final int imageResource) {
        ((ImageView) view.findViewById(resourceId)).setImageResource(imageResource);
    }

    public static void loadImage(final View view, final int resourceId, final String url) {
        Picasso.with(view.getContext()).load(url).into((ImageView) view.findViewById(resourceId));
    }

    public static void setProgress(final View view, final int resourceId, final int current, final int max) {
        final ProgressBar progressBar = (ProgressBar) view.findViewById(resourceId);
        progressBar.setProgress(current);
        progressBar.setMax(max);
    }

    public static void setVisibilty(final View view, final int resourceId, final int state) {
        view.findViewById(resourceId).setVisibility(state);
    }
}
