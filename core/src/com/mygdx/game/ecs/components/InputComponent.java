package com.mygdx.game.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class InputComponent implements Component {
    public final Vector2 moveVector, shootVector;

    public InputComponent() {
        this.moveVector = new Vector2();
        this.shootVector = new Vector2();
    }
}