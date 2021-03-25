package com.mygdx.game.screens.set_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.game_state.GameStateController;
import com.mygdx.game.game_state.GameStateModel;
import com.mygdx.game.screens.game_setup.GameSetupController;
import com.mygdx.game.screens.game_setup.GameSetupModel;
import com.mygdx.game.screens.navigation.NavigationModel;
import com.mygdx.game.screens.navigation.NavigatorController;

public class SetNameView implements Screen {
    private NavigatorController navigatorController;
    private GameStateController gameStateController;
    private GameStateModel gameStateModel;
    private Stage stage;

    public SetNameView(NavigatorController navigatorController
    ){
        this.navigatorController = navigatorController;
        this.gameStateController = GameStateController.GameStateController();

        this.stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {
        Table rootTable = new Table();
        rootTable.setFillParent(true);
        stage.addActor(rootTable);
        Skin skin = new Skin(Gdx.files.internal("skin/neon-ui.json"));
        skin.getFont("font").getData().setScale(5f);

        final TextField usernameField = new TextField("Name", skin);
        usernameField.setMaxLength(12);
        TextButton submitButton = new TextButton("Submit", skin);


        rootTable.add(usernameField).width(500);
        rootTable.row().padTop(60);
        rootTable.add(submitButton).width(500).uniformX();

        submitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                gameStateController.setUsername(usernameField.getText());
                navigatorController.changeScreen(NavigationModel.NavigationScreen.GAMESETUP);
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

    }
}