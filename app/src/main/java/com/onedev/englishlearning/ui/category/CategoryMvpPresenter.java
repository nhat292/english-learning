package com.onedev.englishlearning.ui.category;

import com.onedev.englishlearning.dagger.PerActivity;
import com.onedev.englishlearning.ui.base.MvpPresenter;

@PerActivity
public interface CategoryMvpPresenter<V extends CategoryBaseView> extends MvpPresenter<V> {

    void getCategories(int topicId, boolean isShowLoading);

    void addOrRemoveFavorite(int addOrRemove, int categoryId);
}
