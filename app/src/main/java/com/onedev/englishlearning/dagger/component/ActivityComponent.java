
package com.onedev.englishlearning.dagger.component;

import com.onedev.englishlearning.dagger.PerActivity;
import com.onedev.englishlearning.dagger.module.ActivityModule;
import com.onedev.englishlearning.ui.about.AboutFragment;
import com.onedev.englishlearning.ui.feed.FeedActivity;
import com.onedev.englishlearning.ui.feed.blogs.BlogFragment;
import com.onedev.englishlearning.ui.feed.opensource.OpenSourceFragment;
import com.onedev.englishlearning.ui.login.LoginActivity;
import com.onedev.englishlearning.ui.main.MainActivity;
import com.onedev.englishlearning.ui.main.rating.RateUsDialog;
import com.onedev.englishlearning.ui.splash.SplashActivity;

import dagger.Component;

/**
 * Created by Nhat on 12/13/17.
 */


@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity activity);

    void inject(LoginActivity activity);

    void inject(SplashActivity activity);

    void inject(FeedActivity activity);

    void inject(AboutFragment fragment);

    void inject(OpenSourceFragment fragment);

    void inject(BlogFragment fragment);

    void inject(RateUsDialog dialog);

}