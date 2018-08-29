package com.onedev.englishlearning.ui.category;

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
import com.onedev.englishlearning.data.model.User;
import com.onedev.englishlearning.ui.base.BasePresenter;
import com.onedev.englishlearning.utils.rx.SchedulerProvider;

import org.json.JSONException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class CategoryPresenter<V extends CategoryBaseView> extends BasePresenter<V> implements CategoryMvpPresenter<V> {

    @Inject
    public CategoryPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);

    }

    @Override
    public void getCategories(int topicId, boolean isShowLoading) {
        if (isShowLoading) {
            getMvpView().showLoading();
        }
        Map<String, String> params = new HashMap<>();
        params.put("topic_id", String.valueOf(topicId));
        params.put("db_number", String.valueOf(App.getInstance().getmRuntimeObject().getDbNumber()));
        User user = getDataManager().getUser();
        if (user != null) {
            params.put("user_id", String.valueOf(user.getId()));
        }
        getCompositeDisposable().add(getDataManager()
                .getCategories(params)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if (!isViewAttached()) {
                        return;
                    }

                    getMvpView().hideLoading();
                    getMvpView().hideRefreshLoading();

                    getMvpView().onGetCategoriesSuccess(response);
                }, throwable -> {
                    if (!isViewAttached()) {
                        return;
                    }

                    getMvpView().hideLoading();
                    getMvpView().hideRefreshLoading();

                    // handle the login error here
                    if (throwable instanceof ANError) {
                        ANError anError = (ANError) throwable;
                        handleApiError(anError);
                    }
                }));
    }

    @Override
    public void addOrRemoveFavorite(int addOrRemove, int categoryId) {

        User user = getDataManager().getUser();
        if (user == null) {
            getMvpView().onLoginRequires();
            return;
        }

        getMvpView().showLoading();
        Map<String, String> params = new HashMap<>();
        params.put("add_or_remove", String.valueOf(addOrRemove));
        params.put("category_id", String.valueOf(categoryId));
        params.put("user_id", String.valueOf(user.getId()));
        params.put("db_number", String.valueOf(App.getInstance().getmRuntimeObject().getDbNumber()));
        getCompositeDisposable().add(getDataManager()
                .addOrRemoveFavorite(params)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if (!isViewAttached()) {
                        return;
                    }

                    getMvpView().hideLoading();
                    getMvpView().onAddOrRemoveSuccess();

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
    public void loginClick(CategoryActivity activity) {
        if (getDataManager().getUser() == null) {
            LoginManager.getInstance().logInWithReadPermissions(activity, Arrays.asList("public_profile", "email"));
        }
    }
}
