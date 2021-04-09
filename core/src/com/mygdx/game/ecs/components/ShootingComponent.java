package com.mygdx.game.ecs.components;

import com.badlogic.ashley.core.Component;

public class ShootingComponent implements Component {
    public int interval = 10;
    public int cooldown = 0;
    public boolean fire = false;
}
