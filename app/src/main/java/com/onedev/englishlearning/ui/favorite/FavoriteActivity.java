package com.onedev.englishlearning.ui.favorite;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.onedev.englishlearning.App;
import com.onedev.englishlearning.R;
import com.onedev.englishlearning.adapter.CategoryAdapter;
import com.onedev.englishlearning.data.model.Category;
import com.onedev.englishlearning.data.network.model.FavoritesResponse;
import com.onedev.englishlearning.ui.base.BaseActivity;
import com.onedev.englishlearning.ui.category.CategoryActivity;
import com.onedev.englishlearning.ui.detail.DetailActivity;
import com.onedev.englishlearning.utils.callback.ListItemClickCallback;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FavoriteActivity extends BaseActivity implements FavoriteBaseView, SwipeRefreshLayout.OnRefreshListener {

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, FavoriteActivity.class);
        return intent;
    }

    @Inject
    FavoriteMvpPresenter<FavoriteBaseView> mPresenter;

    @BindView(R.id.swipeToRefreshLayout)
    SwipeRefreshLayout swipeToRefreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.txtNoFavoritesYet)
    TextView txtNoFavoritesYet;

    private ArrayList<Category> mListItems = new ArrayList<>();
    private CategoryAdapter mCategoryAdapter;
    private int mSelectedPosition;


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

        swipeToRefreshLayout.setColorSchemeResources(R.color.colorPrimaryDark, R.color.colorPrimaryDark);
        swipeToRefreshLayout.setOnRefreshListener(this);

        mCategoryAdapter = new CategoryAdapter(mListItems, (position, type) -> {
            if (type == 0) {
                App.getInstance().getmRuntimeObject().setDbNumber(mListItems.get(position).getType());
                startActivity(DetailActivity.getStartIntent(
                        FavoriteActivity.this,
                        mListItems.get(position).getName(),
                        mListItems.get(position).getId()));
            }
            if (type == 1) {
                mSelectedPosition = position;
                mPresenter.removeItemFromFavorites(mListItems.get(position).getId(), mListItems.get(position).getType());
            }
        });
        mCategoryAdapter.setShowingOnFavoritesPage(true);
        recyclerView.setAdapter(mCategoryAdapter);

        mPresenter.getFavorites();
    }

    @Override
    public void onGetFavoritesSuccess(FavoritesResponse response) {
        mListItems.clear();
        mListItems.addAll(response.getFavoritesData().getCategories1());
        mListItems.addAll(response.getFavoritesData().getCategories2());
        mCategoryAdapter.notifyDataSetChanged();

        updateEmptyText();
    }

    @Override
    public void hideRefreshLoading() {
        swipeToRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        mPresenter.getFavorites();
    }

    @Override
    public void onRemovedSuccessfully() {
        mListItems.remove(mSelectedPosition);
        mCategoryAdapter.notifyItemRemoved(mSelectedPosition);

        updateEmptyText();
    }

    private void updateEmptyText() {
        if (mListItems.size() == 0) {
            txtNoFavoritesYet.setVisibility(View.VISIBLE);
        } else {
            txtNoFavoritesYet.setVisibility(View.INVISIBLE);
        }
    }
}
