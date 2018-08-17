package com.onedev.englishlearning.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.onedev.englishlearning.R;
import com.onedev.englishlearning.data.model.Category;
import com.onedev.englishlearning.utils.callback.ListItemClickCallback;
import com.onedev.englishlearning.viewholder.CategoryViewHolder;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder> {

    private ArrayList<Category> mListItems;
    private ListItemClickCallback mCallback;

    public CategoryAdapter(ArrayList<Category> items, ListItemClickCallback callback) {
        mListItems = items;
        mCallback = callback;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.onBind(position);
        Category item = mListItems.get(position);
        holder.setTitle(String.valueOf(position + 1) + ". " + item.getName());
        holder.onItemClick(mCallback);
    }

    @Override
    public int getItemCount() {
        return mListItems.size();
    }
}
