package com.onedev.englishlearning.ui.detail;

import com.onedev.englishlearning.data.network.model.SentenceResponse;
import com.onedev.englishlearning.ui.base.BaseView;

public interface DetailBaseView extends BaseView {

    void onGetSentencesSuccess(SentenceResponse response);

    void hideRefreshLoading();
}
