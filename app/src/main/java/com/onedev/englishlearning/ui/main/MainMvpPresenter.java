
package com.onedev.englishlearning.ui.main;

import com.onedev.englishlearning.dagger.PerActivity;
import com.onedev.englishlearning.ui.base.MvpPresenter;

/**
 * Created by Nhat on 12/13/17.
 */


@PerActivity
public interface MainMvpPresenter<V extends MainBaseView> extends MvpPresenter<V> {

    void onDrawerOptionAboutClick();

    void onDrawerOptionLogoutClick();

    void onDrawerRateUsClick();

    void onViewInitialized();

    void onNavMenuCreated();
}
