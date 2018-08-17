package com.onedev.englishlearning.ui.topic;

import com.onedev.englishlearning.data.network.model.TopicResponse;
import com.onedev.englishlearning.ui.base.BaseView;

public interface TopicBaseView extends BaseView {

    void onGetTopicsSuccess(TopicResponse response);

    void hideRefreshLoading();
}
