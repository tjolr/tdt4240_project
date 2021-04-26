package com.mygdx.game.ecs.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.mygdx.game.ecs.components.PlayerComponent;
import com.mygdx.game.ecs.components.VelocityComponent;

public class PlayerControlSystem extends IteratingSystem {
    // System for setting the velocity of the player from the touchpad input

    // Static variables updated from the touchpad changeListener
    private static float moveJoystickX;
    private static float moveJoystickY;

    private final ComponentMapper<VelocityComponent> velocityMapper;

    public PlayerControlSystem() {
        super(Family.all(PlayerComponent.class, VelocityComponent.class).get());

        velocityMapper = ComponentMapper.getFor(VelocityComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        velocityMapper.get(entity).velocity.set(moveJoystickX, moveJoystickY);
    }

    public static void setMoveJoystick(float x, float y) {
        PlayerControlSystem.moveJoystickX = x;
        PlayerControlSystem.moveJoystickY = y;
        if (!PlayerDirectionSystem.getIsTouched()) {
            // If the direction joystick is not in use, the movement joystick can change the
            // direction of the player
            PlayerDirectionSystem.setDirection(x, y);
        }
    }
}
