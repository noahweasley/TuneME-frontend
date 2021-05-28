package com.tune.tuneme.main;

import android.net.Uri;
import android.text.Html;
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
    private final TextView tv_message, tv_updateTime;
    private Notification notification;

    public NotificationRowHolder(@NonNull View itemView) {
        super(itemView);
        tv_message = itemView.findViewById(R.id.message);
        tv_updateTime = itemView.findViewById(R.id.update_time);
        img_profileImage = itemView.findViewById(R.id.profile_image);
    }

    public void bindView() {
        // easier way to convert ordinary string to a spanned string with bold texts
        String completeMessage
                = "<b>" + notification.getUsername() + "</b> " + notification.getMessage();
        tv_updateTime.setText(notification.getUpdateTime());
        tv_message.setText(Html.fromHtml(completeMessage));

        // The user's profile image would be exactly 50dp x 50dp wide.
        // Because Picasso's resize(int, int) method accepts only integers and is in pixels and
        // and not dp, convert to pixels and provide an extra correction value of 10 dp to make up
        // for any truncation of some of the floating point bits that would have been provided by the
        // conversion from dp to px
        DisplayMetrics metrics = img_profileImage.getContext().getResources().getDisplayMetrics();
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, metrics);

        Uri profileImage = notification.getProfileImage();

        if (profileImage != null)
            Picasso.get().load(profileImage).centerCrop().resize(px, px).into(img_profileImage);
    }

    public NotificationRowHolder with(List<Notification> notificationList) {
        this.notification = notificationList.get(getAdapterPosition());
        return this;
    }
}
