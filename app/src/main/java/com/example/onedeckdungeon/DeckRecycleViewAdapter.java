package com.example.onedeckdungeon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DeckRecycleViewAdapter extends RecyclerView.Adapter<DeckRecycleViewAdapter.ViewHolder> {

    private List<Card> mCards;
    private LayoutInflater mInflater;

    // data is passed into the constructor
    DeckRecycleViewAdapter(Context context, List<Card> cards) {
        this.mInflater = LayoutInflater.from(context);
        this.mCards = cards;
    }

    // inflates the row layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.cardrowview_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the view and textview in each row
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Card card = mCards.get(position);
        if (card.getFaceUp())
            holder.myImageView.setImageResource(card.getImage());
        else
            holder.myImageView.setImageResource(R.drawable.card_back);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mCards.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView myImageView;

        ViewHolder(View itemView) {
            super(itemView);
            myImageView = itemView.findViewById(R.id.cardImage);
        }

    }

}
