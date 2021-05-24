package com.tune.tuneme.discover;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tune.tuneme.data.Story;

import java.util.List;

public class StoryRowHolder extends RecyclerView.ViewHolder {

    private List<Story> storyList;

    public StoryRowHolder(@NonNull View itemView) {
        super(itemView);
    }

    public StoryRowHolder with(List<Story> storyList) {
        this.storyList = storyList;
        return this;
    }

    public void bindView() {
    }
}
