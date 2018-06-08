package org.reactnative.camera;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

import java.util.Collections;
import java.util.List;

/**
 * Created by jgfidelis on 02/02/18.
 */

public class RNCameraPackage implements ReactPackage {
    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactApplicationContext) {
        return Collections.<NativeModule>singletonList(
                new CameraModule(reactApplicationContext)
        );
    }

    // Deprecated in RN 0.47
    public List<Class<? extends JavaScriptModule>> createJSModules() {
        return Collections.emptyList();
    }

    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactApplicationContext) {
        return Collections.<ViewManager>singletonList(
                new CameraViewManager()
        );
    }
}
