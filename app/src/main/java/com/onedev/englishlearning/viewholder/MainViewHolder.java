package com.onedev.englishlearning.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.onedev.englishlearning.R;
import com.onedev.englishlearning.ui.base.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainViewHolder extends BaseViewHolder {

    @BindView(R.id.imgItem)
    ImageView imgItem;
    @BindView(R.id.txtTitle)
    TextView txtTitle;
    @BindView(R.id.txtDescription)
    TextView txtDescription;

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
}
