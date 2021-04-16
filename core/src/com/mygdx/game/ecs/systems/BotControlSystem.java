package com.mygdx.game.ecs.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.mygdx.game.ecs.GameEngine;
import com.mygdx.game.ecs.components.BotComponent;
import com.mygdx.game.ecs.components.DirectionComponent;
import com.mygdx.game.ecs.components.PositionComponent;
import com.mygdx.game.ecs.components.VelocityComponent;

public class BotControlSystem extends IteratingSystem {
    private final ComponentMapper<PositionComponent> positionMapper;
    private final ComponentMapper<DirectionComponent> directionMapper;
    private final ComponentMapper<VelocityComponent> velocityMapper;
    private final ComponentMapper<BotComponent> botComponentMapper;

    private final Entity player;

    public BotControlSystem() {
        super(Family.all(
                BotComponent.class,
                PositionComponent.class,
                DirectionComponent.class,
                VelocityComponent.class
            ).get());

        positionMapper = ComponentMapper.getFor(PositionComponent.class);
        directionMapper = ComponentMapper.getFor(DirectionComponent.class);
        velocityMapper = ComponentMapper.getFor(VelocityComponent.class);
        botComponentMapper = ComponentMapper.getFor(BotComponent.class);

        player = GameEngine.getInstance().getPlayer();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent botPosition = positionMapper.get(entity);
        DirectionComponent botDirection = directionMapper.get(entity);
        VelocityComponent botVelocity = velocityMapper.get(entity);
        PositionComponent playerPosition = positionMapper.get(player);
        BotComponent botComponent = botComponentMapper.get(entity);

        botDirection.direction.set(playerPosition.position).sub(botPosition.position);

        // don't want to move if attacking
        if (botComponent.attacking) {
            botVelocity.velocity.set(0, 0);
        } else {
            botVelocity.velocity.set(botDirection.direction).scl(1f / botDirection.direction.len());
        }
    }
}
