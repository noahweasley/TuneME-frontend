package com.tune.tuneme.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tune.tuneme.databinding.LoginBinding;

public class LoginActivity extends AppCompatActivity {
    private LoginBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = LoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Make Licence agreement statements and login text clickable links
        binding.txtLcAgree.setMovementMethod(LinkMovementMethod.getInstance());
        binding.signNow.setMovementMethod(LinkMovementMethod.getInstance());

        binding.gSignParent.setOnClickListener(v ->
                startActivity(new Intent(this, GoogleLoginActivity.class)));

        binding.signUp.setOnClickListener(v -> CompleteRegistrationActivity.start(this));
        binding.cbxLcAgree.setOnCheckedChangeListener(
                (buttonView, isChecked) -> binding.signUp.setEnabled(isChecked));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}