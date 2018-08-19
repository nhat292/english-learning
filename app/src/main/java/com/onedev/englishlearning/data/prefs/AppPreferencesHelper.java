
package com.onedev.englishlearning.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.onedev.englishlearning.dagger.ApplicationContext;
import com.onedev.englishlearning.dagger.PreferenceInfo;
import com.onedev.englishlearning.data.model.User;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Nhat on 12/13/17.
 */


@Singleton
public class AppPreferencesHelper implements PreferencesHelper {

    private static final String PREF_KEY_CURRENT_USER = "PREF_KEY_CURRENT_USER";

    private final SharedPreferences mPrefs;

    @Inject
    public AppPreferencesHelper(@ApplicationContext Context context,
                                @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }

    @Override
    public void setUser(User user) {
        if (user != null) {
            Gson gson = new Gson();
            mPrefs.edit().putString(PREF_KEY_CURRENT_USER, gson.toJson(user)).apply();
        } else {
            mPrefs.edit().remove(PREF_KEY_CURRENT_USER).apply();
        }
    }

    @Override
    public User getUser() {
        String userJson = mPrefs.getString(PREF_KEY_CURRENT_USER, null);
        if (userJson != null) {
            return new Gson().fromJson(userJson, User.class);
        }
        return null;
    }
}
