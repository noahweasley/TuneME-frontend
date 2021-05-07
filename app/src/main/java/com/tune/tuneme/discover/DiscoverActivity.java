package com.tune.tuneme.discover;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.tune.tuneme.R;
import com.tune.tuneme.databinding.DiscoverBinding;

@SuppressWarnings("ConstantConditions")
public class DiscoverActivity extends AppCompatActivity {
    private DiscoverBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DiscoverBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle(R.string.discover);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}