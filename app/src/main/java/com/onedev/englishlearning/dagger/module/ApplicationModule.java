
package com.onedev.englishlearning.dagger.module;

import android.app.Application;
import android.content.Context;

import com.onedev.englishlearning.BuildConfig;
import com.onedev.englishlearning.R;
import com.onedev.englishlearning.dagger.ApiInfo;
import com.onedev.englishlearning.dagger.ApplicationContext;
import com.onedev.englishlearning.dagger.DatabaseInfo;
import com.onedev.englishlearning.dagger.PreferenceInfo;
import com.onedev.englishlearning.data.AppDataManager;
import com.onedev.englishlearning.data.DataManager;
import com.onedev.englishlearning.data.db.AppDbHelper;
import com.onedev.englishlearning.data.db.DbHelper;
import com.onedev.englishlearning.data.network.ApiHeader;
import com.onedev.englishlearning.data.network.ApiHelper;
import com.onedev.englishlearning.data.network.AppApiHelper;
import com.onedev.englishlearning.data.prefs.AppPreferencesHelper;
import com.onedev.englishlearning.data.prefs.PreferencesHelper;
import com.onedev.englishlearning.utils.AppConstants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Credentials;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Nhat on 12/13/17.
 */


@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }

    @Provides
    @ApiInfo
    String provideApiKey() {
        return BuildConfig.API_KEY;
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(AppDbHelper appDbHelper) {
        return appDbHelper;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }

    @Provides
    @Singleton
    ApiHeader.ProtectedApiHeader provideProtectedApiHeader(@ApiInfo String apiKey,
                                                           PreferencesHelper preferencesHelper) {
        return new ApiHeader.ProtectedApiHeader(
                apiKey,
                preferencesHelper.getCurrentUserId(),
                Credentials.basic(BuildConfig.USERNAME, BuildConfig.PASSWORD));
    }

    @Provides
    @Singleton
    CalligraphyConfig provideCalligraphyDefaultConfig() {
        return new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/source-sans-pro/SourceSansPro-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();
    }
}
