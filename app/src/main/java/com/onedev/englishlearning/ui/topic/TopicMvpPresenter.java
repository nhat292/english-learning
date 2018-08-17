package com.onedev.englishlearning.ui.topic;

import com.onedev.englishlearning.dagger.PerActivity;
import com.onedev.englishlearning.ui.base.MvpPresenter;

@PerActivity
public interface TopicMvpPresenter<V extends TopicBaseView> extends MvpPresenter<V> {

    void getTopics(boolean isShowLoading);

}
