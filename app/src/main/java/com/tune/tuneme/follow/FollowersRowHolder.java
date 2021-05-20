package com.tune.tuneme.follow;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.tune.tuneme.R;
import com.tune.tuneme.data.Followers;

import java.util.List;

@SuppressWarnings("all")
public class FollowersRowHolder extends RecyclerView.ViewHolder {
    private ImageView img_profilePhoto;
    private TextView tv_username, tv_profileName;
    private AddFollowersPageActivity.EndlessFollowersAdapter adapter;
    private Followers follower;
    private boolean isLast;
    private boolean following;
    private int viewType;
    private View divider;
    private Button btn_listAction;

    public FollowersRowHolder(@NonNull View itemView, int viewType) {
        super(itemView);
        this.viewType = viewType;
        if (viewType == 0) {
            img_profilePhoto = itemView.findViewById(R.id.profile_image);
            tv_profileName = itemView.findViewById(R.id.profile_name);
            tv_username = itemView.findViewById(R.id.user_name);
            divider = itemView.findViewById(R.id.divider);
            btn_listAction = itemView.findViewById(R.id.list_action);
            btn_listAction.setActivated(true);

            btn_listAction.setOnClickListener(v -> {
                following = !following;
                sendFollowUserRequest();
                btn_listAction.setText(following ? R.string.following : R.string.add);
                // just a logic used to set the list action button to following action button
                btn_listAction.setActivated(!following);
                adapter.onFollow(getAdapterPosition(), following);
            });
        }
    }

    @SuppressWarnings("unused")
    private void sendFollowUserRequest() {
    }

    public FollowersRowHolder with(
            AddFollowersPageActivity.EndlessFollowersAdapter endlessFollowersAdapter,
            List<Followers> followersList,
            boolean isLast) {
        adapter = endlessFollowersAdapter;
        this.follower = followersList.get(getAdapterPosition());
        this.isLast = isLast;
        return this;
    }

    public void bindView() {
        if (this.viewType == 0) {
            // just a logic used to set the list action button to following action button
            following = adapter.isFollowing(getAdapterPosition());
            btn_listAction.setActivated(!following);
            btn_listAction.setText(following ? R.string.following : R.string.add);
            // remove bottom separator if last visible
            divider.setVisibility(isLast ? View.INVISIBLE : View.VISIBLE);

            tv_username.setText(follower.getUserName());
            tv_profileName.setText(follower.getProfileName());
            if (follower.getProfilePicture() != null) {
                Picasso.get().load(follower.getProfilePicture()).noFade().into(img_profilePhoto);
            }
        }

    }
}
