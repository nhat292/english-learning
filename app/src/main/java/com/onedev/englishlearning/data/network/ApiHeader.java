
package com.onedev.englishlearning.data.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.onedev.englishlearning.dagger.ApiInfo;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Nhat on 12/13/17.
 */


@Singleton
public class ApiHeader {

    private ProtectedApiHeader mProtectedApiHeader;
    private PublicApiHeader mPublicApiHeader;

    @Inject
    public ApiHeader(PublicApiHeader publicApiHeader, ProtectedApiHeader protectedApiHeader) {
        mPublicApiHeader = publicApiHeader;
        mProtectedApiHeader = protectedApiHeader;
    }

    public ProtectedApiHeader getProtectedApiHeader() {
        return mProtectedApiHeader;
    }

    public PublicApiHeader getPublicApiHeader() {
        return mPublicApiHeader;
    }

    public static final class PublicApiHeader {

        @Expose
        @SerializedName("api_key")
        private String mApiKey;

        @Inject
        public PublicApiHeader(@ApiInfo String apiKey) {
            mApiKey = apiKey;
        }

        public String getApiKey() {
            return mApiKey;
        }

        public void setApiKey(String apiKey) {
            mApiKey = apiKey;
        }
    }

    public static final class ProtectedApiHeader {

        @Expose
        @SerializedName("api_key")
        private String mApiKey;

        @Expose
        @SerializedName("user_id")
        private Long mUserId;

        @Expose
        @SerializedName("Authorization")
        private String mAuthorization;

        public ProtectedApiHeader(String mApiKey, Long mUserId, String authorization) {
            this.mApiKey = mApiKey;
            this.mUserId = mUserId;
            this.mAuthorization = authorization;
        }

        public String getApiKey() {
            return mApiKey;
        }

        public void setApiKey(String apiKey) {
            mApiKey = apiKey;
        }

        public Long getUserId() {
            return mUserId;
        }

        public void setUserId(Long mUserId) {
            this.mUserId = mUserId;
        }

        public String getAccessToken() {
            return mAuthorization;
        }

        public void setAccessToken(String authorization) {
            mAuthorization = authorization;
        }
    }
}
