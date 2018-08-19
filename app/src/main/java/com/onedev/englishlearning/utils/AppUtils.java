
package com.onedev.englishlearning.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.onedev.englishlearning.R;


/**
 * Created by Nhat on 12/13/17.
 */


public final class AppUtils {

    private AppUtils() {
        // This class is not publicly instantiable
    }

    public static void openPlayStoreForApp(Context context, String packageName) {
        final String appPackageName = packageName; //context.getPackageName();
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(context
                            .getResources()
                            .getString(R.string.app_market_link) + appPackageName)));
        } catch (android.content.ActivityNotFoundException e) {
            context.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(context
                            .getResources()
                            .getString(R.string.app_google_play_store_link) + appPackageName)));
        }
    }


}
