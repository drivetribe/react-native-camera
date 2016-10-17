/**
 * Created by rpopovici on 23/03/16.
 */

package com.lwansbrough.RCTCamera;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.Surface;

import com.facebook.react.bridge.ReactApplicationContext;

class RCTSensorOrientationChecker {

    private final SensorManager mSensorManager;
    private final SensorEventListener mSensorEventListener;

    private RCTSensorOrientationListener mListener = null;
    private int mOrientation = 0;

    RCTSensorOrientationChecker(ReactApplicationContext reactContext) {
        mSensorManager = (SensorManager) reactContext.getSystemService(Context.SENSOR_SERVICE);
        mSensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float x = event.values[0];
                float y = event.values[1];

                if (x < 5 && x > -5 && y > 5)
                    mOrientation = Surface.ROTATION_0; // portrait
                else if (x < -5 && y < 5 && y > -5)
                    mOrientation = Surface.ROTATION_270; // right
                else if (x < 5 && x > -5 && y < -5)
                    mOrientation = Surface.ROTATION_180; // upside down
                else if (x > 5 && y < 5 && y > -5)
                    mOrientation = Surface.ROTATION_90; // left

                if (mListener != null) {
                    mListener.orientationEvent();
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }
        };
    }

    /**
     * Call on activity onResume()
     */
    void onResume() {
        mSensorManager.registerListener(mSensorEventListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }

    /**
     * Call on activity onPause()
     */
    void onPause() {
        mSensorManager.unregisterListener(mSensorEventListener);
    }

    int getOrientation() {
        return mOrientation;
    }

    void registerOrientationListener(RCTSensorOrientationListener listener) {
        this.mListener = listener;
    }

    void unregisterOrientationListener() {
        mListener = null;
    }
}
