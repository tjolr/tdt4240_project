package com.mygdx.game.ecs.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.mygdx.game.ecs.components.BotComponent;
import com.mygdx.game.firebase.FirebaseController;
import com.mygdx.game.game_state.GlobalStateModel;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class BotAttackSystem extends IteratingSystem {
    private final ComponentMapper<BotComponent> botComponentMapper;
    private GlobalStateModel globalStateModel;
    private FirebaseController firebaseController;

    public BotAttackSystem() {
        super(Family.all(BotComponent.class).get());

        botComponentMapper = ComponentMapper.getFor(BotComponent.class);

        this.globalStateModel = GlobalStateModel.getInstance();
        this.firebaseController = FirebaseController.getInstance();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        BotComponent botComponent = botComponentMapper.get(entity);
        long currentTime = (new Date()).getTime();

        if (botComponent.attacking && ((currentTime - botComponent.lastAttack) > botComponent.attackInterval)) {
            // Creating a random attack value to create a more dynamic game experience
            int randomAttackValue = ThreadLocalRandom.current().nextInt(2,7);
            firebaseController.decrementValueInDb(
                "game."+globalStateModel.getGameId()+".playerUpdateModels."
                +globalStateModel.getUserPlayerUpdateModelId()+".health",
                randomAttackValue
            );

            botComponent.lastAttack = currentTime;
            botComponent.attacking = false;
        }
    }
}
