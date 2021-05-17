package com.tune.tuneme.login;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tune.tuneme.R;
import com.tune.tuneme.data.Followers;
import com.tune.tuneme.databinding.AddFollowersBinding;
import com.tune.tuneme.util.DummyFollowers;

import java.util.List;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

public class AddFollowersPageActivity extends AppCompatActivity {
    private AddFollowersBinding binding;
    private List<Followers> followersList;
    private RecyclerView.Adapter<?> endlessFollowersAdapter;
    private boolean isLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // use dummy followers for now
        followersList = DummyFollowers.initialize(20);
        super.onCreate(savedInstanceState);
        binding = AddFollowersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        RecyclerView rV_followersList = binding.followersList;
        rV_followersList.setLayoutManager
                (new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rV_followersList.setAdapter((endlessFollowersAdapter = new EndlessFollowersAdapter()));
        initScrollDetectionFor(rV_followersList); // start end of scroll detection
        // remove default over-scroll glow effect and use IOS style
        OverScrollDecoratorHelper
                .setUpOverScroll(rV_followersList, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);
    }

    // starts end of scroll detection
    private void initScrollDetectionFor(RecyclerView rV_followersList) {
        // listener
        rV_followersList.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                // get more followers when second-to-last follower is visible
                LinearLayoutManager lmgr = (LinearLayoutManager) recyclerView.getLayoutManager();
                int lastVisibleItemPos = lmgr.findLastCompletelyVisibleItemPosition();
                int listSize = followersList.size() - 1;
                if (!isLoading && lmgr != null && lastVisibleItemPos == listSize) {
                    isLoading = getMoreFollowers();
                }
            }

            // gets more followers
            private boolean getMoreFollowers() {
                followersList.add(null);
                int lastItem = followersList.size() - 1;
                endlessFollowersAdapter.notifyItemInserted(lastItem); // add loader view
                Handler handler = new Handler();
                handler.postDelayed(() -> {
                    int scrollPos = followersList.size();
                    followersList.remove(lastItem);
                    endlessFollowersAdapter.notifyItemRemoved(scrollPos);
                    followersList.addAll(DummyFollowers.initialize(5));
                    endlessFollowersAdapter.notifyDataSetChanged();
                    isLoading = false;
                }, 5000);
                return true;
            }

        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    // List adapter for follower list
    @SuppressWarnings("FieldCanBeLocal")
    private class EndlessFollowersAdapter extends RecyclerView.Adapter<FollowersRowHolder> {
        private final int VIEW_TYPE_ITEM = 0;
        private final int VIEW_TYPE_LOADING = 1;

        @NonNull
        @Override
        public FollowersRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View rowView;
            // inflate row views at runtime, show progress bar when more followers are still loading
            if (viewType == VIEW_TYPE_ITEM)
                rowView = getLayoutInflater().inflate(R.layout.followers_row, parent, false);
            else rowView = getLayoutInflater().inflate(R.layout.item_loading, parent, false);
            return new FollowersRowHolder(rowView, viewType);
        }

        @Override
        public void onBindViewHolder(@NonNull FollowersRowHolder holder, int position) {
            // keep all the row view binding business logic in the row view holder
            holder.with(followersList, position == followersList.size() - 1).bindView();
        }

        @Override
        public int getItemCount() {
            return followersList.size();
        }

        @Override
        public int getItemViewType(int position) {
            return followersList.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
        }
    }
}
