package com.example;

import com.facebook.jni.HybridData;
import com.facebook.react.ReactPackage;
import com.facebook.react.ReactPackageTurboModuleManagerDelegate;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.soloader.SoLoader;

import java.util.List;

public class ExampleTurboModuleManagerDelegate extends ReactPackageTurboModuleManagerDelegate {

    private static volatile boolean sIsSoLibraryLoaded;

    protected ExampleTurboModuleManagerDelegate(ReactApplicationContext reactApplicationContext, List<ReactPackage> packages) {
        super(reactApplicationContext, packages);
    }

    protected native HybridData initHybrid();

    public static class Builder extends ReactPackageTurboModuleManagerDelegate.Builder {
        protected ExampleTurboModuleManagerDelegate build(
                ReactApplicationContext context, List<ReactPackage> packages) {
            return new ExampleTurboModuleManagerDelegate(context, packages);
        }
    }

    @Override
    protected synchronized void maybeLoadOtherSoLibraries() {
        // Prevents issues with initializer interruptions.
        if (!sIsSoLibraryLoaded) {
            SoLoader.loadLibrary("example_appmodules");
            sIsSoLibraryLoaded = true;
        }
    }
}
