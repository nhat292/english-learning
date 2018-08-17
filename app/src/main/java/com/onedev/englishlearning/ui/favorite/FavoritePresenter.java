package com.onedev.englishlearning.ui.favorite;

import com.onedev.englishlearning.data.DataManager;
import com.onedev.englishlearning.ui.base.BasePresenter;
import com.onedev.englishlearning.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class FavoritePresenter<V extends FavoriteBaseView> extends BasePresenter<V> implements FavoriteMvpPresenter<V> {


    @Inject
    public FavoritePresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

}
