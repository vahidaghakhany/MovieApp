package com.ramonapp.android.movieapp.fragment;


import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ramonapp.android.movieapp.R;
import com.ramonapp.android.movieapp.dto.ShowRsp;
import com.ramonapp.android.movieapp.interfaces.FragmentCallback;


public class MovieDetailFragment extends Fragment implements View.OnClickListener{

    private static final String SHOW_KEY = "show_key";
    private ShowRsp show;
    FragmentCallback fragmentCallback;

    private ImageButton backBtn;
    private ImageView titleImg;
    private TextView nameTxt;
    private TextView ratingTxt;
    private TextView summaryTxt;

    public MovieDetailFragment() {
    }

    public static MovieDetailFragment newInstance(ShowRsp show) {
        MovieDetailFragment fragment = new MovieDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(SHOW_KEY,show);
        fragment.setArguments(bundle);
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);

        backBtn = (ImageButton) view.findViewById(R.id.back_btn);
        titleImg = (ImageView) view.findViewById(R.id.title_img);
        nameTxt = (TextView) view.findViewById(R.id.name_txt);
        ratingTxt = (TextView) view.findViewById(R.id.rating_txt);
        summaryTxt = (TextView) view.findViewById(R.id.summary_txt);

        backBtn.setOnClickListener(this);

        show = (ShowRsp) getArguments().getSerializable(SHOW_KEY);
        fillViews();
        fragmentCallback.setToolbarLayout(true);

        return view;
    }

    private void fillViews() {
        Glide.with(getContext())
                .load(show.getImage().getOriginal())
                .centerCrop()
                .placeholder(R.drawable.place_holder)
                .crossFade()
                .into(titleImg)
        ;
        nameTxt.setText(show.getName());

        StringBuilder builder = new StringBuilder(show.getPremiered());
        builder.append(" • ")
            .append(show.getRuntime())
            .append(" min")
            .append(" • ")
            .append(show.getRating().getAverage())
            .append("/10")
        ;
        ratingTxt.setText(builder.toString());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            summaryTxt.setText(Html.fromHtml(show.getSummary(),Html.FROM_HTML_MODE_COMPACT));
        }else{
            summaryTxt.setText(Html.fromHtml(show.getSummary()));
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
                getActivity().onBackPressed();
        }
    }
}
