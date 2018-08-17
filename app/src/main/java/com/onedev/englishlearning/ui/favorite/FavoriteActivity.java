package com.onedev.englishlearning.ui.favorite;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.onedev.englishlearning.R;
import com.onedev.englishlearning.ui.base.BaseActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class FavoriteActivity extends BaseActivity implements FavoriteBaseView {


    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, FavoriteActivity.class);
        return intent;
    }

    @Inject
    FavoriteMvpPresenter<FavoriteBaseView> mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(this);

        setUp();
    }

    @OnClick(R.id.nav_back_btn)
    public void onBackClick() {
        finish();
    }

    @Override
    protected void setUp() {

    }
}
