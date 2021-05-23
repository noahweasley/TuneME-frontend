package com.tune.tuneme.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.tune.tuneme.R;
import com.tune.tuneme.databinding.LoginBinding;
import com.tune.tuneme.discover.DiscoverActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private LoginBinding binding;
    private GoogleSignInClient mGoogleSignInClient;
    public static final String TAG = "LoginActivity";
    private ActivityResultLauncher<Intent> resultLauncher;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fade_in_1, R.anim.fade_out_1);
        binding = LoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        registerGoogleSignInCallback();
        binding.recovery.setOnClickListener(this);
        binding.login.setOnClickListener(this);
        binding.gSignParent.setOnClickListener(this);
        binding.exit.setOnClickListener(this);
        binding.createAcc.setOnClickListener(this);

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso =
                new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestEmail()
                        .requestIdToken(getString(R.string.SIGN_IN_CLIENT_ID))
                        .build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        // if account is non null, update UI accordingly
        updateUI(account);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.recovery) {
            startActivity(new Intent(this, RecoveryActivity.class));
        } else if (id == R.id.login) {
            DiscoverActivity.start(this);
        } else if (id == R.id.g_sign_parent) {
            doGoogleSignIn();
        } else if (id == R.id.exit) {
            onBackPressed();
        } else if (id == R.id.create_acc) {
            startActivity(new Intent(this, RegistrationActivity.class).setFlags(
                    Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
        } else {
            throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    }

    private void doGoogleSignIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        resultLauncher.launch(signInIntent);
    }

    private void registerGoogleSignInCallback() {
        // You can do the assignment inside onAttach or onCreate, i.e, before the activity is displayed
        resultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // There are no request codes
                        Intent data = result.getData();
                        // The Task returned from this call is always completed, no need to attach
                        // a listener.
                        Task<GoogleSignInAccount>
                                task = GoogleSignIn.getSignedInAccountFromIntent(data);
                        handleSignInResult(task);
                    }
                });
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            // Signed in successfully, show authenticated UI.
            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);
        }

    }

    // if already logged in, just go to the main screen
    private void updateUI(GoogleSignInAccount account) {
        if (account != null)
            DiscoverActivity.start(this);
    }

}
