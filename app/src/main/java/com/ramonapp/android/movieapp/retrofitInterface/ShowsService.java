package com.ramonapp.android.movieapp.retrofitInterface;


import com.ramonapp.android.movieapp.constant.Constant;
import com.ramonapp.android.movieapp.dto.ShowRsp;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface ShowsService {
    @GET("shows")
    Single<List<ShowRsp>> resp();

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Constant.URL_SRV)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
}
