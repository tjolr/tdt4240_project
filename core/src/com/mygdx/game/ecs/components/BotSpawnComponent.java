package com.mygdx.game.ecs.components;

import com.badlogic.ashley.core.Component;
import java.util.Date;

public class BotSpawnComponent implements Component {
    public long lastSpawn = (new Date()).getTime(); // unix time of last spawned bot
    public int interval = 1000;
}
