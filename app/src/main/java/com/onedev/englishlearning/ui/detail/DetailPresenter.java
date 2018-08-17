package com.onedev.englishlearning.ui.detail;

import com.androidnetworking.error.ANError;
import com.onedev.englishlearning.App;
import com.onedev.englishlearning.data.DataManager;
import com.onedev.englishlearning.ui.base.BasePresenter;
import com.onedev.englishlearning.utils.rx.SchedulerProvider;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class DetailPresenter<V extends DetailBaseView> extends BasePresenter<V> implements DetailMvpPresenter<V> {


    @Inject
    public DetailPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void getSentences(int categoryId) {
        getMvpView().showLoading();
        Map<String, String> params = new HashMap<>();
        params.put("category_id", String.valueOf(categoryId));
        params.put("db_number", String.valueOf(App.getInstance().getmRuntimeObject().getDbNumber()));
        getCompositeDisposable().add(getDataManager()
                .getSentences(params)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if (!isViewAttached()) {
                        return;
                    }

                    getMvpView().hideLoading();

                    getMvpView().onGetSentencesSuccess(response);
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
