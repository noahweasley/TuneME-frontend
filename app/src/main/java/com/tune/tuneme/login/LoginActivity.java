package com.tune.tuneme.login;

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
        binding.lcAgree.setMovementMethod(LinkMovementMethod.getInstance());
        binding.signNow.setMovementMethod(LinkMovementMethod.getInstance());

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
