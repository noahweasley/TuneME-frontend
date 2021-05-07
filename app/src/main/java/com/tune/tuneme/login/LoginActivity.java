package com.tune.tuneme.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tune.tuneme.R;
import com.tune.tuneme.databinding.LoginBinding;

public class LoginActivity extends AppCompatActivity {
    private LoginBinding binding;

    public static void start(Context context) {
        Intent starter = new Intent(context, LoginActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = LoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Make Licence agreement statements and login text clickable links
        binding.txtLcAgree.setMovementMethod(LinkMovementMethod.getInstance());
        binding.signNow.setMovementMethod(LinkMovementMethod.getInstance());

        binding.gSignParent.setOnClickListener(v -> requestServerFeedback(binding));
        binding.signUp.setOnClickListener(v -> CompleteRegistrationActivity.start(this));
    }

    @SuppressWarnings("unused")
    private void requestServerFeedback(LoginBinding binding) {
        // if can send data, then ...
        GoogleLoginActivity.start(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}