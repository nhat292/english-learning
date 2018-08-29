package com.onedev.englishlearning.ui.conversation;

import com.onedev.englishlearning.data.network.model.ConversationResponse;
import com.onedev.englishlearning.ui.base.BaseView;

public interface ConversationBaseView extends BaseView {

    void onGetConversationSuccess(ConversationResponse response);

}
