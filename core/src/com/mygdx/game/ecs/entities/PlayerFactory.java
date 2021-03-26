package com.mygdx.game.ecs.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.ecs.GameEngine;
import com.mygdx.game.ecs.components.PositionComponent;
import com.mygdx.game.ecs.components.SpriteComponent;

public class PlayerFactory {
    // PlayerFactory is a Singleton class
    private static PlayerFactory playerFactoryInstance = null;

    private PlayerFactory() {}

    public static PlayerFactory getInstance() {
        if (playerFactoryInstance == null)
            playerFactoryInstance = new PlayerFactory();

        return playerFactoryInstance;
    }

    Entity createPlayer(float x, float y) {
        Entity player = GameEngine.getInstance().createEntity();

        PositionComponent position = GameEngine.getInstance().createComponent(PositionComponent.class);
        SpriteComponent sprite = GameEngine.getInstance().createComponent(SpriteComponent.class);

        Texture playerSprite = new Texture("sprites/player.png");
        sprite.textureRegion = new TextureRegion(playerSprite);

        position.position.x = x;
        position.position.y = y;

        player.add(position);
        player.add(sprite);

        return player;
    }
}
