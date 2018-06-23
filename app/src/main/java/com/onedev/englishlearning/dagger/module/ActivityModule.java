
package com.onedev.englishlearning.dagger.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.onedev.englishlearning.dagger.ActivityContext;
import com.onedev.englishlearning.dagger.PerActivity;
import com.onedev.englishlearning.data.network.model.BlogResponse;
import com.onedev.englishlearning.data.network.model.OpenSourceResponse;
import com.onedev.englishlearning.ui.about.AboutBaseView;
import com.onedev.englishlearning.ui.about.AboutMvpPresenter;
import com.onedev.englishlearning.ui.about.AboutPresenter;
import com.onedev.englishlearning.ui.feed.FeedBaseView;
import com.onedev.englishlearning.ui.feed.FeedMvpPresenter;
import com.onedev.englishlearning.ui.feed.FeedPagerAdapter;
import com.onedev.englishlearning.ui.feed.FeedPresenter;
import com.onedev.englishlearning.ui.feed.blogs.BlogAdapter;
import com.onedev.englishlearning.ui.feed.blogs.BlogBaseView;
import com.onedev.englishlearning.ui.feed.blogs.BlogMvpPresenter;
import com.onedev.englishlearning.ui.feed.blogs.BlogPresenter;
import com.onedev.englishlearning.ui.feed.opensource.OpenSourceAdapter;
import com.onedev.englishlearning.ui.feed.opensource.OpenSourceBaseView;
import com.onedev.englishlearning.ui.feed.opensource.OpenSourceMvpPresenter;
import com.onedev.englishlearning.ui.feed.opensource.OpenSourcePresenter;
import com.onedev.englishlearning.ui.login.LoginBaseView;
import com.onedev.englishlearning.ui.login.LoginMvpPresenter;
import com.onedev.englishlearning.ui.login.LoginPresenter;
import com.onedev.englishlearning.ui.main.MainBaseView;
import com.onedev.englishlearning.ui.main.MainMvpPresenter;
import com.onedev.englishlearning.ui.main.MainPresenter;
import com.onedev.englishlearning.ui.main.rating.RatingDialogBaseView;
import com.onedev.englishlearning.ui.main.rating.RatingDialogMvpPresenter;
import com.onedev.englishlearning.ui.main.rating.RatingDialogPresenter;
import com.onedev.englishlearning.ui.splash.SplashBaseView;
import com.onedev.englishlearning.ui.splash.SplashMvpPresenter;
import com.onedev.englishlearning.ui.splash.SplashPresenter;
import com.onedev.englishlearning.utils.rx.AppSchedulerProvider;
import com.onedev.englishlearning.utils.rx.SchedulerProvider;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Nhat on 12/13/17.
 */


@Module
public class ActivityModule {

    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @PerActivity
    SplashMvpPresenter<SplashBaseView> provideSplashPresenter(
            SplashPresenter<SplashBaseView> presenter) {
        return presenter;
    }

    @Provides
    AboutMvpPresenter<AboutBaseView> provideAboutPresenter(
            AboutPresenter<AboutBaseView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    LoginMvpPresenter<LoginBaseView> provideLoginPresenter(
            LoginPresenter<LoginBaseView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    MainMvpPresenter<MainBaseView> provideMainPresenter(
            MainPresenter<MainBaseView> presenter) {
        return presenter;
    }

    @Provides
    RatingDialogMvpPresenter<RatingDialogBaseView> provideRateUsPresenter(
            RatingDialogPresenter<RatingDialogBaseView> presenter) {
        return presenter;
    }

    @Provides
    FeedMvpPresenter<FeedBaseView> provideFeedPresenter(
            FeedPresenter<FeedBaseView> presenter) {
        return presenter;
    }

    @Provides
    OpenSourceMvpPresenter<OpenSourceBaseView> provideOpenSourcePresenter(
            OpenSourcePresenter<OpenSourceBaseView> presenter) {
        return presenter;
    }

    @Provides
    BlogMvpPresenter<BlogBaseView> provideBlogMvpPresenter(
            BlogPresenter<BlogBaseView> presenter) {
        return presenter;
    }

    @Provides
    FeedPagerAdapter provideFeedPagerAdapter(AppCompatActivity activity) {
        return new FeedPagerAdapter(activity.getSupportFragmentManager());
    }

    @Provides
    OpenSourceAdapter provideOpenSourceAdapter() {
        return new OpenSourceAdapter(new ArrayList<OpenSourceResponse.Repo>());
    }

    @Provides
    BlogAdapter provideBlogAdapter() {
        return new BlogAdapter(new ArrayList<BlogResponse.Blog>());
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(AppCompatActivity activity) {
        return new LinearLayoutManager(activity);
    }
}
