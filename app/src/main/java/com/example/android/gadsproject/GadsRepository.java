package com.example.android.gadsproject;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class GadsRepository {
    private static GadsRepository sGadsRepository;
    private JsonPlaceholderApi mJsonPlaceholderApi;

    public GadsRepository() {
        // mJsonPlaceholderApi = RetrofitService.createService(JsonPlaceholderApi.class);

        Retrofit learningRetrofit = new Retrofit.Builder()
                .baseUrl("https://gadsapi.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mJsonPlaceholderApi = learningRetrofit.create(JsonPlaceholderApi.class);


    }

    public static GadsRepository getInstance() {
        if (sGadsRepository == null) {
            sGadsRepository = new GadsRepository();
        }
        return sGadsRepository;
    }

    public MutableLiveData<Gads> getGads() {
        final MutableLiveData<Gads> gadsMutableLiveData = new MutableLiveData<>();

//         mJsonPlaceholderApi.getGads().enqueue(new Callback<List<Gads>>() {
//             @Override
//             public void onResponse(Call<List<Gads>> call, Response<List<Gads>> response) {
//                 if (response.isSuccessful()){
//                     List<Gads> gads = response.body();
//
//                     for (Gads gad: gads){
//                         String name = gad.getName();
//                         int hours = gad.getHours();
//                         String country = gad.getCountry();
//                         String url = gad.getUrl();
//                         gadsMutableLiveData.setValue(new Gads(name, hours, country, url));
//                     }
//
//                     //gadsMutableLiveData.setValue((Gads) response.body());
//                 }
//             }
//
//             @Override
//             public void onFailure(Call<List<Gads>> call, Throwable t) {
//                 gadsMutableLiveData.setValue(null);
//             }
//         });

        return gadsMutableLiveData;
    }
}
