package com.onedev.englishlearning.ui.favorite;

import com.onedev.englishlearning.dagger.PerActivity;
import com.onedev.englishlearning.ui.base.MvpPresenter;

@PerActivity
public interface FavoriteMvpPresenter<V extends FavoriteBaseView> extends MvpPresenter<V> {

    void getFavorites();

    void removeItemFromFavorites(int categoryId, int dbNumber);

}
