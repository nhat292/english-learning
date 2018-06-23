
package com.onedev.englishlearning.ui.feed.blogs;

import com.onedev.englishlearning.ui.base.MvpPresenter;

/**
 * Created by Nhat on 12/13/17.
 */


public interface BlogMvpPresenter<V extends BlogBaseView>
        extends MvpPresenter<V> {

    void onViewPrepared();
}


