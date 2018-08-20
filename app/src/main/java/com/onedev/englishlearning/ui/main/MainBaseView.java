
package com.onedev.englishlearning.ui.main;

import com.onedev.englishlearning.data.model.User;
import com.onedev.englishlearning.ui.base.BaseView;

/**
 * Created by Nhat on 12/13/17.
 */


public interface MainBaseView extends BaseView {

    void closeNavigationDrawer();

    void lockDrawer();

    void unlockDrawer();

    void onOpenFavoriteActivity();

    void onLoginRequires();

    void onGetUserInfoSuccess(User user);

    void onLoginSuccess(User user);

    void onLogoutClick();

    void onLogOutSuccess();
}
