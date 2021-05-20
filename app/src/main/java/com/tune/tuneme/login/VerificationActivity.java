package com.tune.tuneme.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.tune.tuneme.databinding.VerificationBinding;

public class VerificationActivity extends AppCompatActivity {
    private VerificationBinding binding;

    /**
     * Helper method to start this activity
     *
     * @param context the originating context
     */
    public static void start(Context context) {
        Intent starter = new Intent(context, VerificationActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = VerificationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.signUp.setOnClickListener(t -> CompleteRegistrationActivity.start(this));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
