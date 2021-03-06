package com.onedev.englishlearning.viewholder;

import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.onedev.englishlearning.R;
import com.onedev.englishlearning.ui.base.BaseViewHolder;
import com.onedev.englishlearning.utils.Helper;
import com.onedev.englishlearning.utils.ViewUtils;
import com.onedev.englishlearning.utils.callback.ListItemClickCallback;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailViewHolder extends BaseViewHolder {

    @BindView(R.id.txtContent)
    TextView txtContent;
    @BindView(R.id.cardViewItem)
    CardView cardViewItem;
    @BindView(R.id.imgBtnPlayPause)
    ImageButton imgBtnPlayPause;
    @BindView(R.id.imgBtnTranslate)
    ImageButton imgBtnTranslate;

    public DetailViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    protected void clear() {

    }

    public void setContent(String content) {
        txtContent.setText(content.trim());
    }

    public void setButtonVisibility(int visibility) {
        imgBtnPlayPause.setVisibility(visibility);
    }

    public void setButtonResource(int resId) {
        imgBtnPlayPause.setImageResource(resId);
    }

    public void setCardBackgroundVisibility(boolean isVisibility) {
        if (isVisibility) {
            cardViewItem.setBackgroundColor(itemView.getContext().getResources().getColor(android.R.color.white));
            cardViewItem.setCardElevation(ViewUtils.dpToPx(itemView.getResources().getDimension(R.dimen.item_card_elevation)));
            cardViewItem.setRadius(ViewUtils.dpToPx(itemView.getResources().getDimension(R.dimen.item_card_corner_radius)));
        } else {
            cardViewItem.setBackgroundColor(itemView.getContext().getResources().getColor(android.R.color.transparent));
            cardViewItem.setCardElevation(0);
            cardViewItem.setRadius(0);
        }
    }

    public void onItemClick(ListItemClickCallback callback) {
        imgBtnPlayPause.setOnClickListener(view -> {
            if (callback != null) {
                callback.onClick(getCurrentPosition(), 0);
            }
        });
    }

    public void setOnTranslateClick(String textToTranslate) {
        imgBtnTranslate.setOnClickListener(view -> Helper.openGoogleTranslate(itemView.getContext(), textToTranslate));
    }
}
