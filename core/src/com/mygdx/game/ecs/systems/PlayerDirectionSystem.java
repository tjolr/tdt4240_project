package com.mygdx.game.ecs.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.mygdx.game.ecs.components.DirectionComponent;
import com.mygdx.game.ecs.components.PlayerComponent;

public class PlayerDirectionSystem extends IteratingSystem {
    // System for setting the direction of the player from the touchpad input

    // Static variables updated from the touchpad changeListener
    private static float directionX;
    private static float directionY;
    // If isTouched is true, the direction/shooting joystick is in use and the direction
    // from the movement joystick should not be able to change the player direction
    private static boolean isTouched;

    private final ComponentMapper<DirectionComponent> directionMapper;

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
        // For when the direction joystick is used
        setDirection(x, y);
        isTouched = x != 0 || y != 0;
    }

    public static void setDirection(float x, float y) {
        // For when something else than the joystick wants to change direction of the player
        PlayerDirectionSystem.directionX = x;
        PlayerDirectionSystem.directionY = y;
    }

    public static boolean getIsTouched() {
        return isTouched;
    }
}
