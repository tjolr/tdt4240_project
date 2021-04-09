package com.mygdx.game.ecs.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.ecs.GameEngine;
import com.mygdx.game.ecs.components.BulletComponent;
import com.mygdx.game.ecs.components.DirectionComponent;
import com.mygdx.game.ecs.components.PositionComponent;
import com.mygdx.game.ecs.components.SpriteComponent;
import com.mygdx.game.ecs.components.VelocityComponent;

public class BulletFactory {
    // BulletFactory is a Singleton class
    private static BulletFactory bulletFactoryInstance = null;

    private BulletFactory() {}

    public static BulletFactory getInstance() {
        if (bulletFactoryInstance == null)
            bulletFactoryInstance = new BulletFactory();

        return bulletFactoryInstance;
    }

    Entity createBullet(float x, float y, float dirX, float dirY) {
        Entity bullet = GameEngine.getInstance().createEntity();

        PositionComponent position = GameEngine.getInstance().createComponent(PositionComponent.class);
        SpriteComponent sprite = GameEngine.getInstance().createComponent(SpriteComponent.class);
        DirectionComponent direction = GameEngine.getInstance().createComponent(DirectionComponent.class);
        VelocityComponent velocity = GameEngine.getInstance().createComponent(VelocityComponent.class);
        BulletComponent bulletComp = GameEngine.getInstance().createComponent(BulletComponent.class);

        Texture bulletSprite = new Texture("sprites/bullet.png");
        sprite.textureRegion = new TextureRegion(bulletSprite);
        sprite.scaleX = 0.1f;
        sprite.scaleY = 0.1f;

        position.position.x = x - 253;
        position.position.y = y - 216;

        direction.direction.set(dirX, dirY);

        velocity.speed = 100;
        velocity.velocity.set(direction.direction);

        bulletComp.lifetime = 50;

        bullet.add(position);
        bullet.add(sprite);
        bullet.add(direction);
        bullet.add(velocity);
        bullet.add(bulletComp);

        return bullet;
    }
}
