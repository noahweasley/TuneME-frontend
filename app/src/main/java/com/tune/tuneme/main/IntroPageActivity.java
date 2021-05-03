package com.tune.tuneme.main;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tune.tuneme.databinding.IntroBinding;

public class IntroPageActivity extends AppCompatActivity {
    private IntroBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = IntroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
