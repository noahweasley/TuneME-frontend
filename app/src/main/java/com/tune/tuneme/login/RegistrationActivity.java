package com.tune.tuneme.login;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tune.tuneme.databinding.RegistrationBinding;

public class RegistrationActivity extends AppCompatActivity {
    private RegistrationBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = RegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.gSignParent.setOnClickListener(
                v -> startActivity(new Intent(this, GoogleLoginActivity.class)));

        binding.signUp.setOnClickListener(v -> VerificationActivity.start(this));
        binding.cbxLcAgree.setOnCheckedChangeListener(
                (buttonView, isChecked) -> binding.signUp.setEnabled(isChecked));

        // Make Licence agreement statements and login text clickable links
        setLinkOnText(binding.txtLcAgree);
        setLinkOnText(binding.signNow);

    }

    private void detectLinkClick(SpannableStringBuilder strBuilder, final URLSpan span) {
        int start = strBuilder.getSpanStart(span);
        int end = strBuilder.getSpanEnd(span);
        int flags = strBuilder.getSpanFlags(span);
        ClickableSpan clickable = new ClickableSpan() {
            public void onClick(View view) {
                // Do something with links retrieved from span.getURL(), to handle link click...
                String clickedUrl = span.getURL();
                switch (clickedUrl) {
                    case "@login_page":
                        startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
                        break;
                    case "www.privacy-options.com":
                        String link1 = "www.privacy-options.com";
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(link1)));
                        break;
                    case "www.terms-and-conditions.com":
                        String link2 = "www.terms-and-conditions.com";
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(link2)));
                        break;
                    default:
                        Log.w(getClass().getSimpleName(), "No action for this");

                }
            }
        };
        strBuilder.setSpan(clickable, start, end, flags);
        strBuilder.removeSpan(span);
    }

    protected void setLinkOnText(TextView text) {
        CharSequence sequence = text.getText();
        SpannableStringBuilder strBuilder = new SpannableStringBuilder(sequence);
        URLSpan[] urls = strBuilder.getSpans(0, sequence.length(), URLSpan.class);
        for (URLSpan span : urls) {
            detectLinkClick(strBuilder, span);
        }
        text.setText(strBuilder);
        text.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}