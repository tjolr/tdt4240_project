package com.mygdx.game.screens.result;

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
import com.mygdx.game.game_state.GlobalStateModel;
import com.mygdx.game.items.PlayerUpdateModel;
import com.mygdx.game.screens.navigation.NavigationController;
import com.mygdx.game.screens.navigation.NavigationModel;


import java.util.Iterator;
import java.util.Map;

public class ResultView implements Screen {
    private NavigationController navigationController;
    private ResultController resultController;
    private Stage stage;
    private AssetsController assetsController;

    private GlobalStateModel globalStateModel;

    private Table rootTable;
    private Table playerResultTable;
    private Map<String, PlayerUpdateModel> sortedPlayerUpdateModels;
    private Iterator<Map.Entry<String, PlayerUpdateModel>> iter;

    public ResultView(NavigationController navigationController, ResultController resultController) {
        this.navigationController = navigationController;
        this.resultController = resultController;

        this.globalStateModel = GlobalStateModel.getInstance();

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        this.assetsController = AssetsController.getInstance();

    }

    @Override
    public void show() {
        rootTable = new Table();
        rootTable.setFillParent(true);

        stage.addActor(rootTable);

        Label titleLabel = new Label("Result: ",  assetsController.getSkin());
        titleLabel.setFontScale(6f);
        rootTable.add(titleLabel);
        rootTable.row();

        playerResultTable = new Table();
        ScrollPane scrollPane = new ScrollPane(playerResultTable,  assetsController.getSkin());
        rootTable.add(scrollPane).size(Gdx.graphics.getWidth() * 0.9f,500);

        rootTable.row().padTop(50);
        TextButton mainMenuButton = new TextButton("Main Menu",  assetsController.getSkin());
        rootTable.add(mainMenuButton).uniformX();

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


        sortedPlayerUpdateModels = this.resultController.sortPlayerUpdateModelsByScore(globalStateModel.getPlayerUpdateModels());
        iter = this.sortedPlayerUpdateModels.entrySet().iterator();

        playerResultTable.clear();

        while (iter.hasNext()) {
            Map.Entry<String, PlayerUpdateModel> playerUpdateModelEntry = iter.next();
            PlayerUpdateModel playerUpdateModel = playerUpdateModelEntry.getValue();
            String aliveOrDeadText = (playerUpdateModel.health <= 0) ? "(dead)" : "(alive)";
            String playerStatus = playerUpdateModel.player + " " + aliveOrDeadText;

            Label playerStatusLabel = new Label(playerStatus,  assetsController.getSkin());
            playerStatusLabel.setWidth(Gdx.graphics.getWidth() * 0.7f);

            Label scoreLabel = new Label(" " + playerUpdateModel.score + "p",  assetsController.getSkin());
            scoreLabel.setWidth(Gdx.graphics.getWidth() * 0.2f);

            playerResultTable.add(playerStatusLabel);
            playerResultTable.add(scoreLabel);
            playerResultTable.row();

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
        resultController.disposeResultController();
    }
}
