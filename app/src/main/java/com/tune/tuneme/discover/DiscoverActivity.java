package com.tune.tuneme.discover;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.tune.tuneme.R;
import com.tune.tuneme.databinding.DiscoverBinding;

@SuppressWarnings("ConstantConditions")
public class DiscoverActivity extends AppCompatActivity {
    private DiscoverBinding binding;

    /**
     * Simple helper method to start this activity
     * @param context the origin context
     */
   public static void start(Context context) {
       Intent starter = new Intent(context, DiscoverActivity.class);
       context.startActivity(starter);
   }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DiscoverBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = binding.toolbar;
//        toolbar.setTitleTextAppearance(this, R.style.Toolbar_Title_Large);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.discover);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}