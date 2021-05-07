package com.tune.tuneme.intro;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tune.tuneme.R;
import com.tune.tuneme.databinding.IntroPageBinding;
import com.tune.tuneme.util.ImageUtils;

public class IntroScreenFragment extends Fragment {
    private static final String ARG_POSITION = "page position";
    private IntroPageBinding binding;

    public static IntroScreenFragment newInstance(int position) {
        Bundle args = new Bundle();
        args.putInt(ARG_POSITION, position);
        IntroScreenFragment fragment = new IntroScreenFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return (binding = IntroPageBinding.inflate(getLayoutInflater())).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int position = getArguments().getInt(ARG_POSITION);

        ImageView img_intro = binding.introImage;
        TextView tv_title = binding.title;
        TextView tv_subTitle = binding.subtitle;

        if (position == 0) {
            img_intro.setImageBitmap(
                    ImageUtils.decodeSampledBitmapFromResource(getResources(), R.drawable.slide_img_1));
            tv_title.setText(R.string.discover);
            tv_subTitle.setText(R.string.subtitle_0);
        } else if (position == 1) {
            img_intro.setImageBitmap(
                    ImageUtils.decodeSampledBitmapFromResource(getResources(), R.drawable.slide_img_2));
            tv_title.setText(R.string.title_1);
            tv_subTitle.setText(R.string.subtitle_1);
        } else {
            img_intro.setImageBitmap(
                    ImageUtils.decodeSampledBitmapFromResource(getResources(), R.drawable.slide_img_));
            tv_title.setText(R.string.title_2);
            tv_subTitle.setText(R.string.subtitle_2);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
