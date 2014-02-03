package com.ninetwozero.bf4intel.ui.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.ninetwozero.bf4intel.Bf4Intel;
import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.SessionStore;
import com.ninetwozero.bf4intel.base.ui.BaseIntelActivity;
import com.ninetwozero.bf4intel.ui.activities.MainActivity;
import com.ninetwozero.bf4intel.utils.BusProvider;

/*
TODO:
Revamp login look&feel
Provide error messages inline (not toast)
*/

public class LoginActivity extends BaseIntelActivity {
    private static final String RESET_PASSWORD_LINK = "https://signin.ea.com/p/web/resetPassword";

    private View loginFormView;
    private View loginStatusView;
    private TextView alertText;
    private TextView loginStatusMessageView;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initialize();
    }

    @Override
    protected void onResume() {
        super.onResume();
        displayNetworkNotice(Bf4Intel.isConnectedToNetwork());
        BusProvider.getInstance().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(this);
    }

    private void initialize() {
        setupFromPreExistingData();
        setupLayout();
    }

    private void setupFromPreExistingData() {
        setTitle(R.string.title_login);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (SessionStore.isLoggedIn() && Bf4Intel.isConnectedToNetwork()) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    private void setupLayout() {
        setupForm();
        setupMenu();
    }

    private void setupForm() {
        alertText = (TextView) findViewById(R.id.login_alert);
    }

    private void setupMenu() {
        findViewById(R.id.button_menu).setOnClickListener(
            new OnClickListener() {
                @Override
                public void onClick(View view) {
                    final PopupMenu menu = new PopupMenu(LoginActivity.this, view);
                    menu.setOnMenuItemClickListener(
                        new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                Intent intent = null;
                                if (item.getItemId() == R.id.menu_about) {
                                    Toast.makeText(getApplicationContext(), "TODO: This should lead to ABout", Toast.LENGTH_SHORT).show();
                                    // TODO: intent = new Intent(LoginActivity.this, AboutActivity.class);
                                } else if (item.getItemId() == R.id.menu_reset_password) {
                                    intent = new Intent(Intent.ACTION_VIEW).setData(
                                        Uri.parse(RESET_PASSWORD_LINK)
                                    );
                                }

                                if (intent != null) {
                                    startActivity(intent);
                                }
                                return true;
                            }
                        }
                    );
                    menu.inflate(R.menu.activity_login);
                    menu.show();
                }
            }
        );
    }

    private void displayNetworkNotice(final boolean isConnected) {
        alertText.setVisibility(isConnected ? View.GONE : View.VISIBLE);
        findViewById(R.id.sign_in_button).setEnabled(isConnected);
    }

    private void displayErrorMessage(final String error) {
        alertText.setVisibility(View.VISIBLE);
        alertText.setText(error);
    }

    private void displayErrorMessage(final int errorResource) {
        alertText.setVisibility(View.VISIBLE);
        alertText.setText(errorResource);
    }

    private void clearErrorMessage() {
        alertText.setVisibility(View.GONE);
        alertText.setText("");
    }

    private void showProgress(final boolean show) {
        loginStatusView.setVisibility(show ? View.VISIBLE : View.GONE);
        loginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
    }
}
