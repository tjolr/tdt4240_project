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
import com.mygdx.game.game_state.GameStateController;
import com.mygdx.game.game_state.GameStateModel;
import com.mygdx.game.items.SimpleGameModel;
import com.mygdx.game.screens.navigation.NavigationModel;
import com.mygdx.game.screens.navigation.NavigatorController;

import java.util.ArrayList;
import java.util.Iterator;

public class RoomView implements Screen {
    private NavigatorController navigatorController;
    private RoomController roomController;
    private RoomModel roomModel;

    private GameStateModel gameStateModel;
    private GameStateController gameStateController;


    private Skin skin;
    private Table playersTable;

    private Iterator<String> iter;
    private Stage stage;

    public RoomView(NavigatorController navigatorController,
                    RoomController roomController,
                    RoomModel roomModel
    ) {
        this.navigatorController = navigatorController;
        this.roomController = roomController;
        this.roomModel = roomModel;

        this.gameStateController = GameStateController.GameStateController();
        this.gameStateModel = GameStateModel.GameStateModel();

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {
        Table rootTable = new Table();
        rootTable.setFillParent(true);
        stage.addActor(rootTable);

        skin = new Skin(Gdx.files.internal("skin/neon-ui.json"));
        skin.getFont("font").getData().setScale(5f);

        playersTable = new Table();
        ScrollPane scrollPane = new ScrollPane(playersTable, skin);
        rootTable.add(scrollPane).size(1000,500);

        //Tmp player list, will be replaced when network is implemented.
        ArrayList<String> mockPlayers = new ArrayList<>();
        mockPlayers.add("Kari");
        mockPlayers.add("Per");

        Label titleLabel = new Label("Players:", skin);
        titleLabel.setFontScale(6f);
        playersTable.add(titleLabel);
        playersTable.row().padTop(50);

        if (gameStateModel.getHost() != null && gameStateModel.getHost().equals(gameStateModel.getUsername())){
            TextButton startGameButton = new TextButton("START GAME", skin);
            rootTable.add(startGameButton).uniformX();

            startGameButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    navigatorController.changeScreen(NavigationModel.NavigationScreen.GAME);
                }
            });
        } else {
            Label waitForStartLabel = new Label("Wait for host to start the game", skin);
            waitForStartLabel.setFontScale(4f);
            rootTable.row().padTop(90);
            rootTable.add(waitForStartLabel);
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        iter = this.gameStateModel.getPlayers().iterator();

        while(iter.hasNext()) {
            String player = iter.next();
            String playerText = player;
            if (player.equals(gameStateModel.getHost())) {
                playerText = playerText.concat(" (host)");
            }
            Label playerLabel = new Label(playerText, skin);
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

    }
}
