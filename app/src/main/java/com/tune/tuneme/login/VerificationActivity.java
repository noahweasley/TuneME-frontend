package com.tune.tuneme.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.appcompat.app.AppCompatActivity;

import com.tune.tuneme.databinding.VerificationBinding;

public class VerificationActivity extends AppCompatActivity {
    private static final String ARG_PHONE_NUMBER = "Recipient's phone number";
    private VerificationBinding binding;

    /**
     * Helper method to start this activity
     *
     * @param context the originating context
     * @param phoneNumber the phone number of the user that would receive the verification message
     */
    public static void start(Context context, String phoneNumber) {
        Intent starter = new Intent(context, VerificationActivity.class);
        starter.putExtra(ARG_PHONE_NUMBER, phoneNumber);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = VerificationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.signUp.setOnClickListener(t -> CompleteRegistrationActivity.start(this));

        String phoneNumber = getIntent().getStringExtra(ARG_PHONE_NUMBER);
        if(!TextUtils.isEmpty(phoneNumber))
            binding.phoneNumber.setText(phoneNumber);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
