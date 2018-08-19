package com.onedev.englishlearning.viewholder;

import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.onedev.englishlearning.R;
import com.onedev.englishlearning.ui.base.BaseViewHolder;
import com.onedev.englishlearning.utils.callback.ListItemClickCallback;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainViewHolder extends BaseViewHolder {

    @BindView(R.id.imgItem)
    ImageView imgItem;
    @BindView(R.id.txtTitle)
    TextView txtTitle;
    @BindView(R.id.txtDescription)
    TextView txtDescription;
    @BindView(R.id.cardViewItem)
    CardView cardViewItem;

    public MainViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    protected void clear() {

    }

    public void setImage(int resId) {
        imgItem.setImageResource(resId);
    }

    public void setTitle(String title) {
        txtTitle.setText(title);
    }

    public void setDescription(String description) {
        txtDescription.setText(description);
    }

    public void onItemClick(ListItemClickCallback callback) {
        cardViewItem.setOnClickListener(view -> {
            if(callback != null) {
                callback.onClick(getCurrentPosition(), 0);
            }
        });
    }
}
