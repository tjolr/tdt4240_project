package com.mygdx.game.ecs.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.ecs.GameEngine;
import com.mygdx.game.ecs.components.DirectionComponent;
import com.mygdx.game.ecs.components.HealthComponent;
import com.mygdx.game.ecs.components.PlayerComponent;
import com.mygdx.game.ecs.components.PositionComponent;
import com.mygdx.game.ecs.components.SpriteComponent;
import com.mygdx.game.ecs.components.VelocityComponent;

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
        VelocityComponent velocity = GameEngine.getInstance().createComponent(VelocityComponent.class);
        SpriteComponent sprite = GameEngine.getInstance().createComponent(SpriteComponent.class);
        PlayerComponent playerComponent = GameEngine.getInstance().createComponent(PlayerComponent.class);
        DirectionComponent direction = GameEngine.getInstance().createComponent(DirectionComponent.class);
        HealthComponent health = GameEngine.getInstance().createComponent(HealthComponent.class);

        Texture playerSprite = new Texture("sprites/player.png");
        sprite.textureRegion = new TextureRegion(playerSprite);

        position.position.x = x;
        position.position.y = y;

        velocity.speed = 500;

        health.health = 100f;
        health.maxHealth = 100f;

        player.add(position);
        player.add(velocity);
        player.add(sprite);
        player.add(playerComponent);
        player.add(direction);
        player.add(health);

        return player;
    }
}
