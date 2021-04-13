package com.mygdx.game.ecs.components;

import com.badlogic.ashley.core.Component;

public class ExternalPlayerComponent implements Component {
    public String playerName;
    public boolean internal;
    public int index;
}
