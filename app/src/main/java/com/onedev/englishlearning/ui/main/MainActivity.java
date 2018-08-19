
package com.onedev.englishlearning.ui.main;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.onedev.englishlearning.App;
import com.onedev.englishlearning.BuildConfig;
import com.onedev.englishlearning.R;
import com.onedev.englishlearning.adapter.MainAdapter;
import com.onedev.englishlearning.data.model.MainLibrary;
import com.onedev.englishlearning.data.model.User;
import com.onedev.englishlearning.ui.about.AboutFragment;
import com.onedev.englishlearning.ui.base.BaseActivity;
import com.onedev.englishlearning.ui.custom.RoundedImageView;
import com.onedev.englishlearning.ui.favorite.FavoriteActivity;
import com.onedev.englishlearning.ui.topic.TopicActivity;
import com.onedev.englishlearning.utils.AppConstants;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Nhat on 12/13/17.
 */


public class MainActivity extends BaseActivity implements MainBaseView {

    @Inject
    MainMvpPresenter<MainBaseView> mPresenter;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.drawer_view)
    DrawerLayout mDrawer;

    @BindView(R.id.navigationView)
    NavigationView mNavigationView;

    @BindView(R.id.tv_app_version)
    TextView mAppVersionTextView;

    @BindView(R.id.recyclerViewMain)
    RecyclerView recyclerViewMain;

    RoundedImageView imgAvatar;
    TextView txtName;
    TextView txtEmail;

    private ArrayList<MainLibrary> mMainLibraries;
    private MainAdapter mMainAdapter;

    private ActionBarDrawerToggle mDrawerToggle;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(this);

        setUp();
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(AboutFragment.TAG);
        if (fragment == null) {
            super.onBackPressed();
        } else {
            onFragmentDetached(AboutFragment.TAG);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mDrawer != null)
            mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    public void onFragmentAttached() {
    }

    @Override
    public void onFragmentDetached(String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if (fragment != null) {
            fragmentManager
                    .beginTransaction()
                    .disallowAddToBackStack()
                    .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                    .remove(fragment)
                    .commitNow();
            unlockDrawer();
        }
    }

    @Override
    public void lockDrawer() {
        if (mDrawer != null) {
            mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }
    }

    @Override
    public void unlockDrawer() {
        if (mDrawer != null) {
            mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        }
    }

    @Override
    public void onOpenFavoriteActivity() {
        startActivity(FavoriteActivity.getStartIntent(MainActivity.this));
    }

    @Override
    public void onLoginRequires() {
        showMessage("Login requires");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Drawable drawable = item.getIcon();
        if (drawable instanceof Animatable) {
            ((Animatable) drawable).start();
        }
        switch (item.getItemId()) {
            case R.id.action_share:
                return true;
            case R.id.action_favorites:
                mPresenter.onFavoritesClick();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void setUp() {
        setSupportActionBar(mToolbar);
        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawer,
                mToolbar,
                R.string.open_drawer,
                R.string.close_drawer) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                hideKeyboard();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawer.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        setupNavMenu();
        mPresenter.onNavMenuCreated();
        mPresenter.onViewInitialized();

        // Setup recyclerView
        mMainLibraries = new ArrayList<>();
        mMainLibraries.add(new MainLibrary(R.drawable.ic_book, "Library 1", "Lorem lorem lorem lorem lorem", AppConstants.DATABASE1_NUMBER));
        mMainLibraries.add(new MainLibrary(R.drawable.ic_book, "Library 2", "Lorem lorem lorem lorem lorem", AppConstants.DATABASE2_NUMBER));
        mMainAdapter = new MainAdapter(mMainLibraries, (position, type) -> {
            App.getInstance().getmRuntimeObject().setDbNumber(mMainLibraries.get(position).getDbNumber());
            startActivity(TopicActivity.getStartIntent(MainActivity.this, mMainLibraries.get(position).getTitle()));
        });
        recyclerViewMain.setAdapter(mMainAdapter);

        String version = "v" + BuildConfig.VERSION_NAME;
        mAppVersionTextView.setText(version);

        View headerView = mNavigationView.getHeaderView(0);
        imgAvatar = headerView.findViewById(R.id.imgAvatar);
        txtName = headerView.findViewById(R.id.txtName);
        txtEmail = headerView.findViewById(R.id.txtEmail);

        mPresenter.getUserInfo();

    }

    void setupNavMenu() {
        mNavigationView.setNavigationItemSelectedListener(
                item -> {
                    mDrawer.closeDrawer(GravityCompat.START);
                    switch (item.getItemId()) {
                        case R.id.nav_item_about:
                            mPresenter.onDrawerOptionAboutClick();
                            return true;
                        case R.id.nav_item_rate_us:
                            mPresenter.onDrawerRateUsClick();
                            return true;
                        case R.id.nav_item_login:
                            mPresenter.onDrawerOptionLogInClick();
                            return true;
                        default:
                            return false;
                    }
                });
    }

    @Override
    public void closeNavigationDrawer() {
        if (mDrawer != null) {
            mDrawer.closeDrawer(Gravity.START);
        }
    }

    @Override
    public void onGetUserInfoSuccess(User user) {
        Menu menuNav = mNavigationView.getMenu();
        MenuItem loginItem = menuNav.findItem(R.id.nav_item_login);
        if(user != null) {
            txtName.setText(user.getName());
            txtEmail.setText(user.getEmail());
            loginItem.setVisible(false);
        } else {
            txtName.setText("Guest");
            txtEmail.setText("");
            loginItem.setVisible(true);
        }
    }
}
