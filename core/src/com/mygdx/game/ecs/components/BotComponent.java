package com.mygdx.game.ecs.components;

import com.badlogic.ashley.core.Component;

public class BotComponent implements Component {
    public boolean attacking = false;
    public long lastAttack = 0; // unix time of last attack
    public int attackInterval;
    public int attackPower;
}
