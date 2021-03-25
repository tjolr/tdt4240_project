package com.mygdx.game.screens.game;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.ecs.components.PositionComponent;
import com.mygdx.game.ecs.components.SpriteComponent;
import com.mygdx.game.ecs.systems.RenderSystem;
import com.mygdx.game.screens.navigation.NavigatorController;

public class GameView implements Screen {

    private Stage stage;
    private NavigatorController navigatorController;

    private PooledEngine engine;

    public GameView(NavigatorController navigatorController) {
        this.navigatorController = navigatorController;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        initializeEngine();
    }

    private void initializeEngine() {
        this.engine = new PooledEngine();

        // Todo: extract player creation to its own factory
        Entity player = engine.createEntity();

        PositionComponent position = engine.createComponent(PositionComponent.class);
        SpriteComponent sprite = engine.createComponent(SpriteComponent.class);

        Texture playerSprite = new Texture("sprites/player.png");
        sprite.textureRegion = new TextureRegion(playerSprite);

        position.position.x = 200;
        position.position.y = 200;

        player.add(position);
        player.add(sprite);

        engine.addEntity(player);

        engine.addSystem(new RenderSystem());
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        engine.update(delta);
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
