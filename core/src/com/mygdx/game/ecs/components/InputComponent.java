package com.mygdx.game.ecs.components;

import com.badlogic.ashley.core.Component;

public class InputComponent implements Component {
    public final Vector2 moveVector, shootVector;

    public Inputcomponent () {
        this.moveVector = new Vector2();
        this.shootVector = new Vector2();
    }
}