package com.mygdx.game.screens.settings;

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
import com.mygdx.game.screens.navigation.NavigatorController;

public class SettingsView implements Screen {

    private static final String TEXT_MAIN_MENU = "MAIN MENU";
    private Stage stage;
    private NavigatorController navigatorController;

    public SettingsView(NavigatorController navigatorController) {
        this.navigatorController = navigatorController;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        Skin skin = new Skin(Gdx.files.internal("skin/neon-ui.json"));

        TextButton mainMenuButton = new TextButton(TEXT_MAIN_MENU, skin);
        float textSize = 6f;
        mainMenuButton.getLabel().setFontScale(textSize);

        table.add(mainMenuButton).fillX().uniformX();

        table.row().pad(20, 0 , 20, 0);

        mainMenuButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                navigatorController.changeScreen(NavigationModel.NavigationScreen.MAINMENU);
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

    }
}
