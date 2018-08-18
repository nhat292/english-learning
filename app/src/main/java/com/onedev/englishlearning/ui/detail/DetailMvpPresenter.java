package com.onedev.englishlearning.ui.detail;

import com.onedev.englishlearning.dagger.PerActivity;
import com.onedev.englishlearning.ui.base.MvpPresenter;

@PerActivity
public interface DetailMvpPresenter<V extends DetailBaseView> extends MvpPresenter<V> {

    void getSentences(int categoryId, boolean isShowLoading);
}
