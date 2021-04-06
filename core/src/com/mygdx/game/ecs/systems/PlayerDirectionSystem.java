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
    private static float directionJoystickX;
    private static float directionJoystickY;

    private ComponentMapper<DirectionComponent> directionMapper;

    public PlayerDirectionSystem() {
        super(Family.all(PlayerComponent.class, DirectionComponent.class).get());

        directionMapper = ComponentMapper.getFor(DirectionComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        if (directionJoystickX == 0 && directionJoystickY == 0)
            return;
        directionMapper.get(entity).direction.set(directionJoystickX, directionJoystickY);
    }

    public static void setDirectionJoystick(float x, float y) {
        PlayerDirectionSystem.directionJoystickX = x;
        PlayerDirectionSystem.directionJoystickY = y;
    }
}
