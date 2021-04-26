package com.mygdx.game.ecs.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.mygdx.game.ecs.GameEngine;
import com.mygdx.game.ecs.components.BulletComponent;
import com.mygdx.game.ecs.components.PositionComponent;
import com.mygdx.game.ecs.components.VelocityComponent;

public class BulletSystem extends IteratingSystem {
    // System for making sure bullets don't live forever
    // by decreasing lifetime and removing the ones that exceed it
    private final ComponentMapper<BulletComponent> bulletMapper;

    public BulletSystem() {
        super(Family.all(PositionComponent.class, VelocityComponent.class, BulletComponent.class).get());

        bulletMapper = ComponentMapper.getFor(BulletComponent.class);
    }

    public void processEntity(Entity entity, float deltaTime) {
        BulletComponent bullet = bulletMapper.get(entity);

        if (bullet.lifetime <= 0) {
            GameEngine.getInstance().removeEntity(entity);
        }
        else {
            bullet.lifetime -= deltaTime;
        }
    }
}
