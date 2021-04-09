package com.mygdx.game.ecs.systems;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.mygdx.game.ecs.GameEngine;
import com.mygdx.game.ecs.components.PlayerComponent;
import com.mygdx.game.ecs.components.PositionComponent;
import com.mygdx.game.ecs.components.ShootingComponent;
import com.mygdx.game.ecs.components.DirectionComponent;
import com.mygdx.game.ecs.components.SpriteComponent;
import com.mygdx.game.ecs.entities.EntityFactory;

public class ShootingSystem extends IteratingSystem {
    private static boolean fire = false;

    private ComponentMapper<PositionComponent> positionMapper;
    private static ComponentMapper<ShootingComponent> shootingMapper;
    private ComponentMapper<DirectionComponent> directionMapper;
    private ComponentMapper<SpriteComponent> spriteMapper;

    public ShootingSystem() {
        super(Family.all(PlayerComponent.class, PositionComponent.class, ShootingComponent.class, DirectionComponent.class, SpriteComponent.class).get());

        positionMapper = ComponentMapper.getFor(PositionComponent.class);
        shootingMapper = ComponentMapper.getFor(ShootingComponent.class);
        directionMapper = ComponentMapper.getFor(DirectionComponent.class);
        spriteMapper = ComponentMapper.getFor(SpriteComponent.class);
    }

    public void processEntity(Entity entity, float deltaTime) {
        if (ShootingSystem.fire) {
            ShootingComponent shooting = shootingMapper.get(entity);
            if (shooting.cooldown <= 0) {
                PositionComponent position = positionMapper.get(entity);
                DirectionComponent direction = directionMapper.get(entity);

                EntityFactory entityFactory = EntityFactory.getInstance();
                Entity bullet = entityFactory.createBullet(position.position.x, position.position.y, direction.direction.x, direction.direction.y);

                GameEngine gameEngineInstance = GameEngine.getInstance();
                gameEngineInstance.addEntity(bullet);

                shooting.cooldown = shooting.interval;
            }
            else {
                shooting.cooldown -= deltaTime;
            }
            ShootingSystem.fire = false;
        }
    }

    public static void setFire(boolean fire) {
        ShootingSystem.fire = fire;
    }
}

