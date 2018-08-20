
package com.onedev.englishlearning.ui.main;

import com.facebook.CallbackManager;
import com.onedev.englishlearning.dagger.PerActivity;
import com.onedev.englishlearning.ui.base.MvpPresenter;

/**
 * Created by Nhat on 12/13/17.
 */


@PerActivity
public interface MainMvpPresenter<V extends MainBaseView> extends MvpPresenter<V> {

    void onDrawerOptionAboutClick();

    void onDrawerOptionLogInClick();

    void onDrawerRateUsClick();

    void onViewInitialized();

    void onNavMenuCreated();

    void onFavoritesClick();

    void getUserInfo();

    void initFacebookLogin(CallbackManager callbackManager);

    void loginLogoutClick(MainActivity activity);

    void logout();
}
