package com.mygdx.game.screens.game_setup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.assets.AssetsController;
import com.mygdx.game.items.SimpleGameModel;
import com.mygdx.game.screens.navigation.NavigationModel;
import com.mygdx.game.screens.navigation.NavigationController;

import java.util.ArrayList;
import java.util.Iterator;

public class GameSetupView implements Screen {
    private NavigationController navigationController;
    private GameSetupController gameSetupController;
    private GameSetupModel gameSetupModel;

    private static final String TEXT_START = "START NOW";
    private static final String TEXT_HOST_GAME = "HOST GAME";
    private static final String TEXT_JOIN = "Join";
    private Stage stage;
    private AssetsController assetsController;

    private Table rootTable;
    private Table availableGamesTable;
    private Iterator<SimpleGameModel> iter;
    private ArrayList<SimpleGameModel> availableGames;


    public GameSetupView(
            NavigationController navigationController,
            GameSetupController gameSetupController,
            GameSetupModel gameSetupModel
    ) {
        this.navigationController = navigationController;
        this.gameSetupController = gameSetupController;
        this.gameSetupModel = gameSetupModel;

        this.availableGames = new ArrayList<>();
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        this.assetsController = AssetsController.getInstance();
    }

    @Override
    public void show() {
        rootTable = new Table();
        rootTable.setFillParent(true);

        stage.addActor(rootTable);

        TextButton hostGameButton = new TextButton(TEXT_HOST_GAME,  assetsController.getSkin());

        rootTable.row().pad(20, 0 , 20, 0);
        rootTable.add(hostGameButton).uniformX();
        rootTable.row().pad(20, 0 , 20, 0);

        hostGameButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
            gameSetupController.hostCreateGame();
            navigationController.changeScreen(NavigationModel.NavigationScreen.ROOM);
            }
        });


        Label otherGamesTitle = new Label("Join another game: ",  assetsController.getSkin());
        rootTable.add(otherGamesTitle);
        rootTable.row();

        availableGamesTable = new Table();
        ScrollPane scrollPane = new ScrollPane(availableGamesTable,  assetsController.getSkin());
        scrollPane.setFadeScrollBars(false);
        scrollPane.setColor(Color.DARK_GRAY);
        rootTable.add(scrollPane).size(1000,500);
        rootTable.row();
    }

    // saving a local copy of previously rendered available games
    // to prevent double rendering
    private ArrayList<SimpleGameModel> getNewAvailableGames() {
        ArrayList<SimpleGameModel> gamesToAdd = new ArrayList<>();
        for(final SimpleGameModel gameModel: this.gameSetupModel.getAvailableGames()) {
            if (!availableGames.stream().anyMatch(o -> o.getGameId().equals(gameModel.gameId))) {
                availableGames.add(gameModel);
                gamesToAdd.add(gameModel);
            }
        }

        return gamesToAdd;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        this.assetsController.renderMenuBackground();

        iter = this.getNewAvailableGames().iterator();

        while (iter.hasNext()) {
            final SimpleGameModel availableGame = iter.next();

            Label gameLabel = new Label(availableGame.gameId +" ",  assetsController.getSkin());
            TextButton joinButton = new TextButton(TEXT_JOIN, assetsController.getSkin());

            availableGamesTable.add(gameLabel);
            availableGamesTable.add(joinButton);

            availableGamesTable.row();

            joinButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                gameSetupController.playerJoinGame(availableGame);
                navigationController.changeScreen(NavigationModel.NavigationScreen.ROOM);
                }
            });
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
    }
}
