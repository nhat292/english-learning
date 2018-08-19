package com.onedev.englishlearning.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.onedev.englishlearning.App;
import com.onedev.englishlearning.R;
import com.onedev.englishlearning.data.model.Sentence;
import com.onedev.englishlearning.utils.AppConstants;
import com.onedev.englishlearning.utils.callback.ListItemClickCallback;
import com.onedev.englishlearning.viewholder.DetailViewHolder;

import java.util.ArrayList;

public class DetailAdapter extends RecyclerView.Adapter<DetailViewHolder> {

    private ArrayList<Sentence> mListItems;
    private ListItemClickCallback mCallback;

    public DetailAdapter(ArrayList<Sentence> items, ListItemClickCallback callback) {
        mListItems = items;
        mCallback = callback;
    }

    @NonNull
    @Override
    public DetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail, parent, false);
        return new DetailViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailViewHolder holder, int position) {
        holder.onBind(position);
        Sentence item = mListItems.get(position);
        if (item.getType() == Sentence.TYPE_NO_SOUND
                && App.getInstance().getmRuntimeObject().getDbNumber() == AppConstants.DATABASE2_NUMBER) {
            holder.setButtonVisibility(View.GONE);
            holder.setCardBackgroundVisibility(false);
        } else {
            holder.setButtonVisibility(View.VISIBLE);
            holder.setCardBackgroundVisibility(true);
        }
        if (item.isPlaying()) {
            holder.setButtonResource(R.drawable.ic_pause);
        } else {
            holder.setButtonResource(R.drawable.ic_play);
        }
        holder.setContent(item.getPhrase());
        holder.onItemClick(mCallback);
        holder.setOnTranslateClick(item.getPhrase());
    }

    @Override
    public int getItemCount() {
        return mListItems.size();
    }
}
