package com.onedev.englishlearning.ui.category;

import com.onedev.englishlearning.data.model.User;
import com.onedev.englishlearning.data.network.model.CategoryResponse;
import com.onedev.englishlearning.ui.base.BaseView;

public interface CategoryBaseView extends BaseView {


    void hideRefreshLoading();

    void onGetCategoriesSuccess(CategoryResponse response);

    void onAddOrRemoveSuccess();

    void onLoginRequires();

    void onLoginSuccess(User user);
}
