package com.mygdx.game.ecs.components;

import com.badlogic.ashley.core.Component;

public class ExternalPlayerComponent implements Component {
    public String playerName;
    public int index; // for keeping track of sorting
    public int score;
}
