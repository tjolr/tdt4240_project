package com.mygdx.game.ecs.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.mygdx.game.ecs.components.PositionComponent;
import com.mygdx.game.ecs.components.VelocityComponent;

public class MovementSystem extends IteratingSystem {
    private ComponentMapper<PositionComponent> positionMapper;
    private ComponentMapper<VelocityComponent> velocityMapper;

    public MovementSystem() {
        super(Family.all(PositionComponent.class, VelocityComponent.class).get());

        positionMapper = ComponentMapper.getFor(PositionComponent.class);
        velocityMapper = ComponentMapper.getFor(VelocityComponent.class);
    }

    public void processEntity(Entity entity, float deltaTime) {
        PositionComponent position = positionMapper.get(entity);
        VelocityComponent velocity = velocityMapper.get(entity);

        position.position.add(velocity.velocity.scl(deltaTime * velocity.speed));
    }
}

