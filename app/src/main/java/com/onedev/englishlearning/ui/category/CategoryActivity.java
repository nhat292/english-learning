package com.onedev.englishlearning.ui.category;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.onedev.englishlearning.R;
import com.onedev.englishlearning.adapter.CategoryAdapter;
import com.onedev.englishlearning.data.model.Category;
import com.onedev.englishlearning.data.network.model.CategoryResponse;
import com.onedev.englishlearning.ui.base.BaseActivity;
import com.onedev.englishlearning.ui.detail.DetailActivity;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class CategoryActivity extends BaseActivity implements CategoryBaseView, SwipeRefreshLayout.OnRefreshListener {

    private static final String EXTRA_TITLE = "EXTRA_TITLE";
    private static final String EXTRA_TOPIC_ID = "EXTRA_TOPIC_ID";

    public static Intent getStartIntent(Context context, String title, int topicId) {
        Intent intent = new Intent(context, CategoryActivity.class);
        intent.putExtra(EXTRA_TITLE, title);
        intent.putExtra(EXTRA_TOPIC_ID, topicId);
        return intent;
    }

    @Inject
    CategoryMvpPresenter<CategoryBaseView> mPresenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.swipeToRefreshLayout)
    SwipeRefreshLayout swipeToRefreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private int mTopicId;
    private ArrayList<Category> mListItems = new ArrayList<>();
    private CategoryAdapter mCategoryAdapter;
    private int mSelectedItemPosition;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

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
        if (intent.hasExtra(EXTRA_TOPIC_ID)) {
            mTopicId = intent.getIntExtra(EXTRA_TOPIC_ID, 0);
        }

        swipeToRefreshLayout.setColorSchemeResources(R.color.colorPrimaryDark, R.color.colorPrimaryDark);
        swipeToRefreshLayout.setOnRefreshListener(this);

        mCategoryAdapter = new CategoryAdapter(mListItems, (position, type) -> {
            if(type == 0) {
                startActivity(DetailActivity.getStartIntent(
                        CategoryActivity.this,
                        mListItems.get(position).getName(),
                        mListItems.get(position).getId()));
            } else {
                mSelectedItemPosition = mListItems.get(position).getId();
                // 1-add, 0-remove
                int addOrRemove = mListItems.get(position).isAddedFavories() ? 0 : 1;
                mPresenter.addOrRemoveFavorite(addOrRemove, mListItems.get(position).getId());
            }
        }
        );
        recyclerView.setAdapter(mCategoryAdapter);
        mPresenter.getCategories(mTopicId, true);
    }

    @Override
    public void onRefresh() {
        mPresenter.getCategories(mTopicId, false);
    }

    @Override
    public void hideRefreshLoading() {
        swipeToRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onGetCategoriesSuccess(CategoryResponse response) {
        mListItems.clear();
        mListItems.addAll(response.getCategories());
        mCategoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void onAddOrRemoveSuccess() {
        if(mListItems.get(mSelectedItemPosition).isAddedFavories()) {
            mListItems.get(mSelectedItemPosition).setAddedFavorites(false);
        } else {
            mListItems.get(mSelectedItemPosition).setAddedFavorites(true);
        }
        mCategoryAdapter.notifyItemChanged(mSelectedItemPosition);
    }

    @Override
    public void onLoginRequires() {
        showMessage("Login requires");
    }
}
