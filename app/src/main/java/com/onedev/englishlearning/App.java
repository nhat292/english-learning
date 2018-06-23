
package com.onedev.englishlearning;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.interceptors.HttpLoggingInterceptor.Level;
import com.onedev.englishlearning.dagger.component.ApplicationComponent;
import com.onedev.englishlearning.dagger.component.DaggerApplicationComponent;
import com.onedev.englishlearning.dagger.module.ApplicationModule;
import com.onedev.englishlearning.data.DataManager;
import com.onedev.englishlearning.utils.AppLogger;

import javax.inject.Inject;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


/**
 * Created by Nhat on 12/13/17.
 */


public class App extends Application implements Application.ActivityLifecycleCallbacks {

    private static final String TAG = "App";

    @Inject
    DataManager mDataManager;

    @Inject
    CalligraphyConfig mCalligraphyConfig;

    private ApplicationComponent mApplicationComponent;

    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();

        mApplicationComponent.inject(this);

        AppLogger.init();

        AndroidNetworking.initialize(getApplicationContext());
        if (BuildConfig.DEBUG) {
            AndroidNetworking.enableLogging(Level.BODY);
        }

        CalligraphyConfig.initDefault(mCalligraphyConfig);
    }

    public static App getInstance() {
        return instance;
    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }


    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
        AppLogger.d(TAG, activity.getClass().getSimpleName() + ": created");
    }

    @Override
    public void onActivityStarted(Activity activity) {
        AppLogger.d(TAG, activity.getClass().getSimpleName() + ": started");
    }

    @Override
    public void onActivityResumed(Activity activity) {
        AppLogger.d(TAG, activity.getClass().getSimpleName() + ": resumed");
    }

    @Override
    public void onActivityPaused(Activity activity) {
        AppLogger.d(TAG, activity.getClass().getSimpleName() + ": paused");
    }

    @Override
    public void onActivityStopped(Activity activity) {
        AppLogger.d(TAG, activity.getClass().getSimpleName() + ": stopped");
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        AppLogger.d(TAG, activity.getClass().getSimpleName() + ": save instance state");
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        AppLogger.d(TAG, activity.getClass().getSimpleName() + ": destroyed");
    }
}
