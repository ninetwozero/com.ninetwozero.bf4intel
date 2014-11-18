package com.ninetwozero.bf4intel.ui.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.SessionStore;
import com.ninetwozero.bf4intel.base.ui.BaseFragment;
import com.ninetwozero.bf4intel.database.dao.login.SummarizedSoldierStatsDAO;
import com.ninetwozero.bf4intel.events.TrackingNewProfileEvent;
import com.ninetwozero.bf4intel.factories.UrlFactory;
import com.ninetwozero.bf4intel.json.login.SummarizedSoldierStats;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.resources.maps.profile.SoldierImageMap;
import com.ninetwozero.bf4intel.ui.login.LoginActivity;
import com.ninetwozero.bf4intel.utils.BusProvider;
import com.squareup.otto.Subscribe;
import com.squareup.picasso.Picasso;

import se.emilsjolander.sprinkles.OneQuery;
import se.emilsjolander.sprinkles.Query;

public class HomeFragment extends BaseFragment implements View.OnClickListener {
    private int clickCount;

    public static HomeFragment newInstance(final Bundle data) {
        final HomeFragment fragment = new HomeFragment();
        fragment.setArguments(data);
        return fragment;
    }

    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(false);
    }

    @Override
    public void onResume() {
        super.onResume();
        BusProvider.getInstance().register(this);
    }

    @Override
    public void onPause() {
        BusProvider.getInstance().register(this);
        super.onPause();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedState) {
        final View baseView = inflater.inflate(R.layout.fragment_home, container, false);
        initialize(baseView);
        return baseView;
    }

    @Override
    public void onClick(final View v) {
        final Activity activity = getActivity();
        activity.startActivityForResult(
            new Intent(activity, LoginActivity.class),
            LoginActivity.REQUEST_PROFILE
        );
    }

    @Subscribe
    public void onStartedTrackingNewProfile(final TrackingNewProfileEvent event) {
        final View view = getView();
        if (view == null) {
            return;
        }
        initialize(view);
    }

    private void initialize(final View view) {
        if (SessionStore.hasUserId()) {
            setupTracker(view);
        } else {
            setupGuestMode(view);
        }
        setActionBarSubTitle(null);
    }

    private void setupTracker(final View view) {
        view.findViewById(R.id.wrap_guest).setVisibility(View.GONE);
        Query.one(
            SummarizedSoldierStatsDAO.class,
            "SELECT * " +
            "FROM " + SummarizedSoldierStatsDAO.TABLE_NAME + " " +
            "WHERE soldierId = ? AND platformId = ?",
            sharedPreferences.getString(Keys.Menu.LATEST_PERSONA, ""),
            sharedPreferences.getInt(Keys.Menu.LATEST_PERSONA_PLATFORM, 0)
        ).getAsync(
                getLoaderManager(),
                new OneQuery.ResultHandler<SummarizedSoldierStatsDAO>() {
                    @Override
                    public boolean handleResult(SummarizedSoldierStatsDAO soldier) {
                        final View wrap = view.findViewById(R.id.wrap_tracker);
                        final TextView soldierNameView = (TextView) wrap.findViewById(R.id.selected_soldier_name);
                        final ImageView soldierImageView = (ImageView) wrap.findViewById(R.id.selected_soldier_image);

                        soldierNameView.setText(soldier.getSoldierName());
                        Picasso.with(getActivity())
                                .load(SoldierImageMap.get(soldier.getPicture()))
                                .into(soldierImageView);

                        wrap.findViewById(R.id.button_select_another_soldier).setOnClickListener(HomeFragment.this);
                        wrap.setVisibility(View.VISIBLE);
                        return false;
                    }
                }
        );
    }

    private void setupGuestMode(final View view) {
        view.findViewById(R.id.wrap_tracker).setVisibility(View.GONE);

        final View wrap = view.findViewById(R.id.wrap_guest);
        wrap.findViewById(R.id.button_select_account).setOnClickListener(this);
        wrap.setVisibility(View.VISIBLE);
    }
}
