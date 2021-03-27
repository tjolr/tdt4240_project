package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.network.PlayServices;

public class DesktopLauncher  {

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new MyGdxGame(new PlayServices() {
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

}
