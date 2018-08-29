
package com.onedev.englishlearning.data.network;


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

import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by Nhat on 12/13/17.
 */


public interface ApiHelper {

    ApiHeader getApiHeader();

    Observable<LoginResponse> doGoogleLoginApiCall(LoginRequest.GoogleLoginRequest request);

    Observable<LoginResponse> doFacebookLoginApiCall(LoginRequest.FacebookLoginRequest request);

    Observable<LoginResponse> doServerLoginApiCall(LoginRequest.ServerLoginRequest request);

    Observable<LogoutResponse> doLogoutApiCall(NetworkCallback callback);

    Observable<BlogResponse> getBlogApiCall();

    Observable<OpenSourceResponse> getOpenSourceApiCall();

    Observable<TopicResponse> getTopics(Map<String, String> params);

    Observable<CategoryResponse> getCategories(Map<String, String> params);

    Observable<SentenceResponse> getSentences(Map<String, String> params);

    Observable<SimpleResponse> addOrRemoveFavorite(Map<String, String> params);

    Observable<LoginResponse> login(Map<String, String> params);

    Observable<FavoritesResponse> getFavorites(Map<String, String> params);

    Observable<ConversationResponse> getConversations(Map<String, String> params);
}
