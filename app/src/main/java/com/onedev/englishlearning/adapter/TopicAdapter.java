package com.onedev.englishlearning.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.onedev.englishlearning.R;
import com.onedev.englishlearning.data.model.Topic;
import com.onedev.englishlearning.utils.callback.ListItemClickCallback;
import com.onedev.englishlearning.viewholder.TopicViewHolder;

import java.util.ArrayList;

public class TopicAdapter extends RecyclerView.Adapter<TopicViewHolder> {

    private ArrayList<Topic> mListItems;
    private ListItemClickCallback mCallback;

    public TopicAdapter(ArrayList<Topic> items, ListItemClickCallback callback) {
        mListItems = items;
        mCallback = callback;
    }

    @NonNull
    @Override
    public TopicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_topic, parent, false);
        return new TopicViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TopicViewHolder holder, int position) {
        holder.onBind(position);
        Topic item = mListItems.get(position);
        holder.setTitle(String.valueOf(position + 1) + ". " + item.getName());
        holder.onItemClick(mCallback);
        holder.setOnTranslateClick(item.getName());
    }

    @Override
    public int getItemCount() {
        return mListItems.size();
    }
}
