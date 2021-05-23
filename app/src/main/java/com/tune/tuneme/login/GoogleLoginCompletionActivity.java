package com.tune.tuneme.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tune.tuneme.databinding.GoogleLoginBinding;

public class GoogleLoginCompletionActivity extends AppCompatActivity {
    private static final String ARG_FIRST_NAME = "Google User First Name";
    private static final String ARG_LAST_NAME = "Google User Last Name";
    private GoogleLoginBinding binding;

    /**
     * Helper method used in starting this activity while passing the required parameters
     * <code>firstName</code> and <code>lastName</code> can be null. But it also means that there
     * would be no username generated
     *
     * @param context   the starter of this activity
     * @param firstName the required first name of the user that would be used to generate a random username
     * @param lastName  the required last name of the user hat would be used to generate a random username
     */
    public static void start(Context context, String firstName, String lastName) {
        Intent starter = new Intent(context, GoogleLoginCompletionActivity.class);
        starter.putExtra(ARG_FIRST_NAME, firstName);
        starter.putExtra(ARG_LAST_NAME, lastName);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = GoogleLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String randomUsername = generateRandomUserName();
        binding.userName.setText(randomUsername);
        binding.userName.setSelection(randomUsername.length());
        binding.signUp.setOnClickListener(v -> requestServerFeedback(binding));

    }

    // generate a random username based upon the Google user's real name
    private String generateRandomUserName() {
        String firstName = getIntent().getStringExtra(ARG_FIRST_NAME);
        String lastName = getIntent().getStringExtra(ARG_LAST_NAME);

        // don't generate any random username if first and last name wasn't provided
        if (TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName)) return null;

        String l_firstName = firstName.toLowerCase();
        String l_lastName = lastName.toLowerCase();
        StringBuilder indexBuilder = new StringBuilder(l_firstName + l_lastName);

        int n = (int) (Math.random() * 10) % 4;  // limit number to 4

        while (n-- != 0) {
            int randomUID = (int) (Math.random() * 10);
            indexBuilder.append(randomUID);
        }

        return indexBuilder.toString();
    }

    @SuppressWarnings("unused")
    private void requestServerFeedback(GoogleLoginBinding binding) {
        // if can send data, then ...
        CompleteRegistrationActivity.start(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
