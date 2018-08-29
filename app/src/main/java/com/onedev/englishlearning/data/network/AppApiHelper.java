
package com.onedev.englishlearning.data.network;

import com.onedev.englishlearning.App;
import com.onedev.englishlearning.data.network.model.BlogResponse;
import com.onedev.englishlearning.data.network.model.CategoryResponse;
import com.onedev.englishlearning.data.network.model.ConversationResponse;
import com.onedev.englishlearning.data.network.model.FavoritesResponse;
import com.onedev.englishlearning.data.network.model.LoginRequest;
import com.onedev.englishlearning.data.network.model.LoginResponse;
import com.onedev.englishlearning.data.network.model.LogoutResponse;
import com.onedev.englishlearning.data.network.model.OpenSourceResponse;
import com.onedev.englishlearning.data.network.model.SentenceResponse;
import com.onedev.englishlearning.data.network.model.SimpleResponse;
import com.onedev.englishlearning.data.network.model.TopicResponse;
import com.onedev.englishlearning.utils.NetworkUtils;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * Created by Nhat on 12/13/17.
 */


@Singleton
public class AppApiHelper implements ApiHelper {

    private ApiHeader mApiHeader;

    @Inject
    public AppApiHelper(ApiHeader apiHeader) {
        mApiHeader = apiHeader;
    }

    @Override
    public ApiHeader getApiHeader() {
        return mApiHeader;
    }

    @Override
    public Observable<LoginResponse> doGoogleLoginApiCall(LoginRequest.GoogleLoginRequest
                                                                  request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_GOOGLE_LOGIN)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .addBodyParameter(request)
                .build()
                .getObjectObservable(LoginResponse.class);
    }

    @Override
    public Observable<LoginResponse> doFacebookLoginApiCall(LoginRequest.FacebookLoginRequest
                                                                    request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_FACEBOOK_LOGIN)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .addBodyParameter(request)
                .build()
                .getObjectObservable(LoginResponse.class);
    }

    @Override
    public Observable<LoginResponse> doServerLoginApiCall(LoginRequest.ServerLoginRequest
                                                                  request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_SERVER_LOGIN)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .addBodyParameter(request)
                .build()
                .getObjectObservable(LoginResponse.class);
    }

    @Override
    public Observable<LogoutResponse> doLogoutApiCall(NetworkCallback callback) {
        return NetworkUtils.isInternetOnObservable(App.getInstance())
                .filter(connectionStatus -> {
                    if (callback != null) {
                        callback.networkStatus(connectionStatus);
                    }
                    return connectionStatus;
                })
                .switchMap(connectionStatus -> Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_LOGOUT)
                        .addHeaders(mApiHeader.getProtectedApiHeader())
                        .build()
                        .getObjectObservable(LogoutResponse.class));
    }

    @Override
    public Observable<BlogResponse> getBlogApiCall() {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_BLOG)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectObservable(BlogResponse.class);
    }

    @Override
    public Observable<OpenSourceResponse> getOpenSourceApiCall() {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_OPEN_SOURCE)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectObservable(OpenSourceResponse.class);
    }

    @Override
    public Observable<TopicResponse> getTopics(Map<String, String> params) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_GET_TOPICS)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .addUrlEncodeFormBodyParameter(params)
                .build()
                .getObjectObservable(TopicResponse.class);
    }

    @Override
    public Observable<CategoryResponse> getCategories(Map<String, String> params) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_GET_CATEGORIES)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .addUrlEncodeFormBodyParameter(params)
                .build()
                .getObjectObservable(CategoryResponse.class);
    }

    @Override
    public Observable<SentenceResponse> getSentences(Map<String, String> params) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_GET_SENTENCES)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .addUrlEncodeFormBodyParameter(params)
                .build()
                .getObjectObservable(SentenceResponse.class);
    }

    @Override
    public Observable<SimpleResponse> addOrRemoveFavorite(Map<String, String> params) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_ADD_REMOVE_FAVORITES)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .addUrlEncodeFormBodyParameter(params)
                .build()
                .getObjectObservable(SimpleResponse.class);
    }

    @Override
    public Observable<LoginResponse> login(Map<String, String> params) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_LOGIN)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .addUrlEncodeFormBodyParameter(params)
                .build()
                .getObjectObservable(LoginResponse.class);
    }

    @Override
    public Observable<FavoritesResponse> getFavorites(Map<String, String> params) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_GET_FAVORITES)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .addUrlEncodeFormBodyParameter(params)
                .build()
                .getObjectObservable(FavoritesResponse.class);
    }

    @Override
    public Observable<ConversationResponse> getConversations(Map<String, String> params) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_GET_CONVERSATIONS)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .addUrlEncodeFormBodyParameter(params)
                .build()
                .getObjectObservable(ConversationResponse.class);
    }
}

