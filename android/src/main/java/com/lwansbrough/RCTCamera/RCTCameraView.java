package com.lwansbrough.RCTCamera;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.SensorManager;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.facebook.react.uimanager.ThemedReactContext;

@SuppressLint("ViewConstructor")
public class RCTCameraView extends ViewGroup {

    private final WindowManager windowManager;
    private final OrientationEventListener _orientationListener;

    private RCTCameraViewFinder _viewFinder = null;
    private int displayOrientation = -1;
    private int _aspect = RCTCameraModule.RCT_CAMERA_ASPECT_FIT;
    private int _torchMode = -1;
    private int _flashMode = -1;

    public RCTCameraView(ThemedReactContext themedReactContext) {
        super(themedReactContext);
        RCTCamera.getInstance();
        Context appContext = themedReactContext.getApplicationContext();
        this.windowManager = (WindowManager) appContext.getSystemService(Context.WINDOW_SERVICE);

        _orientationListener = new OrientationEventListener(appContext, SensorManager.SENSOR_DELAY_NORMAL) {
            @Override
            public void onOrientationChanged(int orientation) {
                if (setActualDeviceOrientation()) {
                    layoutViewFinder();
                }
            }
        };

        if (_orientationListener.canDetectOrientation()) {
            _orientationListener.enable();
        } else {
            _orientationListener.disable();
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        layoutViewFinder(left, top, right, bottom);
    }

    @Override
    public void onViewAdded(View child) {
        if (this._viewFinder == child) return;
        // remove and read view to make sure it is in the back.
        // TODO: figure out why there was a z order issue in the first place and fix accordingly.
        this.removeView(this._viewFinder);
        this.addView(this._viewFinder, 0);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        _orientationListener.enable();
    }

    @Override
    protected void onDetachedFromWindow() {
        _orientationListener.disable();
        super.onDetachedFromWindow();
    }

    public void setAspect(int aspect) {
        this._aspect = aspect;
        layoutViewFinder();
    }

    public void setCameraType(final int type) {
        if (null != this._viewFinder) {
            this._viewFinder.setCameraType(type);
            RCTCamera.getInstance().adjustPreviewLayout(type);
        } else {
            _viewFinder = new RCTCameraViewFinder(getContext(), type);
            if (-1 != this._flashMode) {
                _viewFinder.setFlashMode(this._flashMode);
            }
            if (-1 != this._torchMode) {
                _viewFinder.setFlashMode(this._torchMode);
            }
            addView(_viewFinder);
        }
    }

    public void setCaptureMode(final int captureMode) {
        if (this._viewFinder != null) {
            this._viewFinder.setCaptureMode(captureMode);
        }
    }

    public void setCaptureQuality(String captureQuality) {
        if (this._viewFinder != null) {
            this._viewFinder.setCaptureQuality(captureQuality);
        }
    }

    public void setTorchMode(int torchMode) {
        this._torchMode = torchMode;
        if (this._viewFinder != null) {
            this._viewFinder.setTorchMode(torchMode);
        }
    }

    public void setFlashMode(int flashMode) {
        this._flashMode = flashMode;
        if (this._viewFinder != null) {
            this._viewFinder.setFlashMode(flashMode);
        }
    }

    public void setOrientation(int orientation) {
        RCTCamera.getInstance().setOrientation(orientation);
        if (this._viewFinder != null) {
            layoutViewFinder();
        }
    }

    private boolean setActualDeviceOrientation() {
        int displayOrientation = getDisplayOrientation();
        if (this.displayOrientation != displayOrientation) {
            this.displayOrientation = displayOrientation;
            RCTCamera.getInstance().setDisplayOrientation(displayOrientation);
            return true;
        } else {
            return false;
        }
    }

    private int getDisplayOrientation() {
        return windowManager.getDefaultDisplay().getRotation();
    }

    private void layoutViewFinder() {
        layoutViewFinder(this.getLeft(), this.getTop(), this.getRight(), this.getBottom());
    }

    private void layoutViewFinder(int left, int top, int right, int bottom) {
        if (null == _viewFinder) {
            return;
        }
        float width = right - left;
        float height = bottom - top;
        int viewfinderWidth;
        int viewfinderHeight;
        double ratio;
        switch (this._aspect) {
            case RCTCameraModule.RCT_CAMERA_ASPECT_FIT:
                ratio = this._viewFinder.getRatio();
                if (ratio * height > width) {
                    viewfinderHeight = (int) (width / ratio);
                    viewfinderWidth = (int) width;
                } else {
                    viewfinderWidth = (int) (ratio * height);
                    viewfinderHeight = (int) height;
                }
                break;
            case RCTCameraModule.RCT_CAMERA_ASPECT_FILL:
                ratio = this._viewFinder.getRatio();
                if (ratio * height < width) {
                    viewfinderHeight = (int) (width / ratio);
                    viewfinderWidth = (int) width;
                } else {
                    viewfinderWidth = (int) (ratio * height);
                    viewfinderHeight = (int) height;
                }
                break;
            default:
                viewfinderWidth = (int) width;
                viewfinderHeight = (int) height;
        }

        int viewFinderPaddingX = (int) ((width - viewfinderWidth) / 2);
        int viewFinderPaddingY = (int) ((height - viewfinderHeight) / 2);

        this._viewFinder.layout(viewFinderPaddingX, viewFinderPaddingY, viewFinderPaddingX + viewfinderWidth, viewFinderPaddingY + viewfinderHeight);
        this.postInvalidate(this.getLeft(), this.getTop(), this.getRight(), this.getBottom());
    }
}
