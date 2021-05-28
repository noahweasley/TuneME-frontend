package com.tune.tuneme.main;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tune.tuneme.R;
import com.tune.tuneme.data.Notification;
import com.tune.tuneme.databinding.NotificationBinding;
import com.tune.tuneme.util.DummyGenerator;

import java.util.ArrayList;
import java.util.List;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

public class NotificationsActivity extends AppCompatActivity {
    private NotificationBinding binding;
    private final List<Notification> notificationList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = NotificationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        notificationList.addAll(DummyGenerator.getNotifications(10));

        RecyclerView rv_notificationList = binding.notificationList;
        rv_notificationList.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rv_notificationList.setAdapter(new NotificationsAdapter());
        // set up IOS style overscroll effect
        OverScrollDecoratorHelper.setUpOverScroll(rv_notificationList,
                                                  OverScrollDecoratorHelper.ORIENTATION_VERTICAL);

        binding.exit.setOnClickListener(v -> onBackPressed());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    private class NotificationsAdapter extends RecyclerView.Adapter<NotificationRowHolder> {

        @NonNull
        @Override
        public NotificationRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.notification_row, parent, false);
            return new NotificationRowHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull NotificationRowHolder holder, int position) {
            holder.with(notificationList).bindView();
        }

        @Override
        public int getItemCount() {
            return notificationList.size();
        }
    }
}
