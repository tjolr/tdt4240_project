package com.mygdx.game.ecs;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.ecs.components.BotSpawnComponent;
import com.mygdx.game.ecs.components.DirectionComponent;
import com.mygdx.game.ecs.components.PositionComponent;
import com.mygdx.game.ecs.components.SpriteComponent;
import com.mygdx.game.ecs.entities.EntityFactory;
import com.mygdx.game.ecs.systems.BotSpawnSystem;
import com.mygdx.game.ecs.systems.HealthRenderSystem;
import com.mygdx.game.ecs.systems.MovementSystem;
import com.mygdx.game.ecs.systems.PlayerControlSystem;
import com.mygdx.game.ecs.systems.PlayerDirectionSystem;
import com.mygdx.game.ecs.systems.RenderSystem;
import com.mygdx.game.ecs.systems.BotControlSystem;
import com.mygdx.game.ecs.systems.ShootingSystem;
import com.mygdx.game.ecs.systems.BulletSystem;

public class GameEngine extends PooledEngine {
    // GameEngine is a Singleton class
    private static GameEngine gameEngineInstance = null;

    private final EntityFactory entityFactory;

    private Entity player;

    private GameEngine() {
        entityFactory = EntityFactory.getInstance();
    }

    public static GameEngine getInstance() {
        if (gameEngineInstance == null)
            gameEngineInstance = new GameEngine();

        return gameEngineInstance;
    }

    public void initializeEngine() {
        createBackground();
        addBotSpawner();
        player = entityFactory.createPlayer(200, 200);

        gameEngineInstance.addEntity(player);

        gameEngineInstance.addSystem(new RenderSystem());
        gameEngineInstance.addSystem(new PlayerControlSystem());
        gameEngineInstance.addSystem(new PlayerDirectionSystem());
        gameEngineInstance.addSystem(new MovementSystem());
        gameEngineInstance.addSystem(new ShootingSystem());
        gameEngineInstance.addSystem(new BulletSystem());
        gameEngineInstance.addSystem(new BotControlSystem());
        gameEngineInstance.addSystem(new HealthRenderSystem());
        gameEngineInstance.addSystem(new BotSpawnSystem());
    }

    private void createBackground() {
        Entity background = gameEngineInstance.createEntity();

        PositionComponent position = gameEngineInstance.createComponent(PositionComponent.class);
        SpriteComponent sprite = gameEngineInstance.createComponent(SpriteComponent.class);
        DirectionComponent direction = gameEngineInstance.createComponent(DirectionComponent.class);

        position.position.x = 0;
        position.position.y = 0;

        Texture backgroundTexture = new Texture("sprites/background.png");
        sprite.textureRegion = new TextureRegion(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        background.add(position);
        background.add(sprite);
        background.add(direction);

        gameEngineInstance.addEntity(background);
    }

    private void addBotSpawner() {
        Entity spawner = gameEngineInstance.createEntity();

        BotSpawnComponent botSpawnComponent = gameEngineInstance.createComponent(BotSpawnComponent.class);

        spawner.add(botSpawnComponent);

        gameEngineInstance.addEntity(spawner);
    }

    public Entity getPlayer() {
        return player;
    }
}
