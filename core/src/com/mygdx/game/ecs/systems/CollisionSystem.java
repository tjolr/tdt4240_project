package com.mygdx.game.ecs.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.mygdx.game.ecs.GameEngine;
import com.mygdx.game.ecs.components.BotComponent;
import com.mygdx.game.ecs.components.BulletComponent;
import com.mygdx.game.ecs.components.PositionComponent;
import com.mygdx.game.ecs.components.SpriteComponent;
import com.mygdx.game.firebase.FirebaseController;
import com.mygdx.game.game_state.GlobalStateModel;

public class CollisionSystem extends IteratingSystem {
    private final ComponentMapper<SpriteComponent> spriteMapper;
    private final ComponentMapper<BotComponent> botComponentMapper;
    private GlobalStateModel globalStateModel;
    private FirebaseController firebaseController;

    public CollisionSystem() {
        super(Family.all(BotComponent.class, SpriteComponent.class, PositionComponent.class).get());

        spriteMapper = ComponentMapper.getFor(SpriteComponent.class);
        botComponentMapper = ComponentMapper.getFor(BotComponent.class);

        this.globalStateModel = GlobalStateModel.getInstance();
        this.firebaseController = FirebaseController.getInstance();
    }

    public void processEntity(Entity entity, float deltaTime) {
        ImmutableArray<Entity> bullets = GameEngine.getInstance().getEntitiesFor(Family.all(BulletComponent.class).get());
        for (Entity bullet : bullets) {
            if(colliding(bullet, entity)) {
                GameEngine.getInstance().removeEntity(entity);
                GameEngine.getInstance().removeEntity(bullet);

                firebaseController.incrementValueInDb(
                    "game."+globalStateModel.getGameId()+".playerUpdateModels."
                    +globalStateModel.getUserPlayerUpdateModelId()+".score",
                    5
                );
            }
        }

        if (colliding(GameEngine.getInstance().getPlayer(), entity)) {
            BotComponent botComponent = botComponentMapper.get(entity);
            botComponent.attacking = true;
        }
    }

    public boolean colliding(Entity first, Entity second) {
        Polygon firstPolygon = spriteMapper.get(first).polygon;
        Polygon secondPolygon = spriteMapper.get(second).polygon;
        return Intersector.overlapConvexPolygons(firstPolygon, secondPolygon);
    }
}

