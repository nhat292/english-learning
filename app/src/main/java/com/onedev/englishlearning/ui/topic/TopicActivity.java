package com.onedev.englishlearning.ui.topic;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.onedev.englishlearning.R;
import com.onedev.englishlearning.adapter.TopicAdapter;
import com.onedev.englishlearning.data.model.Topic;
import com.onedev.englishlearning.data.network.model.TopicResponse;
import com.onedev.englishlearning.ui.base.BaseActivity;
import com.onedev.englishlearning.ui.category.CategoryActivity;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TopicActivity extends BaseActivity implements TopicBaseView, SwipeRefreshLayout.OnRefreshListener {

    private static final String EXTRA_TITLE = "EXTRA_TITLE";

    public static Intent getStartIntent(Context context, String title) {
        Intent intent = new Intent(context, TopicActivity.class);
        intent.putExtra(EXTRA_TITLE, title);
        return intent;
    }

    @Inject
    TopicMvpPresenter<TopicBaseView> mPresenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.swipeToRefreshLayout)
    SwipeRefreshLayout swipeToRefreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private ArrayList<Topic> mListItems = new ArrayList<>();
    private TopicAdapter mTopicAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);

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

        swipeToRefreshLayout.setColorSchemeResources(R.color.colorPrimaryDark, R.color.colorPrimaryDark);
        swipeToRefreshLayout.setOnRefreshListener(this);

        mTopicAdapter = new TopicAdapter(mListItems, position ->
                startActivity(CategoryActivity.getStartIntent(
                        TopicActivity.this,
                        mListItems.get(position).getName(),
                        mListItems.get(position).getId()
                ))
        );
        recyclerView.setAdapter(mTopicAdapter);

        mPresenter.getTopics(true);
    }

    @Override
    public void onRefresh() {
        mPresenter.getTopics(false);
    }

    @Override
    public void hideRefreshLoading() {
        swipeToRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onGetTopicsSuccess(TopicResponse response) {
        mListItems.clear();
        mListItems.addAll(response.getTopics());
        mTopicAdapter.notifyDataSetChanged();
    }
}
