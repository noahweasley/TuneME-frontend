package com.tune.tuneme.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tune.tuneme.R;
import com.tune.tuneme.databinding.CompleteRegistrationBinding;

public class CompleteRegistrationActivity extends AppCompatActivity {
    private CompleteRegistrationBinding binding;

    /**
     * Convenience method to start this activity and pass other details to it
     * @param context the starter
     */
    public static void start(Context context) {
        Intent starter = new Intent(context, CompleteRegistrationActivity.class);
        context.startActivity(starter);

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fade_in_1, R.anim.fade_out_1);
        binding = CompleteRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // populate listCountries, with list of countries for auto-completion
        String[] countryArray = getResources().getStringArray(R.array.countries_array);
        ArrayAdapter<String> adapter
                = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, countryArray);

        binding.listCountries.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in_1, R.anim.fade_out_1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}