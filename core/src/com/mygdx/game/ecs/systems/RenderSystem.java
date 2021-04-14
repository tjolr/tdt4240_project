package com.mygdx.game.ecs.systems;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.ecs.components.DirectionComponent;
import com.mygdx.game.ecs.components.PositionComponent;
import com.mygdx.game.ecs.components.SpriteComponent;

public class RenderSystem extends IteratingSystem {
    private SpriteBatch spriteBatch;
    private Array<Entity> renderQueue;

    private ComponentMapper<SpriteComponent> spriteMapper;
    private ComponentMapper<PositionComponent> positionMapper;
    private ComponentMapper<DirectionComponent> directionMapper;

    public RenderSystem() {
        super(Family.all(SpriteComponent.class, PositionComponent.class, DirectionComponent.class).get());

        spriteBatch = new SpriteBatch();
        renderQueue = new Array<Entity>();

        spriteMapper = ComponentMapper.getFor(SpriteComponent.class);
        positionMapper = ComponentMapper.getFor(PositionComponent.class);
        directionMapper = ComponentMapper.getFor(DirectionComponent.class);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        spriteBatch.begin();

        for (Entity entity : renderQueue) {
            SpriteComponent spriteComponent = spriteMapper.get(entity);

            if (spriteComponent.textureRegion == null) {
                continue;
            }

            PositionComponent positionComponent = positionMapper.get(entity);
            DirectionComponent directionComponent = directionMapper.get(entity);

            float width = spriteComponent.textureRegion.getRegionWidth() * spriteComponent.scaleX;
            float height = spriteComponent.textureRegion.getRegionHeight() * spriteComponent.scaleY;
            float originX = width * 0.5f;
            float originY = height * 0.5f;
            if (spriteComponent.offset) {
                originX -= 60;
                originY += 50;
            }

            spriteBatch.draw(
                    spriteComponent.textureRegion,
                    positionComponent.position.x,
                    positionComponent.position.y,
                    originX,
                    originY,
                    width,
                    height,
                    1,
                    1,
                    directionComponent.direction.angleDeg());
        }

        spriteBatch.end();
        renderQueue.clear();
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        renderQueue.add(entity);
    }
}
