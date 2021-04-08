package com.mygdx.game.screens.navigation;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.game.firebase.FirebaseController;
import com.mygdx.game.screens.game.GameView;
import com.mygdx.game.screens.game_setup.GameSetupController;
import com.mygdx.game.screens.game_setup.GameSetupModel;
import com.mygdx.game.screens.game_setup.GameSetupView;
import com.mygdx.game.screens.main_menu.MainMenuView;
import com.mygdx.game.screens.room.RoomController;
import com.mygdx.game.screens.room.RoomModel;
import com.mygdx.game.screens.room.RoomView;
import com.mygdx.game.screens.set_name.SetNameView;
import com.mygdx.game.screens.settings.SettingsView;

public class NavigatorController implements Disposable {

    private Screen screen;
    private FirebaseController firebaseController;

    public NavigatorController() {
        this.changeScreen(NavigationModel.NavigationScreen.MAINMENU);
        firebaseController = FirebaseController.getInstance();
    }

    public void changeScreen(NavigationModel.NavigationScreen screen) {
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
                gameSetupController.setFirebaseController(firebaseController);
                GameSetupView gameSetupView = new GameSetupView(this, gameSetupController, gameSetupModel);
                this.setScreen(gameSetupView);
                break;
            case ROOM:
                RoomModel roomModel = new RoomModel();
                RoomController roomController = new RoomController(roomModel);
                RoomView roomView = new RoomView(this, roomController, roomModel);
                this.setScreen(roomView);
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
