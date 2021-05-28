package com.tune.tuneme.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tune.tuneme.R;
import com.tune.tuneme.data.Story;
import com.tune.tuneme.databinding.NewsFeedBinding;
import com.tune.tuneme.util.DummyGenerator;

import java.util.ArrayList;
import java.util.List;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

public class NewsFeedFragment extends Fragment {
    private NewsFeedBinding binding;
    private List<Story> storyList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return (binding = NewsFeedBinding.inflate(inflater)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        storyList = new ArrayList<>();
        storyList.add(null); // for the add story button

        storyList.addAll(DummyGenerator.getDummyStories(10));

        RecyclerView rV_storyList = binding.storyList;
        rV_storyList.setAdapter(new StoryAdapter());
        rV_storyList.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        // implement IOS style overscroll effect
        OverScrollDecoratorHelper
                .setUpOverScroll(rV_storyList, OverScrollDecoratorHelper.ORIENTATION_HORIZONTAL);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    // story adapter
    @SuppressWarnings("FieldCanBeLocal")
    private class StoryAdapter extends RecyclerView.Adapter<StoryRowHolder> {
        private final int VIEW_TYPE_STORY = 0; // stories layout
        private final int VIEW_TYPE_ADD = 1; // add story layout

        @NonNull
        @Override
        public StoryRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View rowView;
            // inflate row views at runtime, show add story icon when position is 0
            if (viewType == VIEW_TYPE_STORY)
                rowView = getLayoutInflater().inflate(R.layout.story_row, parent, false);
            else rowView = getLayoutInflater().inflate(R.layout.add_story, parent, false);
            return new StoryRowHolder(rowView);
        }

        @Override
        public void onBindViewHolder(@NonNull StoryRowHolder holder, int position) {
            holder.with(storyList).bindView();
        }

        @Override
        public int getItemCount() {
            return storyList.size();
        }

        @Override
        public int getItemViewType(int position) {
            return storyList.get(position) == null ? VIEW_TYPE_ADD : VIEW_TYPE_STORY;
        }
    }
}
