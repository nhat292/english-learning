package com.onedev.englishlearning.ui.favorite;

import com.androidnetworking.error.ANError;
import com.onedev.englishlearning.App;
import com.onedev.englishlearning.data.DataManager;
import com.onedev.englishlearning.ui.base.BasePresenter;
import com.onedev.englishlearning.utils.rx.SchedulerProvider;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class FavoritePresenter<V extends FavoriteBaseView> extends BasePresenter<V> implements FavoriteMvpPresenter<V> {


    @Inject
    public FavoritePresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


    @Override
    public void getFavorites() {

        getMvpView().showLoading();
        Map<String, String> params = new HashMap<>();
        params.put("user_id", String.valueOf(getDataManager().getUser().getId()));

        getCompositeDisposable().add(getDataManager()
                .getFavorites(params)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(favoritesResponse -> {
                    if (!isViewAttached()) {
                        return;
                    }

                    getMvpView().hideLoading();
                    getMvpView().hideRefreshLoading();

                    getMvpView().onGetFavoritesSuccess(favoritesResponse);
                }, throwable -> {
                    if (!isViewAttached()) {
                        return;
                    }

                    getMvpView().hideLoading();
                    getMvpView().hideRefreshLoading();

                    // handle the login error here
                    if (throwable instanceof ANError) {
                        ANError anError = (ANError) throwable;
                        handleApiError(anError);
                    }
                }));
    }

    @Override
    public void removeItemFromFavorites(int categoryId, int dbNumber) {
        getMvpView().showLoading();
        Map<String, String> params = new HashMap<>();
        params.put("add_or_remove", "0");
        params.put("category_id", String.valueOf(categoryId));
        params.put("user_id", String.valueOf(getDataManager().getUser().getId()));
        params.put("db_number", String.valueOf(dbNumber));
        getCompositeDisposable().add(getDataManager()
                .addOrRemoveFavorite(params)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if (!isViewAttached()) {
                        return;
                    }

                    getMvpView().hideLoading();
                    getMvpView().onRemovedSuccessfully();

                }, throwable -> {
                    if (!isViewAttached()) {
                        return;
                    }

                    getMvpView().hideLoading();

                    // handle the login error here
                    if (throwable instanceof ANError) {
                        ANError anError = (ANError) throwable;
                        handleApiError(anError);
                    }
                }));
    }
}
