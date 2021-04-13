package com.mygdx.game.ecs.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.ecs.components.BotComponent;
import com.mygdx.game.ecs.components.ExternalPlayerComponent;
import com.mygdx.game.ecs.components.HealthComponent;
import com.mygdx.game.ecs.components.PlayerComponent;
import com.mygdx.game.game_state.GlobalStateModel;

public class HealthRenderSystem extends IteratingSystem {
    private final ComponentMapper<HealthComponent> healthMapper;
    private final ComponentMapper<ExternalPlayerComponent> externalPlayerMapper;

    private final ShapeRenderer filledRenderer = new ShapeRenderer();
    private final ShapeRenderer lineRenderer = new ShapeRenderer();
    private BitmapFont font = new BitmapFont();
    private SpriteBatch sb = new SpriteBatch();

    private final Color green = new Color(71f/255f, 110f/255f, 45f/255f, 1f);
    private final Color yellow = new Color(226f/255f, 185f/255f, 99f/255f, 1f);
    private final Color red = new Color(190f/255f, 65f/255f, 60f/255f, 1f);

    private final float healthBarHeight = Gdx.graphics.getHeight() * 0.02f;
    final float originY = Gdx.graphics.getHeight() - healthBarHeight * 4;
    private final float marginX = 30f;

    private GlobalStateModel globalStateModel;
    private int numberOfExternalPlayers;

    public HealthRenderSystem() {
        super(Family.all(HealthComponent.class).one(ExternalPlayerComponent.class).exclude(BotComponent.class).get());

        healthMapper = ComponentMapper.getFor(HealthComponent.class);
        externalPlayerMapper = ComponentMapper.getFor(ExternalPlayerComponent.class);

        this.globalStateModel = GlobalStateModel.getInstance();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        HealthComponent healthComponent = healthMapper.get(entity);
        ExternalPlayerComponent externalPlayerComponent = externalPlayerMapper.get(entity);

        // Set number of external players to calculate how much width each player health bar should have
        if (globalStateModel.getPlayerUpdateModels().size() != numberOfExternalPlayers){
            numberOfExternalPlayers = globalStateModel.getPlayerUpdateModels().size();
        }

        if (externalPlayerComponent != null) {
            // Render external players healthbar on bottom of the screen
            final float healthbarWidth = Gdx.graphics.getWidth() / numberOfExternalPlayers;
            final float healthbarOriginX = externalPlayerComponent.index * healthbarWidth;

            // Updating healthComponent to use latest value from globalStateModel
            healthComponent.health = globalStateModel.getPlayerUpdateModels().get(externalPlayerComponent.playerName).health;
            drawHealthBar(healthComponent, healthbarWidth, healthbarOriginX, originY, externalPlayerComponent.playerName);
        }
    }

    public void drawHealthBar(HealthComponent healthComponent, float maxHealthWidthAdjusted, float currOriginX, float currOriginY, String playerName) {
        // Calculate healthWidth to be shown, and maxHealthwidth
        float healthWidth = maxHealthWidthAdjusted * (healthComponent.health / healthComponent.maxHealth) - 2 * marginX;
        float maxHealthWidth = maxHealthWidthAdjusted - 2 * marginX;

        currOriginX = currOriginX + marginX;

        // Draw healthbar fill with colors corresponding to health level
        filledRenderer.begin(ShapeRenderer.ShapeType.Filled);

        if (healthComponent.health / healthComponent.maxHealth < 0.5f) {
            filledRenderer.setColor(red);
        } else if (healthComponent.health / healthComponent.maxHealth < 0.75f) {
            filledRenderer.setColor(yellow);
        } else {
            filledRenderer.setColor(green);
        }

        filledRenderer.rect(currOriginX, currOriginY, healthWidth, healthBarHeight);
        filledRenderer.end();

        // Draw line around health to show graphically maximum health level
        Gdx.gl20.glLineWidth(0.5f);
        lineRenderer.begin(ShapeRenderer.ShapeType.Line);
        lineRenderer.rect(currOriginX, currOriginY, maxHealthWidth, healthBarHeight);
        lineRenderer.end();

        // Write playername above healthbar
        sb.begin();
        font.getData().setScale(2);
        font.setColor(Color.WHITE);
        font.draw(sb, playerName, currOriginX, currOriginY + 3 * healthBarHeight);
        sb.end();
    }
}
