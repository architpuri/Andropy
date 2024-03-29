package com.aptech.andropy.data;

/**
 * Created By  Archit
 * on 11/13/2019
 * for Andropy
 */
import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static final String baseUrl="http://000.000.000.000:8080/";
    private static AndropyService andropyService = null;

    private ApiClient() {
        // default constructor needs to be private otherwise it will expose the behavior of singleton
    }

    public static AndropyService getInstance() {
        if (andropyService == null) {
            final OkHttpClient okHttpClient = makeOkHttpClient();
            final Retrofit client = makeRetrofit(okHttpClient);
            andropyService = client.create(AndropyService.class);
        }
        return andropyService;
    }

    @NonNull
    private static Retrofit makeRetrofit(OkHttpClient okHttpClient) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @NonNull
    private static OkHttpClient makeOkHttpClient() {
        final HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        //add the interceptor for logging the curl commands
        return new OkHttpClient.Builder()
                //.addInterceptor(new SecurityInterceptor(PrefManager.getInstance()))
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(new CurlLoggingInterceptor())
                .readTimeout(50, TimeUnit.SECONDS)
                .connectTimeout(50, TimeUnit.SECONDS)
                .cache(null)
                .build();
    }
}
