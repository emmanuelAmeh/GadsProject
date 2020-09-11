package com.example.android.gadsproject;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    private static Retrofit learningRetrofit = new Retrofit.Builder()
            .baseUrl("https://gadsapi.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static <S> S createService(Class<S> serviceClass) {
        return learningRetrofit.create(serviceClass);
    }

}
