package com.onedev.englishlearning.utils.permission;

import android.os.Handler;
import android.os.Looper;

import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;

/**
 * Created by Nhat on 3/22/18.
 */

public class BackgroundThreadPermissionListener extends SinglePermissionListener {

    private Handler handler = new Handler(Looper.getMainLooper());


    public BackgroundThreadPermissionListener(PermissionResultListener listener) {
        super(listener);
    }

    @Override
    public void onPermissionGranted(PermissionGrantedResponse response) {
        handler.post(() -> BackgroundThreadPermissionListener.super.onPermissionGranted(response));
    }

    @Override
    public void onPermissionDenied(PermissionDeniedResponse response) {
        handler.post(() -> BackgroundThreadPermissionListener.super.onPermissionDenied(response));
    }

    @Override
    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
        handler.post(() -> BackgroundThreadPermissionListener.super.onPermissionRationaleShouldBeShown(permission, token));
    }
}
