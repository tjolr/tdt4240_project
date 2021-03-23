package com.mygdx.game.screens.navigation;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.game.screens.game.GameView;
import com.mygdx.game.screens.game_setup.GameSetupController;
import com.mygdx.game.screens.game_setup.GameSetupModel;
import com.mygdx.game.screens.game_setup.GameSetupView;
import com.mygdx.game.screens.main_menu.MainMenuView;
import com.mygdx.game.screens.settings.SettingsView;

public class NavigatorController implements Disposable {

    private Screen screen;

    public NavigatorController() {
        this.changeScreen(NavigationModel.NavigationScreen.MAINMENU);
    }

    public void changeScreen(NavigationModel.NavigationScreen screen) {
        switch(screen) {
            case MAINMENU:
                MainMenuView mainMenuView = new MainMenuView(this);
                this.setScreen(mainMenuView);
                break;
            case GAMESETUP:
                GameSetupModel gameSetupModel = new GameSetupModel();
                GameSetupController gameSetupController = new GameSetupController(gameSetupModel);
                GameSetupView gameSetupView = new GameSetupView(this, gameSetupController, gameSetupModel);
                this.setScreen(gameSetupView);
                break;
            case SETTINGS:
                SettingsView settingsView = new SettingsView(this);
                this.setScreen(settingsView);
                break;
            case GAME:
                GameView gameView = new GameView(this);
                this.setScreen(gameView);
                break;
        }
    }


    public void setScreen(Screen screen) {
        if (this.screen != null) {
            this.screen.hide();
            this.screen.dispose();
        }
        this.screen = screen;
        if (this.screen != null) {
            this.screen.show();
        }
    }

    public Screen getScreen() {
        return screen;
    }

    @Override
    public void dispose() {
        if (this.screen != null) {
            this.screen.dispose();
        }
        this.screen = null;
    }


}
