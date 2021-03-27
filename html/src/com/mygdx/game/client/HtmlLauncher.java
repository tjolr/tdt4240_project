package com.mygdx.game.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.network.PlayServices;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                // Resizable application, uses available space in browser
                return new GwtApplicationConfiguration(true);
                // Fixed size application:
                //return new GwtApplicationConfiguration(480, 320);
        }

        @Override
        public ApplicationListener createApplicationListener () {
                return new MyGdxGame(new PlayServices() {
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
                });
        }
}