package com.tune.tuneme.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tune.tuneme.databinding.GoogleLoginBinding;

public class GoogleLoginActivity extends AppCompatActivity {
    private GoogleLoginBinding binding;

    public static void start(Context context) {
        Intent starter = new Intent(context, GoogleLoginActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = GoogleLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.signUp.setOnClickListener(v -> requestServerFeedback(binding));
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
