package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.assets.AssetsController;
import com.mygdx.game.firebase.FirebaseController;
import com.mygdx.game.firebase.FirebaseInterface;
import com.mygdx.game.screens.navigation.NavigationModel;
import com.mygdx.game.screens.navigation.NavigationController;

public class MyGdxGame extends Game {

	private NavigationController navigationController;
	private FirebaseInterface firebaseInterface;
	private FirebaseController firebaseController;
	private AssetsController assetsController;

	public MyGdxGame(FirebaseInterface firebaseInterface) {
		this.navigationController = NavigationController.getInstance();
		this.firebaseInterface = firebaseInterface;
		this.firebaseController = FirebaseController.getInstance();
	}

	@Override
	public void create () {
		this.navigationController.changeScreen(NavigationModel.NavigationScreen.MAINMENU);
		firebaseController.setFirebaseInterface(firebaseInterface);
	}

	@Override
	public void render () {
		navigationController.getScreen().render(Gdx.graphics.getDeltaTime());
	}
	
	@Override
	public void dispose () {
		if (navigationController != null) {
			navigationController.dispose();
		}
		this.assetsController = AssetsController.getInstance();
		assetsController.dispose();
	}
}
