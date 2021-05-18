package com.tune.tuneme.login;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.tune.tuneme.R;
import com.tune.tuneme.custom.DateChooserEditText;
import com.tune.tuneme.databinding.CompleteRegistrationBinding;

import java.util.Calendar;
import java.util.Locale;

public class CompleteRegistrationActivity extends AppCompatActivity {
    private CompleteRegistrationBinding binding;

    /**
     * Convenience method to start this activity and pass other details to it
     *
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
                =
                new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, countryArray);

        binding.listCountries.setAdapter(adapter);
        // Actions when calendar icon is clicked on the date of birth text field
        Calendar calendar = Calendar.getInstance();
        // final actions for date selection
        DatePickerDialog.OnDateSetListener listener =
                (view, year, month, dayOfMonth) -> {
                    // calendar month starts from 0. 0 is not really a month to the user. Add 1
                    String dateFormat
                            =
                            String.format(Locale.US, "%02d-%02d-%02d", dayOfMonth, month + 1, year);
                    // Replace the whole string, because of errors
                    binding.datePicker.setText(dateFormat);
                };

        // listen for right | end drawable clicks
        binding.datePicker.setOnDrawableClickListener(
                (target) -> new DatePickerDialog(this,
                                                 listener,
                                                 calendar.get(Calendar.YEAR),
                                                 calendar.get(Calendar.MONTH),
                                                 calendar.get(Calendar.DAY_OF_MONTH))
                        .show());

        binding.signUp.setOnClickListener(v -> validateFormData(binding));
    }

    // validate date input
    private void validateFormData(CompleteRegistrationBinding binding) {
        String datePattern = "^((0[1-9]|[12][0-9]|3[01])[-/](0[1-9]|1[012])[-/](\\d){4})$";

        AutoCompleteTextView atv_countryList = binding.listCountries;
        boolean isCountryEmpty = TextUtils.isEmpty(atv_countryList.getText());

        DateChooserEditText datePicker = binding.datePicker;
        boolean dateMatches = datePicker.getText().toString().matches(datePattern);

        if (dateMatches && !isCountryEmpty) {
            startActivity(new Intent(this, AddFollowersPageActivity.class));
        } else {
            // because TextInputLayout has child FrameLayout that is the TextInputEditText's parent
            // view, calling getParent() directly on the TextInputEditText will return the FrameLayout
            // and not the TextInputLayout
            if (!dateMatches) {
                ViewGroup container = (ViewGroup) datePicker.getParent();
                ((TextInputLayout) container.getParent()).setError("dd-mm-yyyy or dd/mm-yyy");
            }

            if (isCountryEmpty) {
                ViewGroup container = (ViewGroup) atv_countryList.getParent();
                ((TextInputLayout) container.getParent()).setError("Field can't be empty");
            }
        }
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