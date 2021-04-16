package com.mygdx.game.ecs.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.mygdx.game.ecs.components.BotComponent;

import java.util.Date;

public class BotAttackSystem extends IteratingSystem {
    private final ComponentMapper<BotComponent> botComponentMapper;

    public BotAttackSystem() {
        super(Family.all(BotComponent.class).get());

        botComponentMapper = ComponentMapper.getFor(BotComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        BotComponent botComponent = botComponentMapper.get(entity);
        long currentTime = (new Date()).getTime();

        if (botComponent.attacking && ((currentTime - botComponent.lastAttack) > botComponent.attackInterval)) {
            // TODO: decrease health in database here
            botComponent.lastAttack = currentTime;
            botComponent.attacking = false;
        }
    }
}
