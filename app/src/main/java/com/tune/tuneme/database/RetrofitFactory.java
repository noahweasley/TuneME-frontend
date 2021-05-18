package com.tune.tuneme.database;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@SuppressWarnings("unused")
public class RetrofitFactory {
    private static final String TUNE_BASE_URL = "http://www.api.com/api/v_1.0/";
    private static Retrofit retrofit;

    /**
     * @return a newly created retrofit instance used for HTTP requests on TuneMe's api
     */
    public static Retrofit createInstance() {
        return new Retrofit.Builder()
                .baseUrl(TUNE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * @return the existent retrofit instance used for HTTP request on TuneMe's api
     */
    public static Retrofit getInstance() {
        if (retrofit != null) {
            return retrofit;
        } else {
            throw new IllegalStateException("Retrofit instance not created, use createInstance()");
        }
    }

    /**
     * @return TuneMe's api base url
     */
    public static String getTuneBaseUrl() {
        return TUNE_BASE_URL;
    }
}
