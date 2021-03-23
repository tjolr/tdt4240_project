package com.mygdx.game.ecs.components;

import com.badlogic.ashley.core.Component;

public class HealthComponent implements Component {
    public float health;

    public HealthComponent (float health) {
        this.health = health;
    }
}