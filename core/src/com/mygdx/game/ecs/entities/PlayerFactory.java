package com.mygdx.game.ecs.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.mygdx.game.ecs.GameEngine;
import com.mygdx.game.ecs.components.DirectionComponent;
import com.mygdx.game.ecs.components.ExternalPlayerComponent;
import com.mygdx.game.ecs.components.HealthComponent;
import com.mygdx.game.ecs.components.PlayerComponent;
import com.mygdx.game.ecs.components.PositionComponent;
import com.mygdx.game.ecs.components.ShootingComponent;
import com.mygdx.game.ecs.components.SpriteComponent;
import com.mygdx.game.ecs.components.VelocityComponent;

public class PlayerFactory {
    // PlayerFactory is a Singleton class
    private static PlayerFactory playerFactoryInstance = null;

    private PlayerFactory() {

    }

    public static PlayerFactory getInstance() {
        if (playerFactoryInstance == null)
            playerFactoryInstance = new PlayerFactory();

        return playerFactoryInstance;
    }

    Entity createPlayer(String playerName, float x, float y) {
        Entity player = GameEngine.getInstance().createEntity();

        PositionComponent position = GameEngine.getInstance().createComponent(PositionComponent.class);
        VelocityComponent velocity = GameEngine.getInstance().createComponent(VelocityComponent.class);
        SpriteComponent sprite = GameEngine.getInstance().createComponent(SpriteComponent.class);
        PlayerComponent playerComponent = GameEngine.getInstance().createComponent(PlayerComponent.class);
        DirectionComponent direction = GameEngine.getInstance().createComponent(DirectionComponent.class);
        HealthComponent health = GameEngine.getInstance().createComponent(HealthComponent.class);
        ShootingComponent shooting = GameEngine.getInstance().createComponent(ShootingComponent.class);

        // initialize all important values, in case some components are being re-used from the component pool
        Texture playerSprite = new Texture("sprites/player.png");
        sprite.textureRegion = new TextureRegion(playerSprite);
        sprite.offset = false;
        sprite.scaleX = 1f;
        sprite.scaleY = 1f;

        playerComponent.playerName = playerName;

        position.position.x = x;
        position.position.y = y;

        float left = 25;
        float bot = 25;
        float right = 225;
        float top = 175;
        sprite.polygon = new Polygon(new float[]{
                left,bot,
                left,top,
                right,top,
                right,bot});
        sprite.polygon.setPosition(x,y);
        sprite.polygon.setOrigin((left+right) * 0.5f, (bot+top) * 0.5f);
        sprite.polygon.setRotation(direction.direction.angleDeg());

        velocity.speed = 500;

        health.health = 100f;
        health.maxHealth = 100f;

        player.add(position);
        player.add(velocity);
        player.add(sprite);
        player.add(playerComponent);
        player.add(direction);
        player.add(health);
        player.add(shooting);

        return player;
    }

    Entity createExternalPlayer(String playerName, float health, int index) {
        // external players are just for showing health and score
        GameEngine gameEngine = GameEngine.getInstance();
        Entity playerEntity = gameEngine.createEntity();

        ExternalPlayerComponent extPlayerComponent = gameEngine.createComponent(ExternalPlayerComponent.class);
        HealthComponent healthComponent = gameEngine.createComponent(HealthComponent.class);

        extPlayerComponent.playerName = playerName;
        extPlayerComponent.index = index;

        healthComponent.health = health;
        healthComponent.maxHealth = 100f;

        playerEntity.add(extPlayerComponent);
        playerEntity.add(healthComponent);

        return playerEntity;
    };
}
