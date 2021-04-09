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
    private ComponentMapper<PositionComponent> positionMapper;
    private ComponentMapper<VelocityComponent> velocityMapper;
    private ComponentMapper<BulletComponent> bulletMapper;

    public BulletSystem() {
        super(Family.all(PositionComponent.class, VelocityComponent.class, BulletComponent.class).get());

        bulletMapper = ComponentMapper.getFor(BulletComponent.class);
    }

    public void processEntity(Entity entity, float deltaTime) {
        BulletComponent bullet = bulletMapper.get(entity);

        if (bullet.lifetime <= 0) {
            GameEngine gameEngineInstance = GameEngine.getInstance();
            gameEngineInstance.removeEntity(entity);
        }
        else {
            bullet.lifetime -= deltaTime;
        }
    }
}

