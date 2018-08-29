
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
import com.onedev.englishlearning.ui.category.CategoryBaseView;
import com.onedev.englishlearning.ui.category.CategoryMvpPresenter;
import com.onedev.englishlearning.ui.category.CategoryPresenter;
import com.onedev.englishlearning.ui.conversation.ConversationBaseView;
import com.onedev.englishlearning.ui.conversation.ConversationMvpPresenter;
import com.onedev.englishlearning.ui.conversation.ConversationPresenter;
import com.onedev.englishlearning.ui.detail.DetailBaseView;
import com.onedev.englishlearning.ui.detail.DetailMvpPresenter;
import com.onedev.englishlearning.ui.detail.DetailPresenter;
import com.onedev.englishlearning.ui.favorite.FavoriteBaseView;
import com.onedev.englishlearning.ui.favorite.FavoriteMvpPresenter;
import com.onedev.englishlearning.ui.favorite.FavoritePresenter;
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
import com.onedev.englishlearning.ui.splash.SplashBaseView;
import com.onedev.englishlearning.ui.splash.SplashMvpPresenter;
import com.onedev.englishlearning.ui.splash.SplashPresenter;
import com.onedev.englishlearning.ui.topic.TopicBaseView;
import com.onedev.englishlearning.ui.topic.TopicMvpPresenter;
import com.onedev.englishlearning.ui.topic.TopicPresenter;
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

    @Provides
    TopicMvpPresenter<TopicBaseView> provideTopicMvpPresenter(
            TopicPresenter<TopicBaseView> presenter) {
        return presenter;
    }

    @Provides
    CategoryMvpPresenter<CategoryBaseView> provideCategoryMvpPresenter(
            CategoryPresenter<CategoryBaseView> presenter) {
        return presenter;
    }

    @Provides
    FavoriteMvpPresenter<FavoriteBaseView> provideFavoriteMvpPresenter(
            FavoritePresenter<FavoriteBaseView> presenter) {
        return presenter;
    }

    @Provides
    DetailMvpPresenter<DetailBaseView> provideDetailMvpPresenter(
            DetailPresenter<DetailBaseView> presenter) {
        return presenter;
    }

    @Provides
    ConversationMvpPresenter<ConversationBaseView> provideConversationMvpPresenter(
            ConversationPresenter<ConversationBaseView> presenter) {
        return presenter;
    }
}
