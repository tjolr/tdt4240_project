package com.mygdx.game.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Disposable;

public class AssetsController implements Disposable {
    // AssetsController controls the assets fore backgrounds in game and menu
    private static AssetsController assetsControllerInstance = null;

    private String backgroundInGamePath;

    private TextureRegion backgroundTextureReqionMenu;
    private TextureRegion darkBackgroundTextureReqionMenu;
    private TextureRegion lightBackgroundTextureReqionMenu;

    private SpriteBatch sb;
    private Skin skin;


    private AssetsController(){
        this.skin = new Skin(Gdx.files.internal("neon_skin/neon-ui.json"));
        this.skin.getFont("font").getData().setScale(4f, 4f);

        this.darkBackgroundTextureReqionMenu = new TextureRegion(new Texture("sprites/darkMenu3.jpeg"),0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.lightBackgroundTextureReqionMenu = new TextureRegion(new Texture("sprites/lightMenu3.jpeg"),0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        this.setDarkTheme();
        this.sb = new SpriteBatch();
    }

    public static AssetsController getInstance() {
        if (assetsControllerInstance == null) {
            assetsControllerInstance = new AssetsController();
        }
        return assetsControllerInstance;
    }

    public void setDarkTheme(){
        this.backgroundInGamePath = "sprites/HexagonTile_DIFF.png";
        this.backgroundTextureReqionMenu = darkBackgroundTextureReqionMenu;
    }

    public void setLightTheme(){
        this.backgroundInGamePath = "sprites/HexagonTile_SPEC2.jpeg";
        this.backgroundTextureReqionMenu = lightBackgroundTextureReqionMenu;
    }

    public void renderMenuBackground(){
        sb.begin();
        sb.draw(backgroundTextureReqionMenu, 0 ,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sb.end();
    }

    public Skin getSkin() {
        return skin;
    }

    public Texture getBackgroundTextureInGame() {
        return new Texture(this.backgroundInGamePath);
    }

    @Override
    public void dispose() {
        sb.dispose();
        skin.dispose();
    }
}
