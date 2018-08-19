package com.onedev.englishlearning.ui.category;

import com.androidnetworking.error.ANError;
import com.onedev.englishlearning.App;
import com.onedev.englishlearning.data.DataManager;
import com.onedev.englishlearning.data.model.User;
import com.onedev.englishlearning.ui.base.BasePresenter;
import com.onedev.englishlearning.utils.rx.SchedulerProvider;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class CategoryPresenter<V extends CategoryBaseView> extends BasePresenter<V> implements CategoryMvpPresenter<V> {

    @Inject
    public CategoryPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);

    }

    @Override
    public void getCategories(int topicId, boolean isShowLoading) {
        if (isShowLoading) {
            getMvpView().showLoading();
        }
        Map<String, String> params = new HashMap<>();
        params.put("topic_id", String.valueOf(topicId));
        params.put("db_number", String.valueOf(App.getInstance().getmRuntimeObject().getDbNumber()));
        getCompositeDisposable().add(getDataManager()
                .getCategories(params)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if (!isViewAttached()) {
                        return;
                    }

                    getMvpView().hideLoading();
                    getMvpView().hideRefreshLoading();

                    getMvpView().onGetCategoriesSuccess(response);
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
    public void addOrRemoveFavorite(int addOrRemove, int categoryId) {

        User user = getDataManager().getUser();
        if (user == null) {
            getMvpView().onLoginRequires();
            return;
        }

        getMvpView().showLoading();
        Map<String, String> params = new HashMap<>();
        params.put("add_or_remove", String.valueOf(addOrRemove));
        params.put("category_id", String.valueOf(categoryId));
        params.put("user_id", String.valueOf(user.getId()));
        params.put("db_number", String.valueOf(App.getInstance().getmRuntimeObject().getDbNumber()));
        getCompositeDisposable().add(getDataManager()
                .addOrRemoveFavorite(params)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if (!isViewAttached()) {
                        return;
                    }

                    getMvpView().hideLoading();
                    getMvpView().onAddOrRemoveSuccess();

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
