package com.mygdx.game.screens.settings;

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

public class SettingsView implements Screen {

    private static final String TEXT_MAIN_MENU = "MAIN MENU";
    private Stage stage;
    private NavigationController navigationController;
    private AssetsController assetsController;

    public SettingsView(NavigationController navigationController) {
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

        Label settingsTitle = new Label("Settings: ", assetsController.getSkin());
        TextButton darkBackgroundButton = new TextButton("Dark theme", assetsController.getSkin());
        TextButton lightBackgroundButton = new TextButton("Light theme", assetsController.getSkin());

        TextButton mainMenuButton = new TextButton(TEXT_MAIN_MENU, assetsController.getSkin());

        table.add(settingsTitle);
        table.row().padTop(50);
        table.add(darkBackgroundButton).uniformX();
        table.row();
        table.add(lightBackgroundButton).uniformX();

        table.row().padTop(70);
        table.add(mainMenuButton).uniformX();

        table.row().pad(20, 0 , 20, 0);

        darkBackgroundButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                assetsController.setDarkTheme();
            }
        });

        lightBackgroundButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                assetsController.setLightTheme();
            }
        });

        mainMenuButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                navigationController.changeScreen(NavigationModel.NavigationScreen.MAINMENU);
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
