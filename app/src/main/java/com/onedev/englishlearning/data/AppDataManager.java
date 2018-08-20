
package com.onedev.englishlearning.data;


import android.content.Context;

import com.onedev.englishlearning.dagger.ApplicationContext;
import com.onedev.englishlearning.data.db.DbHelper;
import com.onedev.englishlearning.data.db.model.Option;
import com.onedev.englishlearning.data.db.model.Question;
import com.onedev.englishlearning.data.db.model.User;
import com.onedev.englishlearning.data.network.ApiHeader;
import com.onedev.englishlearning.data.network.ApiHelper;
import com.onedev.englishlearning.data.network.NetworkCallback;
import com.onedev.englishlearning.data.network.model.BlogResponse;
import com.onedev.englishlearning.data.network.model.CategoryResponse;
import com.onedev.englishlearning.data.network.model.FavoritesResponse;
import com.onedev.englishlearning.data.network.model.LoginRequest;
import com.onedev.englishlearning.data.network.model.LoginResponse;
import com.onedev.englishlearning.data.network.model.LogoutResponse;
import com.onedev.englishlearning.data.network.model.OpenSourceResponse;
import com.onedev.englishlearning.data.network.model.SentenceResponse;
import com.onedev.englishlearning.data.network.model.SimpleResponse;
import com.onedev.englishlearning.data.network.model.TopicResponse;
import com.onedev.englishlearning.data.prefs.PreferencesHelper;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * Created by Nhat on 12/13/17.
 */


@Singleton
public class AppDataManager implements DataManager {

    private static final String TAG = "AppDataManager";

    private final Context mContext;
    private final DbHelper mDbHelper;
    private final PreferencesHelper mPreferencesHelper;
    private final ApiHelper mApiHelper;

    @Inject
    public AppDataManager(@ApplicationContext Context context,
                          DbHelper dbHelper,
                          PreferencesHelper preferencesHelper,
                          ApiHelper apiHelper) {
        mContext = context;
        mDbHelper = dbHelper;
        mPreferencesHelper = preferencesHelper;
        mApiHelper = apiHelper;
    }

    @Override
    public ApiHeader getApiHeader() {
        return mApiHelper.getApiHeader();
    }


    @Override
    public Observable<Long> insertUser(User user) {
        return mDbHelper.insertUser(user);
    }

    @Override
    public Observable<List<User>> getAllUsers() {
        return mDbHelper.getAllUsers();
    }

    @Override
    public Observable<LoginResponse> doGoogleLoginApiCall(LoginRequest.GoogleLoginRequest
                                                                  request) {
        return mApiHelper.doGoogleLoginApiCall(request);
    }

    @Override
    public Observable<LoginResponse> doFacebookLoginApiCall(LoginRequest.FacebookLoginRequest
                                                                    request) {
        return mApiHelper.doFacebookLoginApiCall(request);
    }

    @Override
    public Observable<LoginResponse> doServerLoginApiCall(LoginRequest.ServerLoginRequest
                                                                  request) {
        return mApiHelper.doServerLoginApiCall(request);
    }

    @Override
    public Observable<LogoutResponse> doLogoutApiCall(NetworkCallback callback) {
        return mApiHelper.doLogoutApiCall(callback);
    }

    @Override
    public Observable<Boolean> isQuestionEmpty() {
        return mDbHelper.isQuestionEmpty();
    }

    @Override
    public Observable<Boolean> isOptionEmpty() {
        return mDbHelper.isOptionEmpty();
    }

    @Override
    public Observable<Boolean> saveQuestion(Question question) {
        return mDbHelper.saveQuestion(question);
    }

    @Override
    public Observable<Boolean> saveOption(Option option) {
        return mDbHelper.saveOption(option);
    }

    @Override
    public Observable<Boolean> saveQuestionList(List<Question> questionList) {
        return mDbHelper.saveQuestionList(questionList);
    }

    @Override
    public Observable<Boolean> saveOptionList(List<Option> optionList) {
        return mDbHelper.saveOptionList(optionList);
    }

    @Override
    public Observable<List<Question>> getAllQuestions() {
        return mDbHelper.getAllQuestions();
    }


    @Override
    public Observable<BlogResponse> getBlogApiCall() {
        return mApiHelper.getBlogApiCall();
    }

    @Override
    public Observable<OpenSourceResponse> getOpenSourceApiCall() {
        return mApiHelper.getOpenSourceApiCall();
    }

    @Override
    public Observable<TopicResponse> getTopics(Map<String, String> params) {
        return mApiHelper.getTopics(params);
    }

    @Override
    public Observable<CategoryResponse> getCategories(Map<String, String> params) {
        return mApiHelper.getCategories(params);
    }

    @Override
    public Observable<SentenceResponse> getSentences(Map<String, String> params) {
        return mApiHelper.getSentences(params);
    }

    @Override
    public Observable<SimpleResponse> addOrRemoveFavorite(Map<String, String> params) {
        return mApiHelper.addOrRemoveFavorite(params);
    }

    @Override
    public Observable<LoginResponse> login(Map<String, String> params) {
        return mApiHelper.login(params);
    }

    @Override
    public Observable<FavoritesResponse> getFavorites(Map<String, String> params) {
        return mApiHelper.getFavorites(params);
    }

    @Override
    public void setUser(com.onedev.englishlearning.data.model.User user) {
        mPreferencesHelper.setUser(user);
    }

    @Override
    public com.onedev.englishlearning.data.model.User getUser() {
        return mPreferencesHelper.getUser();
    }
}
