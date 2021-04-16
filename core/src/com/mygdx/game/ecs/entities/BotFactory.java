package com.mygdx.game.ecs.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.mygdx.game.ecs.GameEngine;
import com.mygdx.game.ecs.components.BotComponent;
import com.mygdx.game.ecs.components.CollisionComponent;
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
        CollisionComponent collision = GameEngine.getInstance().createComponent(CollisionComponent.class);

        Texture zombieSprite = new Texture("sprites/zombie.png");
        sprite.textureRegion = new TextureRegion(zombieSprite);
        sprite.offset = false;
        sprite.scaleX = 1f;
        sprite.scaleY = 1f;

        position.position.x = x;
        position.position.y = y;

        float left = 25;
        float bot = 10;
        float right = 200;
        float top = 190;
        sprite.polygon = new Polygon(new float[]{
                left,bot,
                left,top,
                right,top,
                right,bot});
        sprite.polygon.setPosition(x,y);
        sprite.polygon.setOrigin((left+right) * 0.5f, (bot+top) * 0.5f);

        velocity.speed = 150;

        botComponent.attackInterval = 800;
        botComponent.attackPower = 5;

        zombie.add(position);
        zombie.add(sprite);
        zombie.add(direction);
        zombie.add(velocity);
        zombie.add(botComponent);
        zombie.add(collision);

        return zombie;
    }
}
