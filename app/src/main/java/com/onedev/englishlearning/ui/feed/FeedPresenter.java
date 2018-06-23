
package com.onedev.englishlearning.ui.feed;

import com.onedev.englishlearning.data.DataManager;
import com.onedev.englishlearning.ui.base.BasePresenter;
import com.onedev.englishlearning.ui.base.BaseView;
import com.onedev.englishlearning.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Nhat on 12/13/17.
 */


public class FeedPresenter<V extends BaseView> extends BasePresenter<V> implements
        FeedMvpPresenter<V> {

    private static final String TAG = "FeedPresenter";

    @Inject
    public FeedPresenter(DataManager dataManager,
                         SchedulerProvider schedulerProvider,
                         CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
