
package com.onedev.englishlearning.ui.main.rating;

import com.onedev.englishlearning.ui.base.MvpPresenter;

/**
 * Created by Nhat on 12/13/17.
 */


public interface RatingDialogMvpPresenter<V extends RatingDialogBaseView> extends MvpPresenter<V> {

    void onRatingSubmitted(float rating, String message);

    void onCancelClicked();

    void onLaterClicked();

    void onPlayStoreRatingClicked();
}
