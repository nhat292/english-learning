package com.onedev.englishlearning.viewholder;

import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.onedev.englishlearning.R;
import com.onedev.englishlearning.ui.base.BaseViewHolder;
import com.onedev.englishlearning.utils.Helper;
import com.onedev.englishlearning.utils.callback.ListItemClickCallback;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TopicViewHolder extends BaseViewHolder {

    @BindView(R.id.txtTitle)
    TextView txtTitle;
    @BindView(R.id.cardViewItem)
    CardView cardViewItem;
    @BindView(R.id.imgBtnTranslate)
    ImageButton imgBtnTranslate;

    public TopicViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    protected void clear() {

    }

    public void setTitle(String title) {
        txtTitle.setText(title.trim());
    }

    public void onItemClick(ListItemClickCallback callback) {
        cardViewItem.setOnClickListener(view -> {
            if(callback != null) {
                callback.onClick(getCurrentPosition(), 0);
            }
        });
    }

    public void setOnTranslateClick(String textToTranslate) {
        imgBtnTranslate.setOnClickListener(view -> Helper.openGoogleTranslate(itemView.getContext(), textToTranslate));
    }
}
