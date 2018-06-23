
package com.onedev.englishlearning.ui.feed.opensource;

import com.onedev.englishlearning.ui.base.MvpPresenter;

/**
 * Created by Nhat on 12/13/17.
 */


public interface OpenSourceMvpPresenter<V extends OpenSourceBaseView>
        extends MvpPresenter<V> {

    void onViewPrepared();
}
