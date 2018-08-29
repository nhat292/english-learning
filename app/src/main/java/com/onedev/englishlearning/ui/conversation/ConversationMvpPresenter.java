package com.onedev.englishlearning.ui.conversation;

import com.onedev.englishlearning.dagger.PerActivity;
import com.onedev.englishlearning.ui.base.MvpPresenter;

@PerActivity
public interface ConversationMvpPresenter<V extends ConversationBaseView> extends MvpPresenter<V> {


    void getConversations(int categoryId);

}
