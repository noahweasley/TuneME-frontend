package com.tune.tuneme.main;

import android.net.Uri;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.tune.tuneme.R;
import com.tune.tuneme.data.Notification;

import java.util.List;

public class NotificationRowHolder extends RecyclerView.ViewHolder {
    private final ImageView img_profileImage;
    private final TextView tv_userName, tv_message, tv_updateTime;
    private Notification notification;

    public NotificationRowHolder(@NonNull View itemView) {
        super(itemView);
        tv_userName = itemView.findViewById(R.id.user_name);
        tv_message = itemView.findViewById(R.id.message);
        tv_updateTime = itemView.findViewById(R.id.update_time);
        img_profileImage = itemView.findViewById(R.id.profile_image);
    }

    public void bindView() {
        tv_userName.setText(notification.getUsername());
        tv_updateTime.setText(notification.getUpdateTime());
        tv_message.setText(notification.getMessage());

        DisplayMetrics metrics = img_profileImage.getContext().getResources().getDisplayMetrics();
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, metrics);

        Uri profileImage = notification.getProfileImage();

        if (profileImage != null)
            Picasso.get().
                    load(profileImage)
                   .centerCrop()
                   .resize(px, px)
                   .into(img_profileImage);
    }

    public NotificationRowHolder with(List<Notification> notificationList) {
        this.notification = notificationList.get(getAdapterPosition());
        return this;
    }
}
