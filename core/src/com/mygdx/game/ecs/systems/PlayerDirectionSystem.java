package com.mygdx.game.ecs.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.ecs.components.DirectionComponent;
import com.mygdx.game.ecs.components.PlayerComponent;

public class PlayerDirectionSystem extends IteratingSystem {
    // Static variables updated from the touchpad changeListener
    private static float directionX;
    private static float directionY;
    private static boolean isTouched;

    private ComponentMapper<DirectionComponent> directionMapper;

    public PlayerDirectionSystem() {
        super(Family.all(PlayerComponent.class, DirectionComponent.class).get());

        directionMapper = ComponentMapper.getFor(DirectionComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        if (directionX == 0 && directionY == 0)
            return;
        directionMapper.get(entity).direction.set(directionX, directionY);
        ShootingSystem.setFire(isTouched);
    }

    public static void setDirectionJoystick(float x, float y) {
        setDirection(x, y);
        if (x != 0 || y != 0) {
            isTouched = true;
        }
    }

    public static void setDirectionWithoutShooting(float x, float y) {
        setDirection(x, y);
        isTouched = false;
    }

    public static void setDirection(float x, float y) {
        PlayerDirectionSystem.directionX = x;
        PlayerDirectionSystem.directionY = y;
    }
}
