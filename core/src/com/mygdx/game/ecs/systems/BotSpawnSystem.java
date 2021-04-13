package com.mygdx.game.ecs.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.ecs.components.BotSpawnComponent;
import com.mygdx.game.ecs.entities.EntityFactory;

import java.util.Date;
import java.util.Random;

public class BotSpawnSystem extends IteratingSystem {
    private final ComponentMapper<BotSpawnComponent> botSpawnMapper;

    private final float width = Gdx.graphics.getWidth();
    private final float height = Gdx.graphics.getHeight();

    public BotSpawnSystem() {
        super(Family.all(BotSpawnComponent.class).get());

        botSpawnMapper = ComponentMapper.getFor(BotSpawnComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        BotSpawnComponent botSpawnComponent = botSpawnMapper.get(entity);

        long currentTime = (new Date()).getTime();

        if (currentTime - botSpawnComponent.lastSpawn > botSpawnComponent.interval) {
            float[] spawnPoint = getRandomSpawnPoint();
            getEngine().addEntity(EntityFactory.getInstance().createZombie(spawnPoint[0], spawnPoint[1]));
            botSpawnComponent.lastSpawn = currentTime;
            if (botSpawnComponent.interval > 100)
                botSpawnComponent.interval -= 10;
        }
    }

    private float[] getRandomSpawnPoint() {
        float[] spawnPoint = new float[2];
        Random random = new Random();
        float screenOffset = 250f;

        int side = random.nextInt(4);
        float placement = random.nextFloat();

        if (side == 0) {
            // left
            spawnPoint[0] = -screenOffset;
            spawnPoint[1] = (height + 2 * screenOffset) * placement - screenOffset;
        } else if (side == 1) {
            // top
            spawnPoint[1] = height;
            spawnPoint[0] = (width + 2 * screenOffset) * placement - screenOffset;
        } else if (side == 2) {
            //right
            spawnPoint[0] = width;
            spawnPoint[1] = (height + 2 * screenOffset) * placement - screenOffset;
        } else {
            // bottom
            spawnPoint[1] = -screenOffset;
            spawnPoint[0] = (width + 2 * screenOffset) * placement - screenOffset;
        }

        return spawnPoint;
    }
}
