package com.lwansbrough.RCTCamera;

import android.hardware.Camera;

class CameraInfoWrapper {
    final Camera.CameraInfo info;
    int rotation = 0;
    int previewWidth = -1;
    int previewHeight = -1;

    CameraInfoWrapper(Camera.CameraInfo info) {
        this.info = info;
    }
}
