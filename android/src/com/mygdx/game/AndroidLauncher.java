package com.mygdx.game;

import android.os.Bundle;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.mygdx.game.firebase.FirebaseService;


public class AndroidLauncher extends AndroidApplication {
	MyGdxGame game;
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		this.game = new MyGdxGame(new FirebaseService());
		initialize(game, config);
	}

	// dispose game when application is destroyed
	@Override
	protected void onDestroy() {
		super.onDestroy();
		game.dispose();
	}
}
