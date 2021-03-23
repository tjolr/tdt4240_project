package com.mygdx.game.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class VelocityComponent implements Component {
    public final Vector2 velocity;

    public VelocityComponent (float velocityX, float velocityY) {
        velocity = new Vector2(velocityX, velocityY)
    }

    public VelocityComponent () {
        velocity = new Vector2();
    }

    // funksjonen angleDeg() kan kjøres på Vector2, det samme kan clamp(float min, float max)
}
