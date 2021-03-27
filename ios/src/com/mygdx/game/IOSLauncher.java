package com.mygdx.game;

import org.robovm.apple.foundation.NSAutoreleasePool;
import org.robovm.apple.uikit.UIApplication;

import com.badlogic.gdx.backends.iosrobovm.IOSApplication;
import com.badlogic.gdx.backends.iosrobovm.IOSApplicationConfiguration;
import com.mygdx.game.network.PlayServices;

public class IOSLauncher extends IOSApplication.Delegate {
    @Override
    protected IOSApplication createApplication() {
        IOSApplicationConfiguration config = new IOSApplicationConfiguration();
        return new IOSApplication(new MyGdxGame(new PlayServices() {
            @Override
            public void startAdvertising() {

            }

            @Override
            public void startDiscovery() {

            }

            @Override
            public void onEndpointFound() {

            }

            @Override
            public void onEndpointLost(String endpointId) {

            }

            @Override
            public void stopAdvertising() {

            }

            @Override
            public void stopDiscovery() {

            }

            @Override
            public String getUserNickname() {
                return null;
            }
        }), config);
    }

    public static void main(String[] argv) {
        NSAutoreleasePool pool = new NSAutoreleasePool();
        UIApplication.main(argv, null, IOSLauncher.class);
        pool.close();
    }
}