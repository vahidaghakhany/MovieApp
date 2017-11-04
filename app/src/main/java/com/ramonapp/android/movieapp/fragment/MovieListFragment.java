package com.ramonapp.android.movieapp.fragment;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ramonapp.android.movieapp.R;
import com.ramonapp.android.movieapp.adapter.MovieListAdapter;
import com.ramonapp.android.movieapp.dto.ShowRsp;
import com.ramonapp.android.movieapp.interfaces.FragmentCallback;
import com.ramonapp.android.movieapp.retrofitInterface.ShowsService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class MovieListFragment extends Fragment{

    FragmentCallback fragmentCallback;
    RecyclerView movieRcl;
    MovieListAdapter adapter;
    List<ShowRsp> shows;

    Disposable disposable;

    public MovieListFragment() {
    }

    public static MovieListFragment newInstance() {
        MovieListFragment fragment = new MovieListFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentCallback = (FragmentCallback) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden){
            fragmentCallback.setToolbarLayout(false);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_list, container, false);

        movieRcl = (RecyclerView) view.findViewById(R.id.movie_rcl);

        initMovieRecyclerView();

        loadMovieList();


        return view;
    }

    private void initMovieRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        movieRcl.setLayoutManager(layoutManager);
        shows = new ArrayList<>();
        adapter = new MovieListAdapter(getContext(), shows);
        movieRcl.setAdapter(adapter);
        adapter.setOnItemClick(new MovieListAdapter.OnItemClickListener() {
            @Override
            public void onClick(ShowRsp show) {
                Fragment fragment = MovieDetailFragment.newInstance(show);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.hide(getFragmentManager().findFragmentById(R.id.fragment_container));
                transaction.add(R.id.fragment_container, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }


    private void loadMovieList() {
        ShowsService service = ShowsService.retrofit.create(ShowsService.class);
        service.resp()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new SingleObserver<List<ShowRsp>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable = d;
                        fragmentCallback.showWaiting();
                    }

                    @Override
                    public void onSuccess(@NonNull List<ShowRsp> showRsps) {
                        fragmentCallback.dismissWaiting();
                        doOnSuccessLoadMovies(showRsps);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        fragmentCallback.dismissWaiting();
                        showAlert();
                    }

                });
    }

    private void doOnSuccessLoadMovies(List<ShowRsp> showRsps) {
        shows.clear();
        shows.addAll(showRsps);
        adapter.notifyDataSetChanged();
    }

    private void showAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder
                .setMessage("Network Error")
                .setCancelable(false)
                .setPositiveButton("retry", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        loadMovieList();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }
}
