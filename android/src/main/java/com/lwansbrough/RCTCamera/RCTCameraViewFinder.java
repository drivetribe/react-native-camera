package com.lwansbrough.RCTCamera;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.view.TextureView;

import java.util.List;

@SuppressLint("ViewConstructor")
class RCTCameraViewFinder extends TextureView implements TextureView.SurfaceTextureListener {

    private int _cameraType;
    private int _captureMode;
    private SurfaceTexture _surfaceTexture;
    private boolean _isStarting;
    private boolean _isStopping;
    private Camera _camera;

    public RCTCameraViewFinder(Context context, int type) {
        super(context);
        this.setSurfaceTextureListener(this);
        this._cameraType = type;
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        _surfaceTexture = surface;
        startCamera();
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        _surfaceTexture = null;
        stopCamera();
        return true;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {
    }

    public double getRatio() {
        int width = RCTCamera.getInstance().getPreviewWidth(this._cameraType);
        int height = RCTCamera.getInstance().getPreviewHeight(this._cameraType);
        return ((float) width) / ((float) height);
    }

    public void setCameraType(final int type) {
        if (this._cameraType == type) {
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                stopPreview();
                _cameraType = type;
                startPreview();
            }
        }).start();
    }

    public void setCaptureMode(final int captureMode) {
        RCTCamera.getInstance().setCaptureMode(_cameraType, captureMode);
        this._captureMode = captureMode;
    }

    public void setCaptureQuality(String captureQuality) {
        RCTCamera.getInstance().setCaptureQuality(_cameraType, captureQuality);
    }

    public void setTorchMode(int torchMode) {
        RCTCamera.getInstance().setTorchMode(_cameraType, torchMode);
    }

    public void setFlashMode(int flashMode) {
        RCTCamera.getInstance().setFlashMode(_cameraType, flashMode);
    }

    private void startPreview() {
        if (_surfaceTexture != null) {
            startCamera();
        }
    }

    private void stopPreview() {
        if (_camera != null) {
            stopCamera();
        }
    }

    synchronized private void startCamera() {
        if (!_isStarting) {
            _isStarting = true;
            try {
                _camera = RCTCamera.getInstance().acquireCameraInstance(_cameraType);
                Camera.Parameters parameters = RCTCamera.getParameters(_camera);
                // set auto focus
                List<String> focusModes = parameters.getSupportedFocusModes();
                if (focusModes.contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)) {
                    parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
                }
                // set picture size
                // defaults to max available size
                List<Camera.Size> supportedSizes;
                if (_captureMode == RCTCameraModule.RCT_CAMERA_CAPTURE_MODE_STILL) {
                    supportedSizes = parameters.getSupportedPictureSizes();
                } else if (_captureMode == RCTCameraModule.RCT_CAMERA_CAPTURE_MODE_VIDEO) {
                    supportedSizes = RCTCamera.getInstance().getSupportedVideoSizes(_camera);
                } else {
                    throw new RuntimeException("Unsupported capture mode:" + _captureMode);
                }
                Camera.Size optimalPictureSize = RCTCamera.getInstance().getBestSize(
                        supportedSizes,
                        Integer.MAX_VALUE,
                        Integer.MAX_VALUE
                );
                parameters.setPictureSize(optimalPictureSize.width, optimalPictureSize.height);

                _camera.setParameters(parameters);
                _camera.setPreviewTexture(_surfaceTexture);
                _camera.startPreview();
            } catch (NullPointerException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
                stopCamera();
            } finally {
                _isStarting = false;
            }
        }
    }

    synchronized private void stopCamera() {
        if (!_isStopping) {
            _isStopping = true;
            try {
                if (_camera != null) {
                    _camera.stopPreview();
                    // stop sending previews to `onPreviewFrame`
                    _camera.setPreviewCallback(null);
                    RCTCamera.getInstance().releaseCameraInstance(_cameraType);
                    _camera = null;
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                _isStopping = false;
            }
        }
    }

}
