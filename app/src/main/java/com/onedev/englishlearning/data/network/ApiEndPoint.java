
package com.onedev.englishlearning.data.network;


import com.onedev.englishlearning.BuildConfig;

/**
 * Created by Nhat on 12/13/17.
 */


public final class ApiEndPoint {

    public static final String ENDPOINT_GOOGLE_LOGIN = BuildConfig.BASE_URL
            + "/588d14f4100000a9072d2943";

    public static final String ENDPOINT_FACEBOOK_LOGIN = BuildConfig.BASE_URL
            + "/588d15d3100000ae072d2944";

    public static final String ENDPOINT_SERVER_LOGIN = BuildConfig.BASE_URL
            + "/588d15f5100000a8072d2945";

    public static final String ENDPOINT_LOGOUT = BuildConfig.BASE_URL
            + "/588d161c100000a9072d2946";

    public static final String ENDPOINT_BLOG = BuildConfig.BASE_URL
            + "/5926ce9d11000096006ccb30";

    public static final String ENDPOINT_OPEN_SOURCE = BuildConfig.BASE_URL
            + "/5926c34212000035026871cd";

    public static final String ENDPOINT_GET_TOPICS = BuildConfig.BASE_URL
            + "api/api.php/get-topics";

    public static final String ENDPOINT_GET_CATEGORIES = BuildConfig.BASE_URL
            + "api/api.php/get-categories-by-topic";

    public static final String ENDPOINT_GET_SENTENCES = BuildConfig.BASE_URL
            + "api/api.php/get-sentences-by-category";

    public static final String ENDPOINT_ADD_REMOVE_FAVORITES = BuildConfig.BASE_URL
            + "api/api.php/add-or-remove-favorites";

    public static final String ENDPOINT_LOGIN = BuildConfig.BASE_URL
            + "api/api.php/login";

    public static final String ENDPOINT_GET_FAVORITES = BuildConfig.BASE_URL
            + "api/api.php/get-favorites";

    public static final String ENDPOINT_GET_CONVERSATIONS= BuildConfig.BASE_URL
            + "api/api.php/get-conversations";

    private ApiEndPoint() {
        // This class is not publicly instantiable
    }

}
