package com.ramonapp.android.movieapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.bumptech.glide.Glide;
import com.ramonapp.android.movieapp.R;
import com.ramonapp.android.movieapp.dto.ShowRsp;

import java.util.List;

/**
 * Created by vahid on 10/31/2017.
 */

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MyViewHolder> {

    public interface OnItemClickListener{
        void onClick(ShowRsp show);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageButton movieImg;

        public MyViewHolder(final View itemView) {
            super(itemView);
            movieImg = (ImageButton) itemView.findViewById(R.id.movie_img);
        }
    }

    private OnItemClickListener onItemClickListener;
    private List<ShowRsp> shows;
    private Context context;

    public MovieListAdapter(Context context, List<ShowRsp> shows) {
        this.shows = shows;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_row,parent,false);
        view.getLayoutParams().width = parent.getMeasuredWidth() / 2;
        view.getLayoutParams().height = (int) (view.getLayoutParams().width * 1.4f);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final ShowRsp show = shows.get(position);
        Glide.with(context)
                .load(show.getImage().getMedium())
                .centerCrop()
                .placeholder(R.drawable.place_holder)
                .crossFade()
                .into(holder.movieImg)
        ;

        holder.movieImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onClick(show);
            }
        });

    }

    public void setOnItemClick(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return shows.size();
    }


}
