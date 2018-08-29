package com.onedev.englishlearning.viewholder;

import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.onedev.englishlearning.R;
import com.onedev.englishlearning.ui.base.BaseViewHolder;
import com.onedev.englishlearning.utils.Helper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ConversationViewHolder extends BaseViewHolder {

    @BindView(R.id.txtContent)
    TextView txtContent;
    @BindView(R.id.cardViewItem)
    CardView cardViewItem;
    @BindView(R.id.imgBtnTranslate)
    ImageButton imgBtnTranslate;

    public ConversationViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    protected void clear() {

    }

    public void setContent(String content) {
        txtContent.setText(content.trim());
    }


    public void setOnTranslateClick(String textToTranslate) {
        imgBtnTranslate.setOnClickListener(view -> Helper.openGoogleTranslate(itemView.getContext(), textToTranslate));
    }
}
