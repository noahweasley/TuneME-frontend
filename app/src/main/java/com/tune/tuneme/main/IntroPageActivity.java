package com.tune.tuneme.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.tune.tuneme.R;
import com.tune.tuneme.databinding.IntroBinding;
import com.tune.tuneme.login.LoginActivity;

public class IntroPageActivity extends AppCompatActivity implements View.OnClickListener {
    private IntroBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fade_in_1, R.anim.fade_out_1);
        binding = IntroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewPager2 pager_intro = binding.introPager;
        pager_intro.setOffscreenPageLimit(3);
        pager_intro.setAdapter(new IntroPagerAdapter(this));

        // add scroll listener
        pager_intro.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                // hide or show corresponding views according to page position
                if (position == 2) {
                    binding.close.setVisibility(View.INVISIBLE);
                    binding.start.setVisibility(View.VISIBLE);
                } else {
                    binding.close.setVisibility(View.VISIBLE);
                    binding.start.setVisibility(View.GONE);
                }
            }

        });

        // navigate to login page
        binding.close.setOnClickListener(this);
        binding.start.setOnClickListener(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    private static class IntroPagerAdapter extends FragmentStateAdapter {

        public IntroPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return IntroScreenFragment.newInstance(position);
        }

        @Override
        public int getItemCount() {
            return 3;
        }
    }
}
