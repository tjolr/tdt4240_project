package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.screens.navigation.NavigatorController;

public class MyGdxGame extends Game {

	private NavigatorController navigatorController;
	@Override
	public void create () {
		this.navigatorController = new NavigatorController();
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
