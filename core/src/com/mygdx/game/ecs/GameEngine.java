package com.mygdx.game.ecs;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.mygdx.game.ecs.entities.EntityFactory;
import com.mygdx.game.ecs.systems.HealthRenderSystem;
import com.mygdx.game.ecs.systems.MovementSystem;
import com.mygdx.game.ecs.systems.PlayerControlSystem;
import com.mygdx.game.ecs.systems.PlayerDirectionSystem;
import com.mygdx.game.ecs.systems.RenderSystem;
import com.mygdx.game.ecs.systems.BotControlSystem;

public class GameEngine extends PooledEngine {
    // GameEngine is a Singleton class
    private static GameEngine gameEngineInstance = null;

    private final EntityFactory entityFactory;

    private GameEngine() {
        entityFactory = EntityFactory.getInstance();
    }

    public static GameEngine getInstance() {
        if (gameEngineInstance == null)
            gameEngineInstance = new GameEngine();

        return gameEngineInstance;
    }

    public void initializeEngine() {
        Entity player = entityFactory.createPlayer(200, 200);
        Entity zombie = entityFactory.createZombie(600, 600);

        gameEngineInstance.addEntity(player);
        gameEngineInstance.addEntity(zombie);

        gameEngineInstance.addSystem(new RenderSystem());
        gameEngineInstance.addSystem(new PlayerControlSystem());
        gameEngineInstance.addSystem(new PlayerDirectionSystem());
        gameEngineInstance.addSystem(new MovementSystem());
        gameEngineInstance.addSystem(new BotControlSystem());
        gameEngineInstance.addSystem(new HealthRenderSystem());
    }
}
