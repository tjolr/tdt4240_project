package com.mygdx.game.screens.set_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.assets.AssetsController;
import com.mygdx.game.game_state.GlobalStateController;
import com.mygdx.game.screens.navigation.NavigationModel;
import com.mygdx.game.screens.navigation.NavigationController;

public class SetNameView implements Screen {
    private NavigationController navigationController;
    private GlobalStateController globalStateController;
    private Stage stage;
    private AssetsController assetsController;


    public SetNameView(NavigationController navigationController){
        this.navigationController = navigationController;
        this.globalStateController = GlobalStateController.getInstance();
        this.assetsController = AssetsController.getInstance();

        this.stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {
        Table rootTable = new Table();
        rootTable.setFillParent(true);
        stage.addActor(rootTable);

        final TextField usernameField = new TextField("Name",  assetsController.getSkin());
        usernameField.setMaxLength(12);

        TextButton submitButton = new TextButton("Submit",  assetsController.getSkin());

        rootTable.add(usernameField).width(500);
        rootTable.row().padTop(60);
        rootTable.add(submitButton).width(500).uniformX();

        submitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                globalStateController.setUsername(usernameField.getText());
                navigationController.changeScreen(NavigationModel.NavigationScreen.GAMESETUP);
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
