package com.pigeonstudios.scavenj.controller;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pigeonstudios.scavenj.R;
import com.pigeonstudios.scavenj.model.ScavenJCard;

import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Dennis on 6/17/2017.
 */

public class AvailableScavenJAdapter extends RecyclerView.Adapter<AvailableScavenJAdapter.ScavenJViewHolder>{
    List<ScavenJCard> cards;

    public AvailableScavenJAdapter(List<ScavenJCard> cards){
        this.cards = cards;
    }

    public static class ScavenJViewHolder extends RecyclerView.ViewHolder {
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



    @Override
    public ScavenJViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.scavenj_card_view, parent, false);
        ScavenJViewHolder card = new ScavenJViewHolder(v);
        return card;
    }

    @Override
    public void onBindViewHolder(final ScavenJViewHolder holder, final int position) {
        holder.name.setText(cards.get(position).getName());
        holder.description.setText(cards.get(position).getDescription());
        holder.photo.setImageResource(cards.get(position).getImgId());

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
        return cards.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
