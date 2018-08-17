package com.onedev.englishlearning.ui.topic;

import com.androidnetworking.error.ANError;
import com.onedev.englishlearning.App;
import com.onedev.englishlearning.data.DataManager;
import com.onedev.englishlearning.ui.base.BasePresenter;
import com.onedev.englishlearning.utils.rx.SchedulerProvider;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class TopicPresenter<V extends TopicBaseView> extends BasePresenter<V> implements TopicMvpPresenter<V> {

    private static final String TAG = "TopicPresenter";

    @Inject
    public TopicPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void getTopics(boolean isShowLoading) {
        if(isShowLoading) {
            getMvpView().showLoading();
        }
        Map<String, String> params = new HashMap<>();
        params.put("db_number", String.valueOf(App.getInstance().getmRuntimeObject().getDbNumber()));
        getCompositeDisposable().add(getDataManager()
                .getTopics(params)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if (!isViewAttached()) {
                        return;
                    }

                    getMvpView().hideLoading();
                    getMvpView().hideRefreshLoading();

                    getMvpView().onGetTopicsSuccess(response);

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
}
