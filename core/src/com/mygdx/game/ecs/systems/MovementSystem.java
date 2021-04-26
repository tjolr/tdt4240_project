package com.mygdx.game.ecs.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.mygdx.game.ecs.components.PositionComponent;
import com.mygdx.game.ecs.components.VelocityComponent;

public class MovementSystem extends IteratingSystem {
    // System for making entities move by using their velocity component
    private final ComponentMapper<PositionComponent> positionMapper;
    private final ComponentMapper<VelocityComponent> velocityMapper;

    public MovementSystem() {
        super(Family.all(PositionComponent.class, VelocityComponent.class).get());

        positionMapper = ComponentMapper.getFor(PositionComponent.class);
        velocityMapper = ComponentMapper.getFor(VelocityComponent.class);
    }

    public void processEntity(Entity entity, float deltaTime) {
        PositionComponent position = positionMapper.get(entity);
        VelocityComponent velocity = velocityMapper.get(entity);

        // Using deltatime to have consistent speed even though deltatime can vary with framerate
        position.position.add(velocity.velocity.scl(deltaTime * velocity.speed));
    }
}
