package com.tune.tuneme.main.video;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.tune.tuneme.R;
import com.tune.tuneme.databinding.VideosBinding;

/**
 * Video Section UI
 */
public class VideoFragment extends Fragment {
    private VideosBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return (binding = VideosBinding.inflate(getLayoutInflater())).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        TabLayout tbl_videoTabs = binding.videoTab;
        ViewPager2 pg_videoPager = binding.videoPager;
        pg_videoPager.setAdapter(new VideoStateAdapter(this));
        // assign the text to the tabs based on the current selected page
        String videos = getString(R.string.video_all_caps);
        String movies = getString(R.string.movie_all_caps);
        new TabLayoutMediator(tbl_videoTabs, pg_videoPager,
                              (tab, pos) -> tab.setText(pos == 0 ? videos : movies)).attach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    // the UI of the individual video list pages
    private static class VideoStateAdapter extends FragmentStateAdapter {

        public VideoStateAdapter(@NonNull Fragment fragment) {
            super(fragment);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return VideoListFragment.newInstance(position);
        }

        @Override
        public int getItemCount() {
            return 2; // number of pages
        }
    }
}