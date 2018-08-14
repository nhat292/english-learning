
package com.onedev.englishlearning.ui.main;

import com.onedev.englishlearning.ui.base.BaseView;

/**
 * Created by Nhat on 12/13/17.
 */


public interface MainBaseView extends BaseView {

    void openLoginActivity();

    void showAboutFragment();

    void updateAppVersion();

    void showRateUsDialog();

    void closeNavigationDrawer();

    void lockDrawer();

    void unlockDrawer();
}
