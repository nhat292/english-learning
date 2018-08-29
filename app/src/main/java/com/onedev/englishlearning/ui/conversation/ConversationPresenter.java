package com.onedev.englishlearning.ui.conversation;

import com.androidnetworking.error.ANError;
import com.onedev.englishlearning.data.DataManager;
import com.onedev.englishlearning.ui.base.BasePresenter;
import com.onedev.englishlearning.utils.rx.SchedulerProvider;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class ConversationPresenter<V extends ConversationBaseView> extends BasePresenter<V> implements ConversationMvpPresenter<V> {

    @Inject
    public ConversationPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void getConversations(int categoryId) {

        getMvpView().showLoading();
        Map<String, String> params = new HashMap<>();
        params.put("category_id", String.valueOf(categoryId));

        getCompositeDisposable().add(getDataManager()
                .getConversations(params)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if (!isViewAttached()) {
                        return;
                    }

                    getMvpView().hideLoading();

                    getMvpView().onGetConversationSuccess(response);
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
