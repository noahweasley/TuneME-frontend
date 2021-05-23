package com.tune.tuneme.login;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.tune.tuneme.databinding.RecoveryBinding;

public class RecoveryActivity extends AppCompatActivity {
    private RecoveryBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = RecoveryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.exit.setOnClickListener(v -> onBackPressed());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
