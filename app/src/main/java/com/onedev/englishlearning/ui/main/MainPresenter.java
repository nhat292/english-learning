
package com.onedev.englishlearning.ui.main;

import android.os.Bundle;

import com.androidnetworking.error.ANError;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.onedev.englishlearning.App;
import com.onedev.englishlearning.R;
import com.onedev.englishlearning.data.DataManager;
import com.onedev.englishlearning.ui.base.BasePresenter;
import com.onedev.englishlearning.utils.rx.SchedulerProvider;

import org.json.JSONException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;


/**
 * Created by Nhat on 12/13/17.
 */


public class MainPresenter<V extends MainBaseView> extends BasePresenter<V>
        implements MainMvpPresenter<V> {

    private static final String TAG = "MainPresenter";

    @Inject
    public MainPresenter(DataManager dataManager,
                         SchedulerProvider schedulerProvider,
                         CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onDrawerOptionAboutClick() {
        getMvpView().closeNavigationDrawer();
    }

    @Override
    public void onDrawerOptionLogInClick() {
    }

    @Override
    public void onViewInitialized() {

    }


    @Override
    public void onNavMenuCreated() {

    }

    @Override
    public void onDrawerRateUsClick() {
        getMvpView().closeNavigationDrawer();
    }

    @Override
    public void onFavoritesClick() {
        if (getDataManager().getUser() != null) {
            getMvpView().onOpenFavoriteActivity();
        } else {
            getMvpView().onLoginRequires();
        }
    }

    @Override
    public void getUserInfo() {
        getMvpView().onGetUserInfoSuccess(getDataManager().getUser());
    }

    @Override
    public void initFacebookLogin(CallbackManager callbackManager) {
        if (AccessToken.getCurrentAccessToken() != null) {
            LoginManager.getInstance().logOut();
        }
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), (object, response) -> {
                            try {
                                final String id = object.getString("id");
                                final String name = object.optString("name");
                                final String email = object.getString("email");

                                getMvpView().showLoading();
                                Map<String, String> params = new HashMap<>();
                                params.put("fb_id", id);
                                params.put("name", name);
                                params.put("email", email);
                                getCompositeDisposable().add(getDataManager()
                                        .login(params)
                                        .subscribeOn(getSchedulerProvider().io())
                                        .observeOn(getSchedulerProvider().ui())
                                        .subscribe(loginResponse -> {
                                            if (!isViewAttached()) {
                                                return;
                                            }

                                            getMvpView().hideLoading();

                                            getDataManager().setUser(loginResponse.getUser());
                                            getMvpView().onLoginSuccess(loginResponse.getUser());

                                        }, throwable -> {
                                            if (!isViewAttached()) {
                                                return;
                                            }

                                            getMvpView().hideLoading();

                                            // handle the login error here
                                            if (throwable instanceof ANError) {
                                                ANError anError = (ANError) throwable;
                                                handleApiError(anError);
                                            }
                                        }));

                            } catch (JSONException e) {
                                LoginManager.getInstance().logOut();
                                e.printStackTrace();
                            }
                        });
                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id, name, email");
                        request.setParameters(parameters);
                        request.executeAsync();
                    }

                    @Override
                    public void onCancel() {
                        getMvpView().showMessage(R.string.login_canceled);
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        getMvpView().onError(exception.getMessage());
                    }
                });
    }

    @Override
    public void loginLogoutClick(MainActivity activity) {
        if (getDataManager().getUser() == null) {
            LoginManager.getInstance().logInWithReadPermissions(activity, Arrays.asList("public_profile", "email"));
        } else {
            getMvpView().onLogoutClick();
        }
    }

    @Override
    public void logout() {
        getDataManager().setUser(null);
        getMvpView().onLogOutSuccess();
    }
}
