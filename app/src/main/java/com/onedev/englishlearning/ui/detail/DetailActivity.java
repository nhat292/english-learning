package com.onedev.englishlearning.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.onedev.englishlearning.R;
import com.onedev.englishlearning.data.network.model.SentenceResponse;
import com.onedev.englishlearning.ui.base.BaseActivity;
import com.onedev.englishlearning.utils.AppLogger;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailActivity extends BaseActivity implements DetailBaseView {

    private static final String EXTRA_TITLE = "EXTRA_TITLE";
    private static final String EXTRA_CATEGORY_ID = "EXTRA_CATEGORY_ID";

    public static Intent getStartIntent(Context context, String title, int categoryId) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(EXTRA_TITLE, title);
        intent.putExtra(EXTRA_CATEGORY_ID, categoryId);
        return intent;
    }

    @Inject
    DetailMvpPresenter<DetailBaseView> mPresenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private int mCategoryId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

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
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_TITLE)) {
            toolbar.setTitle(intent.getStringExtra(EXTRA_TITLE));
        }
        if (intent.hasExtra(EXTRA_CATEGORY_ID)) {
            mCategoryId = intent.getIntExtra(EXTRA_CATEGORY_ID, 0);
        }

        mPresenter.getSentences(mCategoryId);
    }

    @Override
    public void onGetSentencesSuccess(SentenceResponse response) {
        AppLogger.d("OnGetSentencesStatus: " + response.getMessage());
    }
}
