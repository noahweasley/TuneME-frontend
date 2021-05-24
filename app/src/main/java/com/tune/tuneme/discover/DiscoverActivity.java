package com.tune.tuneme.discover;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tune.tuneme.R;
import com.tune.tuneme.data.Story;
import com.tune.tuneme.databinding.DiscoverBinding;
import com.tune.tuneme.util.DummyGenerator;

import java.util.ArrayList;
import java.util.List;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

@SuppressWarnings("ConstantConditions")
public class DiscoverActivity extends AppCompatActivity {
    private DiscoverBinding binding;
    private List<Story> storyList;

    /**
     * Simple helper method to start this activity
     *
     * @param context the origin context
     */
    public static void start(Context context) {
        Intent starter = new Intent(context, DiscoverActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        storyList = new ArrayList<>();
        storyList.add(null); // for the add story button

        storyList.addAll(DummyGenerator.getDummyStory(10));

        binding = DiscoverBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        RecyclerView rV_storyList = binding.storyList;
        rV_storyList.setAdapter(new StoryAdapter());
        rV_storyList.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        // implement IOS style overscroll effect
        OverScrollDecoratorHelper
                .setUpOverScroll(rV_storyList, OverScrollDecoratorHelper.ORIENTATION_HORIZONTAL);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
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