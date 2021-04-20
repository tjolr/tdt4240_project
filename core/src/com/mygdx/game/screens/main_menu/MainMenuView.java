package com.mygdx.game.screens.main_menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.assets.AssetsController;
import com.mygdx.game.screens.navigation.NavigationModel;
import com.mygdx.game.screens.navigation.NavigationController;

public class MainMenuView implements Screen {

    private Stage stage;
    private NavigationController navigationController;
    private AssetsController assetsController;

    public MainMenuView(NavigationController navigationController) {
        this.navigationController = navigationController;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        this.assetsController = AssetsController.getInstance();
    }

    @Override
    public void show() {
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        Label gameTitle = new Label("Pandemic survival",  assetsController.getSkin());
        gameTitle.setFontScale(5f);

        TextButton playButton = new TextButton("PLAY NOW",  assetsController.getSkin());
        TextButton settingsButton = new TextButton("SETTINGS",  assetsController.getSkin());

        table.add(gameTitle);
        table.row().padTop(50);

        table.add(playButton).uniformX();

        table.row().pad(20, 0 , 20, 0);

        table.add(settingsButton).uniformX();

        playButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
            navigationController.changeScreen(NavigationModel.NavigationScreen.SET_NAME);
            }
        });

        settingsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                navigationController.changeScreen(NavigationModel.NavigationScreen.SETTINGS);
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        this.assetsController.renderMenuBackground();

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
