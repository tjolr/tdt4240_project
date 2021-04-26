package com.mygdx.game.screens.navigation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.game.screens.game.GameController;
import com.mygdx.game.screens.game.GameView;
import com.mygdx.game.screens.game_setup.GameSetupController;
import com.mygdx.game.screens.game_setup.GameSetupModel;
import com.mygdx.game.screens.game_setup.GameSetupView;
import com.mygdx.game.screens.main_menu.MainMenuView;
import com.mygdx.game.screens.result.ResultController;
import com.mygdx.game.screens.result.ResultView;
import com.mygdx.game.screens.room.RoomController;
import com.mygdx.game.screens.room.RoomView;
import com.mygdx.game.screens.set_name.SetNameView;
import com.mygdx.game.screens.settings.SettingsView;

public class NavigationController implements Disposable {
    /*
    * NavigationController is the main class for handling what screens should be shown, and
    * change screens correctly.
    * */

    // Singleton
    private static NavigationController navigationControllerInstance = null;

    private Screen screen;

    private NavigationController() {}

    public static NavigationController getInstance() {
        if (navigationControllerInstance == null) {
            navigationControllerInstance = new NavigationController();
        }
        return navigationControllerInstance;
    }

    public void changeScreen(NavigationModel.NavigationScreen screen) {
        // Make sure that it runs on main thread which is initialized with OpenGL ES
        // Without setting the screens to use the main thread, the network thread can try to render
        // LibGDX graphics, which causes an error
        Gdx.app.postRunnable(()-> {
            switch(screen) {
                case MAINMENU:
                    MainMenuView mainMenuView = new MainMenuView(this);
                    this.setScreen(mainMenuView);
                    break;
                case SET_NAME:
                    SetNameView setNameView = new SetNameView(this);
                    this.setScreen(setNameView);
                    break;
                case GAMESETUP:
                    GameSetupModel gameSetupModel = GameSetupModel.getInstance();
                    GameSetupController gameSetupController = GameSetupController.getInstance();
                    gameSetupController.initGameSetupController();
                    GameSetupView gameSetupView = new GameSetupView(this,gameSetupController, gameSetupModel);
                    this.setScreen(gameSetupView);
                    break;
                case ROOM:
                    RoomController roomController = RoomController.getInstance();
                    RoomView roomView = new RoomView(this, roomController);
                    this.setScreen(roomView);
                    break;
                case SETTINGS:
                    SettingsView settingsView = new SettingsView(this);
                    this.setScreen(settingsView);
                    break;
                case GAME:
                    GameController gameController = GameController.getInstance();
                    GameView gameView = new GameView(gameController);
                    this.setScreen(gameView);
                    break;
                case RESULT:
                    ResultController resultController = ResultController.getInstance();
                    ResultView resultView = new ResultView(this, resultController);
                    this.setScreen(resultView);
                    break;
            }
        });
    }

    // disposing the screen that is not shown
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
