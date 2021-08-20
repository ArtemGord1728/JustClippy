package com.smart.videored.core.utils.photoeditor;

import android.graphics.Bitmap;

public interface OnSaveBitmap {
    void onBitmapReady(Bitmap bitmap);

    void onFailure(Exception exc);
}
