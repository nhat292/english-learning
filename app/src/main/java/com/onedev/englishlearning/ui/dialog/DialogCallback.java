package com.onedev.englishlearning.ui.dialog;

/**
 * Created by Nguyen Van Nhat on 8/20/2018
 */
public interface DialogCallback<T> {

    void onNegative(T dialog);

    void onPositive(T dialog);
}

