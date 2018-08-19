
package com.onedev.englishlearning.ui.main;

import com.onedev.englishlearning.data.DataManager;
import com.onedev.englishlearning.ui.base.BasePresenter;
import com.onedev.englishlearning.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;


/**
 * Created by Nhat on 12/13/17.
 */


public class MainPresenter<V extends MainBaseView> extends BasePresenter<V>
        implements MainMvpPresenter<V> {

    private static final String TAG = "MainPresenter";

    @Inject
    public MainPresenter(DataManager dataManager,
                         SchedulerProvider schedulerProvider,
                         CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onDrawerOptionAboutClick() {
        getMvpView().closeNavigationDrawer();
    }

    @Override
    public void onDrawerOptionLogInClick() {
    }

    @Override
    public void onViewInitialized() {

    }


    @Override
    public void onNavMenuCreated() {

    }

    @Override
    public void onDrawerRateUsClick() {
        getMvpView().closeNavigationDrawer();
    }

    @Override
    public void onFavoritesClick() {
        if(getDataManager().getUser() != null) {
            getMvpView().onOpenFavoriteActivity();
        } else {
            getMvpView().onLoginRequires();
        }
    }

    @Override
    public void getUserInfo() {
        getMvpView().onGetUserInfoSuccess(getDataManager().getUser());
    }
}
