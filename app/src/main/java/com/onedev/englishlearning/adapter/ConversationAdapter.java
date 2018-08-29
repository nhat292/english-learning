package com.onedev.englishlearning.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.onedev.englishlearning.R;
import com.onedev.englishlearning.data.model.Conversation;
import com.onedev.englishlearning.viewholder.ConversationViewHolder;

import java.util.ArrayList;

public class ConversationAdapter extends RecyclerView.Adapter<ConversationViewHolder> {

    private ArrayList<Conversation> mListItems;

    public ConversationAdapter(ArrayList<Conversation> items) {
        mListItems = items;
    }

    @NonNull
    @Override
    public ConversationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_conversation, parent, false);
        return new ConversationViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ConversationViewHolder holder, int position) {
        holder.onBind(position);
        Conversation item = mListItems.get(position);
        holder.setContent(item.getPerson() + ":   " + item.getContent());
        holder.setOnTranslateClick(item.getContent());
    }

    @Override
    public int getItemCount() {
        return mListItems.size();
    }
}
