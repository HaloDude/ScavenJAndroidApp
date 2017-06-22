package com.pigeonstudios.scavenj.controller.recyclerviewcontroller;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.pigeonstudios.scavenj.R;
import com.pigeonstudios.scavenj.model.ScavenJCard;

import java.util.List;

/**
 * Created by Dennis on 6/17/2017.
 */

public class AvailableScavenJAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<ScavenJCard> cards;

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    private OnLoadMoreListener onLoadMoreListener;
    private boolean isLoading;
    private Activity activity;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;

    public AvailableScavenJAdapter(List<ScavenJCard> cards, RecyclerView recyclerView, Activity activity){
        this.cards = cards;
        this.activity = activity;

        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new  RecyclerView.OnScrollListener(){
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if(!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)){
                    if(onLoadMoreListener != null){
                        onLoadMoreListener.onLoadMore();
                    }
                    isLoading = true;
                }
            }
        });

    }

    /**
     * Card view holder
     */
    private class ScavenJViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView name;
        TextView description;
        ImageView photo;


        public ScavenJViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.scavenJCardView);
            name = (TextView)itemView.findViewById(R.id.scavenJName);
            description = (TextView) itemView.findViewById(R.id.scavenJDescription);
            photo = (ImageView) itemView.findViewById(R.id.cardPhoto);
        }
    }

    /**
     * Loading item view holder
     */
    private class LoadingViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public LoadingViewHolder(View view){
            super(view);
            progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        }
    }






    @Override
    public int getItemViewType(int position) {
        return cards.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == VIEW_TYPE_ITEM){
            View view = LayoutInflater.from(activity).inflate(R.layout.scavenj_card_view, parent, false);
            return new ScavenJViewHolder(view);
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(activity).inflate(R.layout.progress_bar, parent, false);
            return new LoadingViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof ScavenJViewHolder){
            ScavenJViewHolder scavenJViewHolder = (ScavenJViewHolder) holder;
            scavenJViewHolder.name.setText(cards.get(position).getName());
            scavenJViewHolder.description.setText(cards.get(position).getDescription());
            scavenJViewHolder.photo.setImageResource(cards.get(position).getImgId());
        } else if(holder instanceof LoadingViewHolder){
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }

        //touch controller for every card
        //TODO implement the touch stuff
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d("Click", String.valueOf(position));

            }
        });
    }

    @Override
    public int getItemCount() {
        //if cards are not set then just return 0 else return the size
        return cards == null ? 0 : cards.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public void setOnLoadMoreListener(OnLoadMoreListener OnLoadListener) {
        this.onLoadMoreListener = OnLoadListener;
    }

    public void setLoaded(){
        isLoading = false;
    }

}
