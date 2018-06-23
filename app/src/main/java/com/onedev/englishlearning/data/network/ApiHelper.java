
package com.onedev.englishlearning.data.network;


import com.onedev.englishlearning.data.network.model.BlogResponse;
import com.onedev.englishlearning.data.network.model.LoginRequest;
import com.onedev.englishlearning.data.network.model.LoginResponse;
import com.onedev.englishlearning.data.network.model.LogoutResponse;
import com.onedev.englishlearning.data.network.model.OpenSourceResponse;

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
}
