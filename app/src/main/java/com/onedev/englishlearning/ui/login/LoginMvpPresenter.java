
package com.onedev.englishlearning.ui.login;

import com.onedev.englishlearning.dagger.PerActivity;
import com.onedev.englishlearning.ui.base.MvpPresenter;

/**
 * Created by Nhat on 12/13/17.
 */


@PerActivity
public interface LoginMvpPresenter<V extends LoginBaseView> extends MvpPresenter<V> {

    void onServerLoginClick(String email, String password);

    void onGoogleLoginClick();

    void onFacebookLoginClick();

}
