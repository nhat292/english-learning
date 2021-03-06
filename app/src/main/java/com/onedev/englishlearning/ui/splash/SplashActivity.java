
package com.onedev.englishlearning.ui.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.onedev.englishlearning.R;
import com.onedev.englishlearning.ui.base.BaseActivity;
import com.onedev.englishlearning.ui.login.LoginActivity;
import com.onedev.englishlearning.ui.main.MainActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;


/**
 * Created by Nhat on 12/13/17.
 */


public class SplashActivity extends BaseActivity implements SplashBaseView {

    @Inject
    SplashMvpPresenter<SplashBaseView> mPresenter;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, SplashActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(SplashActivity.this);
    }


    @Override
    public void openMainActivity() {
        Intent intent = MainActivity.getStartIntent(SplashActivity.this);
        startActivity(intent);
        finish();
    }


    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    protected void setUp() {

    }
}
