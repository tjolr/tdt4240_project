package com.mygdx.game.ecs.components;

import com.badlogic.ashley.core.Component;

public class BulletComponent implements Component {
    public float lifetime; // how long the bullet will live if it doesn't hit anything
}
