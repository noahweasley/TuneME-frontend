package com.tune.tuneme.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.animation.AnimationUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tune.tuneme.BuildConfig;
import com.tune.tuneme.R;
import com.tune.tuneme.databinding.TuneSplashBinding;
import com.tune.tuneme.util.ImageUtils;

import java.util.Locale;

public class TuneSplashScreen extends AppCompatActivity {
    private TuneSplashBinding binding;
    private GestureDetector gestureDetector;
    private static final int TIMEOUT = 5000;
    private Handler handler;
    private Runnable startMain;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gestureDetector = new GestureDetector(this, new TapGestureDetector());
        binding = TuneSplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String versionName = String.format(Locale.US, "%s: %s", "Version: ", BuildConfig.VERSION_NAME);
        binding.versionName.setText(versionName);
        binding.tuneIcon.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in_2));
        binding.backgroundImage.setImageBitmap(
                ImageUtils.decodeSampledBitmapFromResource(getResources(),
                        R.drawable.splash_screen_wallpaper_cropped));

        // Display main screen after the splash screen
        (handler = new Handler(getMainLooper())).postDelayed(startMain = this::launch, TIMEOUT);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
        removeSystemCallback();
    }

    private void launch() {
        startActivity(new Intent(this, IntroPageActivity.class));
        finish();
    }

    private void removeSystemCallback() {
        if (handler != null)
            handler.removeCallbacks(startMain);
        handler = null;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    // intercept touch and provide skip splash screen on double-tap
    private class TapGestureDetector extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            removeSystemCallback();
            launch();
            return true;
        }
    }
}
