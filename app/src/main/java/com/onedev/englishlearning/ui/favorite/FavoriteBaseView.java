package com.onedev.englishlearning.ui.favorite;

import com.onedev.englishlearning.data.network.model.FavoritesResponse;
import com.onedev.englishlearning.ui.base.BaseView;

public interface FavoriteBaseView extends BaseView {

    void onGetFavoritesSuccess(FavoritesResponse response);

    void hideRefreshLoading();

    void onRemovedSuccessfully();
}
