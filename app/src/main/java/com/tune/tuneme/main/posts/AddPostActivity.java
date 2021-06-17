package com.tune.tuneme.main.posts;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.tune.tuneme.databinding.AddPostBinding;

public class AddPostActivity extends AppCompatActivity {
    private AddPostBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = AddPostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
