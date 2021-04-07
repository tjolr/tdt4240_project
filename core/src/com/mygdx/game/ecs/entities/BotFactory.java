package com.mygdx.game.ecs.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.ecs.GameEngine;
import com.mygdx.game.ecs.components.BotComponent;
import com.mygdx.game.ecs.components.DirectionComponent;
import com.mygdx.game.ecs.components.PositionComponent;
import com.mygdx.game.ecs.components.SpriteComponent;
import com.mygdx.game.ecs.components.VelocityComponent;

public class BotFactory {
    // BotFactory is a Singleton class
    private static BotFactory botFactoryInstance = null;

    private BotFactory() {}

    public static BotFactory getInstance() {
        if (botFactoryInstance == null)
            botFactoryInstance = new BotFactory();

        return botFactoryInstance;
    }

    Entity createZombie(float x, float y) {
        Entity zombie = GameEngine.getInstance().createEntity();

        PositionComponent position = GameEngine.getInstance().createComponent(PositionComponent.class);
        SpriteComponent sprite = GameEngine.getInstance().createComponent(SpriteComponent.class);
        DirectionComponent direction = GameEngine.getInstance().createComponent(DirectionComponent.class);
        BotComponent botComponent = GameEngine.getInstance().createComponent(BotComponent.class);
        VelocityComponent velocity = GameEngine.getInstance().createComponent(VelocityComponent.class);

        Texture zombieSprite = new Texture("sprites/zombie.png");
        sprite.textureRegion = new TextureRegion(zombieSprite);

        position.position.x = x;
        position.position.y = y;

        velocity.speed = 150;

        zombie.add(position);
        zombie.add(sprite);
        zombie.add(direction);
        zombie.add(velocity);
        zombie.add(botComponent);

        return zombie;
    }
}
