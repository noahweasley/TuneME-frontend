package com.tune.tuneme.main.video;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tune.tuneme.R;
import com.tune.tuneme.data.Video;
import com.tune.tuneme.databinding.VideoSectionList1Binding;

import java.util.ArrayList;
import java.util.List;

public class VideoListFragment extends Fragment {
    public static final String ARG_PAGE_POSITION = "Video list page position";
    private VideoSectionList1Binding binding1;
    private final List<Video> videoList = new ArrayList<>();

    /**
     * Simple factory method used to keep the logic of this fragment, contained within itself
     *
     * @param position the position of this fragment in a view pager
     * @return a new instance of this fragment with it's page position saved
     */
    public static VideoListFragment newInstance(int position) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE_POSITION, position);
        // the new instance of this fragment with page position saved
        VideoListFragment fragment = new VideoListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return (binding1 = VideoSectionList1Binding.inflate(inflater)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        RecyclerView rv_videoList = binding1.videoList;
        int spanCount = R.integer.grid_span_count; // span count according to screen width
        rv_videoList.setLayoutManager(new GridLayoutManager(getContext(), spanCount));
        rv_videoList.setAdapter(new VideoListAdapter());
    }

    @Override
    public void onDestroyView() {
        binding1 = null;
        super.onDestroyView();
    }

    // adapter for list of videos
    private class VideoListAdapter extends RecyclerView.Adapter<VideoListRowHolder> {

        @NonNull
        @Override
        public VideoListRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            // parent would probably have a context in which it reside, and
            View rootView = getLayoutInflater().inflate(R.layout.video_list_row, parent, false);
            return new VideoListRowHolder(rootView);
        }

        @Override
        public void onBindViewHolder(@NonNull VideoListRowHolder holder, int position) {
            holder.with(videoList).bindView();
        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }
}
