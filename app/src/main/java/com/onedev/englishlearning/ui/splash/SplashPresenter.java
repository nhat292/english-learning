
package com.onedev.englishlearning.ui.splash;

import android.os.Looper;

import com.onedev.englishlearning.data.DataManager;
import com.onedev.englishlearning.ui.base.BasePresenter;
import com.onedev.englishlearning.utils.AppConstants;
import com.onedev.englishlearning.utils.rx.SchedulerProvider;

import java.lang.ref.WeakReference;
import java.util.logging.Handler;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Nhat on 12/13/17.
 */


public class SplashPresenter<V extends SplashBaseView> extends BasePresenter<V>
        implements SplashMvpPresenter<V> {

    @Inject
    public SplashPresenter(DataManager dataManager,
                           SchedulerProvider schedulerProvider,
                           CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);
        handleNextActivity();
    }

    private void handleNextActivity() {
        android.os.Handler handler = new android.os.Handler(Looper.getMainLooper());
        handler.postDelayed(new HandlePassSplashScreen(this), AppConstants.SPLASH_TIMEOUT);
    }

    class HandlePassSplashScreen implements Runnable {

        private WeakReference<SplashPresenter> mSplashPresenter;

        public HandlePassSplashScreen(SplashPresenter splashPresenter) {
            mSplashPresenter = new WeakReference<>(splashPresenter);
        }

        @Override
        public void run() {
            SplashPresenter splashPresenter = mSplashPresenter.get();
            if (splashPresenter != null) {
                getMvpView().openMainActivity();
            }
        }
    }
}
