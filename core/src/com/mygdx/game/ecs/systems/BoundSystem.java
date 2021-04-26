package com.mygdx.game.ecs.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.ecs.components.DirectionComponent;
import com.mygdx.game.ecs.components.PositionComponent;
import com.mygdx.game.ecs.components.SpriteComponent;

public class BoundSystem extends IteratingSystem {
    private final ComponentMapper<SpriteComponent> spriteMapper;
    private final ComponentMapper<PositionComponent> positionMapper;
    private final ComponentMapper<DirectionComponent> directionMapper;

    private ShapeRenderer shapeRenderer;
    private Array<Entity> renderQueue;

    public BoundSystem() {
        // System for making sure the hitboxes follows the entities
        super(Family.all(SpriteComponent.class, PositionComponent.class, DirectionComponent.class).get());

        spriteMapper = ComponentMapper.getFor(SpriteComponent.class);
        positionMapper = ComponentMapper.getFor(PositionComponent.class);
        directionMapper = ComponentMapper.getFor(DirectionComponent.class);

        shapeRenderer = new ShapeRenderer();
        renderQueue = new Array<Entity>();
    }

    /* update function only for debug purposes */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        for (Entity entity: renderQueue) {
            SpriteComponent sprite = spriteMapper.get(entity);
            if (sprite.polygon == null) {
                continue;
            }
            shapeRenderer.polygon(sprite.polygon.getTransformedVertices());
        }
        shapeRenderer.end();
        renderQueue.clear();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent position = positionMapper.get(entity);
        DirectionComponent direction = directionMapper.get(entity);
        SpriteComponent sprite = spriteMapper.get(entity);

        if (sprite.polygon != null) {
            sprite.polygon.setPosition(position.position.x,position.position.y);
            sprite.polygon.setRotation(direction.direction.angleDeg());
        }
        /* Uncomment the following line to display "hitboxes" */
        //renderQueue.add(entity);
    }
}
