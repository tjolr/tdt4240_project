package com.mygdx.game.screens.main_menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.screens.navigation.NavigationModel;
import com.mygdx.game.screens.navigation.NavigationController;

public class MainMenuView implements Screen {

    private static final String TEXT_PLAY = "PLAY NOW";
    private static final String TEXT_SETTINGS = "SETTINGS";
    private Stage stage;
    private NavigationController navigationController;

    public MainMenuView(NavigationController navigationController) {
        this.navigationController = navigationController;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        Skin skin = new Skin(Gdx.files.internal("skin/neon-ui.json"));

        TextButton playButton = new TextButton(TEXT_PLAY, skin);
        TextButton settingsButton = new TextButton(TEXT_SETTINGS, skin);

        float textSize = 6f;
        playButton.getLabel().setFontScale(textSize);
        settingsButton.getLabel().setFontScale(textSize);

        table.add(playButton).fillX().uniformX();

        table.row().pad(20, 0 , 20, 0);

        table.add(settingsButton).fillX().uniformX();

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
