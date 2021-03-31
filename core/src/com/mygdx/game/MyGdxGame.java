package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.firebase.FirebaseController;
import com.mygdx.game.firebase.FirebaseInterface;
import com.mygdx.game.screens.navigation.NavigatorController;

public class MyGdxGame extends Game {

	private NavigatorController navigatorController;
	private FirebaseInterface firebaseInterface;
	private FirebaseController firebaseController;

	public MyGdxGame(FirebaseInterface firebaseInterface) {
		this.firebaseInterface = firebaseInterface;
		this.firebaseController = FirebaseController.getInstance();
	}

	@Override
	public void create () {
		this.navigatorController = new NavigatorController();
		firebaseController.setFirebaseInterface(firebaseInterface);
		firebaseController.writeToDb("test", "FirebaseController writes to db!!");
	}

	@Override
	public void render () {
		navigatorController.getScreen().render(Gdx.graphics.getDeltaTime());
	}
	
	@Override
	public void dispose () {
		if (navigatorController != null) {
			navigatorController.dispose();
		}
	}
}
