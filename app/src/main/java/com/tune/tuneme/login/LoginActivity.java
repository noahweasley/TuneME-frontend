package com.tune.tuneme.login;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tune.tuneme.R;
import com.tune.tuneme.databinding.LoginBinding;

public class LoginActivity extends AppCompatActivity {
    private LoginBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fade_in_1, R.anim.fade_out_1);
        binding = LoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
