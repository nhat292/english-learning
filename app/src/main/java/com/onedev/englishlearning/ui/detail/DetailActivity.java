package com.onedev.englishlearning.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.onedev.englishlearning.R;
import com.onedev.englishlearning.adapter.DetailAdapter;
import com.onedev.englishlearning.data.model.Sentence;
import com.onedev.englishlearning.data.model.SentenceData;
import com.onedev.englishlearning.data.network.model.SentenceResponse;
import com.onedev.englishlearning.ui.base.BaseActivity;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailActivity extends BaseActivity implements DetailBaseView, SwipeRefreshLayout.OnRefreshListener {

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

    @BindView(R.id.swipeToRefreshLayout)
    SwipeRefreshLayout swipeToRefreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private int mCategoryId;
    private ArrayList<Sentence> mListItems = new ArrayList<>();
    private DetailAdapter mDetailAdapter;
    private SentenceData mSentenceData;

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

        swipeToRefreshLayout.setColorSchemeResources(R.color.colorPrimaryDark, R.color.colorPrimaryDark);
        swipeToRefreshLayout.setOnRefreshListener(this);

        mDetailAdapter = new DetailAdapter(mListItems, position -> {

        });
        recyclerView.setAdapter(mDetailAdapter);

        mPresenter.getSentences(mCategoryId, true);
    }

    @Override
    public void onGetSentencesSuccess(SentenceResponse response) {
        mListItems.clear();
        mSentenceData = response.getData();
        mListItems.addAll(response.getData().getSentences());
        mDetailAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh() {
        mPresenter.getSentences(mCategoryId, false);
    }

    @Override
    public void hideRefreshLoading() {
        swipeToRefreshLayout.setRefreshing(false);
    }
}
