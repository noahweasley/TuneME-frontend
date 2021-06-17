package com.tune.tuneme.main.video;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tune.tuneme.data.Video;

import java.util.List;

public class VideoListRowHolder extends RecyclerView.ViewHolder {
    private List<Video> videoList;

    public VideoListRowHolder(@NonNull View itemView) {
        super(itemView);
    }

    public VideoListRowHolder with(List<Video> videoList) {
        this.videoList = videoList;
        return this;
    }

    public void bindView() {

    }
}
