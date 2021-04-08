package com.mygdx.game.ecs.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.ecs.components.HealthComponent;
import com.mygdx.game.ecs.components.PlayerComponent;

public class HealthRenderSystem extends IteratingSystem {
    private final ComponentMapper<HealthComponent> healthMapper;

    private final ShapeRenderer filledRenderer = new ShapeRenderer();
    private final ShapeRenderer lineRenderer = new ShapeRenderer();

    private final Color green = new Color(71f/255f, 110f/255f, 45f/255f, 1f);
    private final Color yellow = new Color(226f/255f, 185f/255f, 99f/255f, 1f);
    private final Color red = new Color(190f/255f, 65f/255f, 60f/255f, 1f);

    private final float healthBarHeight = Gdx.graphics.getHeight() * 0.02f;
    private final float originX = Gdx.graphics.getWidth() * 0.2f;
    private final float originY = Gdx.graphics.getHeight() - healthBarHeight * 2;
    private final float maxHealthWidth = Gdx.graphics.getWidth() * 0.6f;

    public HealthRenderSystem() {
        super(Family.all(HealthComponent.class, PlayerComponent.class).get());

        healthMapper = ComponentMapper.getFor(HealthComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        HealthComponent health = healthMapper.get(entity);
        float healthWidth = maxHealthWidth * (health.health/health.maxHealth);

        Gdx.gl20.glLineWidth(0.5f);
        filledRenderer.begin(ShapeRenderer.ShapeType.Filled);
        if (health.health/health.maxHealth < 0.5f) {
            filledRenderer.setColor(red);
        } else if (health.health/health.maxHealth < 0.75f) {
            filledRenderer.setColor(yellow);
        } else {
            filledRenderer.setColor(green);
        }

        filledRenderer.rect(originX, originY, healthWidth, healthBarHeight);

        filledRenderer.end();

        lineRenderer.begin(ShapeRenderer.ShapeType.Line);
        lineRenderer.rect(originX, originY, maxHealthWidth, healthBarHeight);
        lineRenderer.end();
    }
}
