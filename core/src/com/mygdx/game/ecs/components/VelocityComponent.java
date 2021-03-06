package com.mygdx.game.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class VelocityComponent implements Component {
    // velocity is mostly used for direction, while speed for actual speed
    public final Vector2 velocity = new Vector2();
    public int speed = 0;
}
