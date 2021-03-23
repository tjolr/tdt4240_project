package com.mygdx.game.ecs.components;

import com.badlogic.ashley.core.Component;

public class PlayerComponent implements Component {
    public int playerID;

    public PlayerComponent (int playerID) {
        this.playerID = playerID;
    }
}