package com.mygdx.game.screens.room;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.assets.AssetsController;
import com.mygdx.game.game_state.GlobalStateController;
import com.mygdx.game.game_state.GlobalStateModel;
import com.mygdx.game.screens.navigation.NavigationModel;
import com.mygdx.game.screens.navigation.NavigationController;

import java.util.ArrayList;
import java.util.Iterator;

public class RoomView implements Screen {
    private NavigationController navigationController;
    private RoomController roomController;
    private RoomModel roomModel;

    private GlobalStateModel globalStateModel;
    private GlobalStateController globalStateController;
    private AssetsController assetsController;

    private Table playersTable;

    private Iterator<String> iter;
    private Stage stage;

    public RoomView(
        NavigationController navigationController,
        RoomController roomController,
        RoomModel roomModel
    ) {
        this.navigationController = navigationController;
        this.roomController = roomController;
        this.roomModel = roomModel;

        this.globalStateController = GlobalStateController.getInstance();
        this.globalStateModel = GlobalStateModel.getInstance();
        this.assetsController = AssetsController.getInstance();


        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {
        Table rootTable = new Table();
        rootTable.setFillParent(true);
        stage.addActor(rootTable);

        playersTable = new Table();
        ScrollPane scrollPane = new ScrollPane(playersTable,  assetsController.getSkin());
        rootTable.add(scrollPane).size(1000,500);

        Label titleLabel = new Label("Players:",  assetsController.getSkin());
        titleLabel.setFontScale(6f);
        playersTable.add(titleLabel);
        playersTable.row().padTop(50);

        if (globalStateModel.getHost() != null && globalStateModel.getHost().equals(globalStateModel.getUsername())){
            TextButton startGameButton = new TextButton("START GAME",  assetsController.getSkin());
            rootTable.add(startGameButton).uniformX();

            startGameButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                navigationController.changeScreen(NavigationModel.NavigationScreen.GAME);
                roomController.setGameStateActive();
                }
            });
        } else {
            Label waitForStartLabel = new Label("Wait for host to start the game",  assetsController.getSkin());
            waitForStartLabel.setFontScale(4f);
            rootTable.row().padTop(90);
            rootTable.add(waitForStartLabel);
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        this.assetsController.renderMenuBackground();

        iter = this.globalStateModel.getPlayers().iterator();

        while(iter.hasNext()) {
            String player = iter.next();
            String playerText = player;
            if (player.equals(globalStateModel.getHost())) {
                playerText = playerText.concat(" (host)");
            }
            Label playerLabel = new Label(playerText,  assetsController.getSkin());
            playersTable.add(playerLabel);
            playersTable.row();
            iter.remove();
        }

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
        roomController.disposeRoomListeners();
    }
}
