package com.mygdx.game.screens.game_setup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
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
import com.mygdx.game.items.GameModel;
import com.mygdx.game.screens.navigation.NavigationModel;
import com.mygdx.game.screens.navigation.NavigatorController;

import java.util.Iterator;

public class GameSetupView implements Screen {
    private NavigatorController navigatorController;
    private GameSetupController gameSetupController;
    private GameSetupModel gameSetupModel;

    private static final String TEXT_START = "START NOW";
    private static final String TEXT_HOST_GAME = "HOST GAME";
    private static final String TEXT_JOIN = "Join";
    private Stage stage;

    private Table rootTable;
    private Table availableGamesTable;
    private float textSize;
    private Skin skin;
    private Iterator<GameModel> iter;


    public GameSetupView(
            NavigatorController navigatorController,
            GameSetupController gameSetupController,
            GameSetupModel gameSetupModel
    ) {
        this.navigatorController = navigatorController;
        this.gameSetupController = gameSetupController;
        this.gameSetupModel = gameSetupModel;

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {
        System.out.println("GameSetupView Show!");
        rootTable = new Table();
        rootTable.setFillParent(true);

        stage.addActor(rootTable);

        skin = new Skin(Gdx.files.internal("skin/neon-ui.json"));

        TextButton startButton = new TextButton(TEXT_START, skin);
        TextButton hostGameButton = new TextButton(TEXT_HOST_GAME, skin);
        textSize = 5f;
        startButton.getLabel().setFontScale(textSize);
        hostGameButton.getLabel().setFontScale(textSize);


        rootTable.row().pad(50, 0 , 20, 0);
        rootTable.add(startButton).uniformX();
        rootTable.row().pad(20, 0 , 20, 0);
        rootTable.add(hostGameButton).uniformX();
        rootTable.row().pad(20, 0 , 20, 0);

        startButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
            navigatorController.changeScreen(NavigationModel.NavigationScreen.GAME);
            }
        });
        hostGameButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
            gameSetupController.hostCreateGame();
            navigatorController.changeScreen(NavigationModel.NavigationScreen.ROOM);
            }
        });


        Label otherGamesTitle = new Label("Join another game: ", skin);
        otherGamesTitle.setFontScale(textSize);
        rootTable.add(otherGamesTitle);
        rootTable.row();

        availableGamesTable = new Table();
        ScrollPane scrollPane = new ScrollPane(availableGamesTable, skin);
        scrollPane.setFadeScrollBars(false);
        scrollPane.setColor(Color.DARK_GRAY);
        rootTable.add(scrollPane).size(1000,500);
        rootTable.row();


    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        availableGamesTable.clear();
        iter = this.gameSetupModel.getAvailableGames().iterator();
        while(iter.hasNext()) {
            GameModel availableGame = iter.next();

            Label gameLabel = new Label(availableGame.gameId +" ", skin);
            TextButton joinButton = new TextButton(TEXT_JOIN,skin);

            gameLabel.setFontScale(textSize);
            joinButton.getLabel().setFontScale(textSize);

            availableGamesTable.add(gameLabel);
            availableGamesTable.add(joinButton);

            availableGamesTable.row();

            joinButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    navigatorController.changeScreen(NavigationModel.NavigationScreen.ROOM);
                }
            });
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
