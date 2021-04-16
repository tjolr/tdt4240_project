package com.mygdx.game.ecs.components;

import com.badlogic.ashley.core.Component;

public class BotComponent implements Component {
    public boolean attacking = false;
    public long lastAttack = 0;
    public int attackInterval;
    public int attackPower;
}
