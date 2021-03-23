package com.mygdx.game.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class PositionComponent implements Component {
    public final Vector2 vector;

    public PositionComponent (float x, float y) {
        this.vector = new Vector2(x, y);
    }
}
