package com.ninetwozero.bf4intel.base.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ninetwozero.bf4intel.Bf4Intel;
import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.provider.MenuProvider;
import com.ninetwozero.bf4intel.database.dao.unlocks.SortMode;
import com.ninetwozero.bf4intel.factories.GsonProvider;
import com.ninetwozero.bf4intel.ui.animations.SimpleFadeInAnimation;
import com.ninetwozero.bf4intel.ui.animations.SimpleFadeOutAnimation;
import com.ninetwozero.bf4intel.ui.awards.AwardGridFragment;
import com.ninetwozero.bf4intel.ui.menu.RefreshEvent;
import com.ninetwozero.bf4intel.utils.BusProvider;

import java.util.Arrays;

public abstract class BaseLoadingListFragment
    extends BaseListFragment
    implements Response.ErrorListener,
               MenuProvider.OnMenuProviderSelectedListener,
               SwipeRefreshLayout.OnRefreshListener {

    protected Gson gson = GsonProvider.getInstance();
    protected boolean isReloading;
    protected String[] filterTitleResources;
    protected String[] sortingKeys;
    protected String[] sortTitleResources;

    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView errorMessageView;

    @Override
    public void onCreate(final Bundle icicle) {
        super.onCreate(icicle);
        setHasOptionsMenu(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        BusProvider.getInstance().register(this);

        final ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setSubtitle(
                Bf4Intel.isConnectedToNetwork() ? null : getString(R.string.label_offline_mode)
            );
        }
    }

    @Override
    public void onRefresh() {
        startLoadingData(true);
    }

    @Override
    public void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        errorMessageView = null;
    }

    @Override
    public void onErrorResponse(final VolleyError error) {
        Log.w(getClass().getSimpleName(), "[" + error.getClass().getSimpleName() + " in onLoadFailure] " + error.getMessage());
        if (getView() != null) {
            final String message = error.getMessage();
            showErrorMessage(message == null ? error.getClass().getSimpleName() : message);
        }
    }

    protected void setupSwipeRefreshLayout(final View view) {
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setOnRefreshListener(this);
            swipeRefreshLayout.setColorScheme(
                R.color.loading_stripe_1,
                R.color.loading_stripe_2,
                R.color.loading_stripe_3,
                R.color.loading_stripe_4
            );
        }
    }

    protected void onRefreshEventReceived(RefreshEvent event) {
        if (!Bf4Intel.isConnectedToNetwork()) {
            showToast(R.string.msg_no_network);
            return;
        }
        startLoadingData(true);
    }

    protected <T> T fromJson(final String json, final Class<T> outClass) {
        final JsonObject jsonObject = extractFromJson(json, false);
        return gson.fromJson(jsonObject, outClass);
    }

    protected <T> T fromJson(final String json, final Class<T> outClass, final boolean returnTopLevelJson) {
        final JsonObject jsonObject = extractFromJson(json, returnTopLevelJson);
        return gson.fromJson(jsonObject, outClass);
    }

    protected <T> T fromJson(final Gson gsonToUse, final String json, final Class<T> outClass) {
        final JsonObject jsonObject = extractFromJson(json, false);
        return gsonToUse.fromJson(jsonObject, outClass);
    }

    protected <T> T fromJson(final Gson gsonToUse, final String json, final Class<T> outClass, final boolean returnTopLevelJson) {
        final JsonObject jsonObject = extractFromJson(json, returnTopLevelJson);
        return gsonToUse.fromJson(jsonObject, outClass);
    }

    protected JsonObject extractFromJson(final String json, boolean returnTopLevel) {
        JsonParser parser = new JsonParser();
        JsonObject topLevel = parser.parse(json).getAsJsonObject();
        return returnTopLevel? topLevel : topLevel.getAsJsonObject("data");
    }

    protected void showLoadingState(final boolean isLoading) {
        final Activity activity = getActivity();
        if (activity == null) {
            return;
        }

        toggleActionBarProgressBar(activity, isLoading);
    }

    private void toggleActionBarProgressBar(final Activity activity, final boolean isLoading) {
        final View view = getView();
        if (activity == null || view == null) {
            return;
        }

        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(isLoading);
        }
    }

    protected void setupErrorMessage(final View view) {
        errorMessageView = (TextView) view.findViewById(R.id.inline_error_message);
        if (errorMessageView == null) {
            throw new IllegalStateException("Missing TextView with ID: @id/inline_error_message");
        }

        errorMessageView.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clearErrorMessage();
                }
            }
        );
    }

    protected void showErrorMessage(final String textResource) {
        errorMessageView.setText(textResource);
        errorMessageView.startAnimation(new SimpleFadeInAnimation(errorMessageView));
    }

    protected void clearErrorMessage() {
        errorMessageView.startAnimation(new SimpleFadeOutAnimation(errorMessageView));
    }

    protected void setActionBarSubTitle(String subtitle) {
        try{
            ((AppCompatActivity)getActivity()).getSupportActionBar().setSubtitle(subtitle);
        } catch (NullPointerException npe) {
            String message = String.format(BaseFragment.class.getSimpleName()
                    + " Some of following objects maybe null getActivity() getActivity().getActionBar() subtitle ");
        }
    }

    protected void actionBarSetSubtitleFromSharedPref(String sharedPrefKey, int defaultResource) {
        String defaultSubtitle = getResourceString(defaultResource);
        String subtitle = sharedPreferences.contains(sharedPrefKey)
            ? sharedPreferences.getString(sharedPrefKey, defaultSubtitle)
            : defaultSubtitle;

        setActionBarSubTitle(subtitle);
    }

    protected String getResourceString(int resourceId) {
        return getActivity().getResources().getString(resourceId);
    }

    protected void addMenuProviderFor(int menuId, Menu menu, String[] providerTitles) {
        MenuItem sortMenu = menu.findItem(menuId);
        MenuProvider sortMenuProvider = (MenuProvider) MenuItemCompat.getActionProvider(sortMenu);
        sortMenuProvider.setMenuTitles(providerTitles);
        sortMenuProvider.setOnMenuSelectedListener(this);
    }

    protected String[] getResourceStringArray(int resourceId) {
        return getActivity().getResources().getStringArray(resourceId);
    }

    protected void setCustomEmptyText(final View container, final int resourceId){
        final TextView textView = (TextView) container.findViewById(R.id.custom_empty_text);
        if (textView != null) {
            textView.setText(resourceId);
        }
    }

    protected void setCustomEmptyText(final View container, final String string){
        final TextView textView = (TextView) container.findViewById(R.id.custom_empty_text);
        if (textView != null) {
            textView.setText(string);
        }
    }

    @Override
    public void onMenuSelected(MenuItem item) {
        int itemId = item.getItemId();
        String itemTitle = item.getTitle().toString();
        if (Arrays.asList(filterTitleResources).contains(itemTitle)) {
            handleFilterRequest(sortingKeys[itemId], filterTitleResources[itemId]);
        } else if (itemTitle.equals(sortTitleResources[0])) {
            handleSortingRequest(SortMode.ALL, sortTitleResources[0]);
        } else if (itemTitle.equals(sortTitleResources[1])) {
            handleSortingRequest(SortMode.PROGRESS, sortTitleResources[1]);
        } else {
            Log.d(AwardGridFragment.class.getSimpleName(), "Unknown MenuItem " + item.getTitle());
        }
    }

    protected void handleFilterRequest(final String category, final String subtitleResString){}

    protected void handleSortingRequest(SortMode sortMode, final String subtitleResString) {}

    protected abstract void startLoadingData(boolean showLoading);
}
