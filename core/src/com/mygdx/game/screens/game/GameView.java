package com.mygdx.game.screens.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.assets.AssetsController;
import com.mygdx.game.ecs.GameEngine;
import com.mygdx.game.ecs.systems.PlayerControlSystem;
import com.mygdx.game.ecs.systems.PlayerDirectionSystem;
import com.mygdx.game.ecs.systems.ShootingSystem;

public class GameView implements Screen {
    private Stage stage;
    private GameController gameController;
    private AssetsController assetsController;

    public GameView(GameController gameController) {
        this.gameController = gameController;
        this.assetsController = AssetsController.getInstance();
        stage = new Stage(new ScreenViewport());

        initializeTouchpad(stage);
        Gdx.input.setInputProcessor(stage);
        GameEngine.getInstance().initializeEngine();
    }

    private void initializeTouchpad(Stage stage) {
        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();
        float sizeFactor = 0.14f;
        float heightFactor = 0.2f;
        float widthFactor = 0.18f;
        int deadzoneRadius = 20;

        Touchpad move = new Touchpad(deadzoneRadius,  assetsController.getSkin());
        move.setSize(width * sizeFactor, width * sizeFactor);
        move.setPosition(width * widthFactor, height * heightFactor);
        move.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                float deltaX = ((Touchpad) actor).getKnobPercentX();
                float deltaY = ((Touchpad) actor).getKnobPercentY();
                PlayerControlSystem.setMoveJoystick(deltaX, deltaY);
            }
        });

        ShootingSystem.setFire(false);
        Touchpad shoot = new Touchpad(deadzoneRadius,  assetsController.getSkin());
        shoot.setSize(width * sizeFactor, width * sizeFactor);
        shoot.setPosition(width * (1f - widthFactor) - shoot.getWidth(), height * heightFactor);
        shoot.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                float deltaX = ((Touchpad) actor).getKnobPercentX();
                float deltaY = ((Touchpad) actor).getKnobPercentY();
                PlayerDirectionSystem.setDirectionJoystick(deltaX, deltaY);
            }
        });

        stage.addActor(move);
        stage.addActor(shoot);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameController.checkIfPlayerHasDied();

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        GameEngine.getInstance().update(delta);
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
