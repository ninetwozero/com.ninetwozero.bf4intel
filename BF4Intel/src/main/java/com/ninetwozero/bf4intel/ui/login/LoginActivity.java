package com.ninetwozero.bf4intel.ui.login;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.ninetwozero.bf4intel.Bf4Intel;
import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseLoadingIntelActivity;
import com.ninetwozero.bf4intel.database.dao.login.SummarizedSoldierStatsDAO;
import com.ninetwozero.bf4intel.database.entities.ProfileEntity;
import com.ninetwozero.bf4intel.factories.BundleFactory;
import com.ninetwozero.bf4intel.factories.UrlFactory;
import com.ninetwozero.bf4intel.json.Profile;
import com.ninetwozero.bf4intel.json.login.SoldierListingRequest;
import com.ninetwozero.bf4intel.json.login.SummarizedSoldierStats;
import com.ninetwozero.bf4intel.network.SimpleGetRequest;
import com.ninetwozero.bf4intel.repository.ProfileRepository;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.ui.search.SearchActivity;
import com.ninetwozero.bf4intel.utils.BusProvider;
import com.ninetwozero.bf4intel.utils.PersonaUtils;

import java.lang.ref.WeakReference;

import se.emilsjolander.sprinkles.SqlStatement;
import se.emilsjolander.sprinkles.Transaction;

public class LoginActivity extends BaseLoadingIntelActivity {
    public static final int REQUEST_PROFILE = 0;

    private Bundle profileBundle;
    private View loginStatusView;
    private SharedPreferences sharedPreferences;
    private int selectedSoldierPlatform;
    private String selectedPersonaId;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initialize();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!Bf4Intel.isConnectedToNetwork()) {
            displayNetworkNotice();
        }
        BusProvider.getInstance().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(this);
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SearchActivity.REQUEST_SEARCH && resultCode == Activity.RESULT_OK) {
            final Profile profile = (Profile) data.getSerializableExtra(SearchActivity.RESULT_SEARCH_RESULT);
            selectedSoldierPlatform = data.getIntExtra(SearchActivity.RESULT_SEARCH_RESULT_PLATFORM, 0);
            selectedPersonaId = data.getStringExtra(SearchActivity.RESULT_SEARCH_RESULT_PERSONA_ID);

            //new ProfileDAO(profile).saveAsync();
            new AgentAsyncTask(this, profile).execute();

            profileBundle = BundleFactory.createForProfile(profile);
            loadSoldiers(profileBundle);
        }
    }

    // TODO: Refactor functionality to a service?
    private void loadSoldiers(final Bundle bundle) {
        setLoadingState(true);
        Bf4Intel.getRequestQueue().add(
                new SimpleGetRequest<SoldierListingRequest>(
                        UrlFactory.buildSoldierListURL(bundle.getString(Keys.Profile.ID)),
                        this) {
                    private int bf4SoldierCount;
                    private SummarizedSoldierStats selectedSoldier;

                    @Override
                    protected SoldierListingRequest doParse(String json) {
                        final JsonObject baseObject = extractFromJson(json);
                        final SoldierListingRequest request = gson.fromJson(baseObject, SoldierListingRequest.class);
                        final Transaction transaction = new Transaction();
                        final int soldierCount = request.getSoldiers().size();

                        for (int i = 0; i < soldierCount; i++) {
                            final SummarizedSoldierStats stats = request.getSoldiers().get(i);
                            if (PersonaUtils.isBf4Soldier(stats.getGameId())) {
                                if (bf4SoldierCount == 0) {
                                    new SqlStatement(
                                        "DELETE FROM " +
                                        SummarizedSoldierStatsDAO.TABLE_NAME + " " +
                                        "WHERE soldierId  = ? AND platformId = ?"
                                    ).execute(stats.getPersona().getPersonaId(), stats.getPlatformId());
                                }

                                if (stats.getPlatformId() == selectedSoldierPlatform && Long.toString(stats.getPersona().getPersonaId()).equals(selectedPersonaId)) {
                                    //save to room
                                    new SummarizedSoldierStatsDAO(stats).save(transaction);
                                    selectedSoldier = stats;
                                    bf4SoldierCount = 1;
                                    break;
                                }
                            }
                        }
                        transaction.setSuccessful(true);
                        transaction.finish();
                        return request;
                    }

                    @Override
                    protected void deliverResponse(SoldierListingRequest response) {
                        if (bf4SoldierCount > 0) {
                            storeSelectedPersonaInPreferences(selectedSoldier);
                            setResult(Activity.RESULT_OK, new Intent().putExtras(profileBundle));
                        } else {
                            setResult(Activity.RESULT_CANCELED);
                        }
                        finish();
                    }

                    private void storeSelectedPersonaInPreferences(SummarizedSoldierStats soldierStats) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(Keys.Menu.LATEST_PERSONA, String.valueOf(soldierStats.getPersona().getPersonaId()));
                        editor.putInt(Keys.Menu.LATEST_PERSONA_PLATFORM, soldierStats.getPlatformId());
                        editor.apply();
                    }
                }
        );
    }

    private void initialize() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        setupErrorMessage();
        setupLayout();
    }

    private void setupLayout() {
        setupForm();
        setupButton();
    }

    private void setupForm() {
        loginStatusView = findViewById(R.id.login_status);
        EditText searchField = (EditText) findViewById(R.id.search_term);

        searchField.setOnEditorActionListener(
            new EditText.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                    if (id == EditorInfo.IME_ACTION_SEARCH) {
                        if (Bf4Intel.isConnectedToNetwork()) {
                            onFormSubmitted();
                        }
                        return true;
                    }
                    return false;
                }
            }
        );
    }

    private void setupButton() {
        findViewById(R.id.button_search_account).setOnClickListener(
            new OnClickListener() {
                @Override
                public void onClick(View v) {
                    onFormSubmitted();
                }
            }
        );
    }

    private void onFormSubmitted() {
        final EditText searchField = (EditText) findViewById(R.id.search_term);
        final String searchTerm = searchField.getText().toString().trim();
        if ("".equals(searchTerm) || searchTerm.length() < 3) {
            searchField.setError(getString(R.string.msg_search_error_length));
            return;
        } else {
            searchField.setError(null);
        }

        startActivityForResult(
            new Intent(LoginActivity.this, SearchActivity.class).putExtra(
                SearchActivity.QUERY,
                searchTerm
            ),
            SearchActivity.REQUEST_SEARCH
        );
    }

    private void displayNetworkNotice() {
        showErrorMessage(R.string.msg_no_network);
        findViewById(R.id.button_search_account).setEnabled(false);
    }

    private void setLoadingState(final boolean show) {
        loginStatusView.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    private static class AgentAsyncTask extends AsyncTask<Void, Void, Integer> {

        private WeakReference<Activity> weakActivity;
        private Profile profile;
        public AgentAsyncTask(Activity activity, Profile profile) {
            weakActivity = new WeakReference<>(activity);
            this.profile = profile;
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            Application app = weakActivity.get().getApplication();
            ProfileRepository repository = ((Bf4Intel) app).getProfileRepository();
            repository.insert(new ProfileEntity(profile));
            return null;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            Activity activity = weakActivity.get();
            if(activity == null) {
                return;
            } else {
                Toast.makeText(activity, "Profile saved", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
