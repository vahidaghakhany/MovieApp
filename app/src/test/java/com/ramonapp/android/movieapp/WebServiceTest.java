package com.ramonapp.android.movieapp;


import com.ramonapp.android.movieapp.dto.ShowRsp;
import com.ramonapp.android.movieapp.retrofitInterface.ShowsService;

import junit.framework.Assert;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by vahid on 11/4/2017.
 */

public class WebServiceTest {

    ShowRsp show;

    @Test
    public void testShows() throws IOException{

        ShowsService  service = ShowsService.retrofit.create(ShowsService.class);
        service.resp()
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .subscribe(new SingleObserver<List<ShowRsp>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull List<ShowRsp> showRsps) {
                        show = showRsps.get(0);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });

        Assert.assertNotNull(show);
    }

}
