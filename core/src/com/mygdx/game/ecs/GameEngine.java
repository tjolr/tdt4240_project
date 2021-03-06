package com.mygdx.game.ecs;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.assets.AssetsController;
import com.mygdx.game.ecs.components.BotSpawnComponent;
import com.mygdx.game.ecs.components.DirectionComponent;
import com.mygdx.game.ecs.components.PositionComponent;
import com.mygdx.game.ecs.components.SpriteComponent;
import com.mygdx.game.ecs.entities.EntityFactory;
import com.mygdx.game.ecs.systems.BotAttackSystem;
import com.mygdx.game.ecs.systems.BotSpawnSystem;
import com.mygdx.game.ecs.systems.BoundSystem;
import com.mygdx.game.ecs.systems.CollisionSystem;
import com.mygdx.game.ecs.systems.PlayerHealthScoreSystem;
import com.mygdx.game.ecs.systems.MovementSystem;
import com.mygdx.game.ecs.systems.PlayerControlSystem;
import com.mygdx.game.ecs.systems.PlayerDirectionSystem;
import com.mygdx.game.ecs.systems.RenderSystem;
import com.mygdx.game.ecs.systems.BotControlSystem;
import com.mygdx.game.ecs.systems.ShootingSystem;
import com.mygdx.game.ecs.systems.BulletSystem;
import com.mygdx.game.game_state.GlobalStateModel;
import com.mygdx.game.items.PlayerUpdateModel;
import java.util.Map;

public class GameEngine extends PooledEngine {
    // GameEngine is a Singleton class
    private static GameEngine gameEngineInstance = null;

    private final EntityFactory entityFactory;
    private static GlobalStateModel globalStateModel;
    private static AssetsController assetsController;

    private Entity player;

    private GameEngine() {
        entityFactory = EntityFactory.getInstance();
        globalStateModel = GlobalStateModel.getInstance();
        assetsController = AssetsController.getInstance();
    }

    public static GameEngine getInstance() {
        if (gameEngineInstance == null)
            gameEngineInstance = new GameEngine();

        return gameEngineInstance;
    }

    public void initializeEngine() {
        // Reset the game engine if it isn't the first round
        GameEngine.gameEngineInstance.removeAllEntities();
        GameEngine.gameEngineInstance.clearPools();
        // Initialize some entities that don't need their own factory.
        createBackground();
        addPlayerUpdateModels();
        addBotSpawner();

        player = entityFactory.createPlayer(globalStateModel.getUsername(),200, 200);
        gameEngineInstance.addEntity(player);

        gameEngineInstance.addSystem(new RenderSystem());
        gameEngineInstance.addSystem(new PlayerControlSystem());
        gameEngineInstance.addSystem(new PlayerDirectionSystem());
        gameEngineInstance.addSystem(new MovementSystem());
        gameEngineInstance.addSystem(new ShootingSystem());
        gameEngineInstance.addSystem(new BulletSystem());
        gameEngineInstance.addSystem(new BotControlSystem());
        gameEngineInstance.addSystem(new PlayerHealthScoreSystem());
        gameEngineInstance.addSystem(new BotSpawnSystem());
        gameEngineInstance.addSystem(new BoundSystem());
        gameEngineInstance.addSystem(new CollisionSystem());
        gameEngineInstance.addSystem(new BotAttackSystem());
    }

    private void createBackground() {
        Entity background = gameEngineInstance.createEntity();

        PositionComponent position = gameEngineInstance.createComponent(PositionComponent.class);
        SpriteComponent sprite = gameEngineInstance.createComponent(SpriteComponent.class);
        DirectionComponent direction = gameEngineInstance.createComponent(DirectionComponent.class);

        position.position.x = 0;
        position.position.y = 0;

        // assetsController returns the right background according to the current theme
        sprite.textureRegion = new TextureRegion(assetsController.getBackgroundTextureInGame(),0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        background.add(position);
        background.add(sprite);
        background.add(direction);

        gameEngineInstance.addEntity(background);
    }

    private void addBotSpawner() {
        Entity spawner = gameEngineInstance.createEntity();

        BotSpawnComponent botSpawnComponent = gameEngineInstance.createComponent(BotSpawnComponent.class);

        spawner.add(botSpawnComponent);

        gameEngineInstance.addEntity(spawner);
    }

    public Entity getPlayer() {
        // To give other packages easy access to the current player
        return player;
    }

    private void addPlayerUpdateModels() {
        // Add entities with external player models that are fetched from the database and saved in GlobalStateModel
        int index = 0;
        for (Map.Entry<String, PlayerUpdateModel> playerUpdateModelEntry : globalStateModel.getPlayerUpdateModels().entrySet()) {
            PlayerUpdateModel playerUpdateModel = playerUpdateModelEntry.getValue();

            Entity externalPlayer = entityFactory.createExternalPlayer(playerUpdateModel.player, playerUpdateModel.health, index);
            gameEngineInstance.addEntity(externalPlayer);

            index++;
        }
    }
}
