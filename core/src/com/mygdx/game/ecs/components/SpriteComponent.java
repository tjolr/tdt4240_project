package com.mygdx.game.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;

public class SpriteComponent implements Component {
    public TextureRegion textureRegion = null;
    public Polygon polygon = null;
    public boolean offset = false;
    public float scaleX = 1;
    public float scaleY = 1;
}
