package com.tune.tuneme.login;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tune.tuneme.R;
import com.tune.tuneme.databinding.AddFollowersBinding;

public class AddFollowersPageActivity extends AppCompatActivity {
    private AddFollowersBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = AddFollowersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        RecyclerView rV_followersList = binding.followersList;
        rV_followersList.setLayoutManager
                (new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rV_followersList.setAdapter(new FollowerListAdapter());
        rV_followersList
                .addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    // List adapter for follower list
    private class FollowerListAdapter extends RecyclerView.Adapter<FollowersRowHolder> {

        @NonNull
        @Override
        public FollowersRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View rowView = getLayoutInflater().inflate(R.layout.followers_row, parent, false);
            return new FollowersRowHolder(rowView);
        }

        @Override
        public void onBindViewHolder(@NonNull FollowersRowHolder holder, int position) {
            // keep all the row view binding business logic in the row view holder
            holder.with(position).bindView();
        }

        @Override
        public int getItemCount() {
            return 15;
        }
    }
}
