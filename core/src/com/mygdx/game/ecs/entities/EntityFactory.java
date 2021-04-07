package com.mygdx.game.ecs.entities;

import com.badlogic.ashley.core.Entity;

public class EntityFactory {
    // EntityFactory is a Singleton class
    private static EntityFactory entityFactoryInstance = null;

    PlayerFactory playerFactory;
    BotFactory botFactory;

    private EntityFactory() {
        playerFactory = PlayerFactory.getInstance();
        botFactory = BotFactory.getInstance();
    }

    public static EntityFactory getInstance() {
        if (entityFactoryInstance == null)
            entityFactoryInstance = new EntityFactory();

        return entityFactoryInstance;
    }

    public Entity createPlayer(float x, float y) {
        return playerFactory.createPlayer(x, y);
    }

    public Entity createZombie(float x, float y) {
        return botFactory.createZombie(x, y);
    }
}
