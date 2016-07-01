package com.reshout;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import com.reshout.modules.NotificationModule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReShoutPackage implements ReactPackage {

    @Override
    public List<NativeModule> createNativeModules(final ReactApplicationContext reactContext) {
        return new ArrayList<NativeModule>() {{
            add(new NotificationModule(reactContext));
        }};
    }

    @Override
    public List<Class<? extends JavaScriptModule>> createJSModules() {
        return Collections.emptyList();
    }

    @Override
    public List<ViewManager> createViewManagers(final ReactApplicationContext reactContext) {
        return Collections.emptyList();
    }
}
