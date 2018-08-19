package com.onedev.englishlearning.utils;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import java.util.Locale;

public final class Helper {

    private Helper() {

    }

    public static void openGoogleTranslate(Context context, String textToTranslate) {
        try {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT, textToTranslate);
            intent.putExtra("key_text_input", "");
            intent.putExtra("key_text_output", "");
            intent.putExtra("key_language_from", "en");
            intent.putExtra("key_language_to", Locale.getDefault().getLanguage());
            intent.putExtra("key_suggest_translation", "");
            intent.putExtra("key_from_floating_window", false);
            intent.setComponent(new ComponentName(
                    "com.google.android.apps.translate",
                    //Change is here
                    //"com.google.android.apps.translate.HomeActivity"));
                    "com.google.android.apps.translate.TranslateActivity"));
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            AppUtils.openPlayStoreForApp(context, "com.google.android.apps.translate");
        }
    }
}
