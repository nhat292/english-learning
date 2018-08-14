package com.onedev.englishlearning.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.onedev.englishlearning.R;
import com.onedev.englishlearning.data.model.MainLibrary;
import com.onedev.englishlearning.viewholder.MainViewHolder;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainViewHolder> {

    private ArrayList<MainLibrary> mListItems;

    public MainAdapter(ArrayList<MainLibrary> listItems) {
        mListItems = listItems;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_library, parent, false);
        return new MainViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        holder.onBind(position);
        MainLibrary item = mListItems.get(position);
        holder.setImage(item.getResId());
        holder.setTitle(item.getTitle());
        holder.setDescription(item.getDescription());
    }

    @Override
    public int getItemCount() {
        return mListItems.size();
    }
}
