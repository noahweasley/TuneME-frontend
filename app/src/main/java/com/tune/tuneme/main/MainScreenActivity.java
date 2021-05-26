package com.tune.tuneme.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.tune.tuneme.R;
import com.tune.tuneme.databinding.MainScreenBinding;

@SuppressWarnings("ConstantConditions")
public class MainScreenActivity extends AppCompatActivity
        implements View.OnClickListener, BottomNavigationView.OnNavigationItemSelectedListener {
    private MainScreenBinding binding;

    /**
     * Simple helper method to start this activity
     *
     * @param context the origin context
     */
    @SuppressWarnings("unused")
    public static void start(Context context) {
        Intent starter = new Intent(context, MainScreenActivity.class);
        starter.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = MainScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loadFragment(new DiscoverFragment()); // the default fragment

        binding.addPost.setOnClickListener(this);
        binding.bottomNavigation.setOnNavigationItemSelectedListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.add_post) {
            startActivity(new Intent(this, AddPostActivity.class));
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        TextView tv_title = binding.title;

        if (itemId == R.id.home) {
            tv_title.setText(R.string.discover);
            loadFragment(new DiscoverFragment());
        } else if (itemId == R.id.videos) {
            tv_title.setText(R.string.videos);
            loadFragment(new VideoFragment());
        } else if (itemId == R.id.chats) {
            tv_title.setText(R.string.chats);
            loadFragment(new ChatsFragment());
        } else if (itemId == R.id.products) {
            tv_title.setText(R.string.products);
            loadFragment(new ProductsFragment());
        } else {
            Toast.makeText(this, "More clicked", Toast.LENGTH_LONG).show();
        }

        return true;
    }

    // load requested fragment into view
    private void loadFragment(Fragment fragment) {
        FragmentManager mgr = getSupportFragmentManager();
        FragmentTransaction transaction = mgr.beginTransaction();
        transaction.replace(R.id.frame_content, fragment);
        transaction.commit();
    }
}