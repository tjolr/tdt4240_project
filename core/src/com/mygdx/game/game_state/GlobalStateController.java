package com.mygdx.game.game_state;


import com.mygdx.game.items.SimpleGameModel;
import com.mygdx.game.screens.navigation.NavigationModel;
import com.mygdx.game.screens.navigation.NavigatorController;

public class GlobalStateController {
    private static GlobalStateController globalStateControllerInstance = null;

    private GlobalStateModel globalStateModel;
    private NavigatorController navigatorController;

    private GlobalStateController() {
        this.globalStateModel = GlobalStateModel.getInstance();
        this.navigatorController = NavigatorController.getInstance();
    }

    // Singleton
    public static GlobalStateController getInstance() {
        if (globalStateControllerInstance == null)
        {
            globalStateControllerInstance = new GlobalStateController();
        }
        return globalStateControllerInstance;
    }

    public void addPlayer(String player) {
        this.globalStateModel.addPlayer(player);
    }

    public void setHost(String host) {
        this.globalStateModel.setHost(host);
    }

    public void setUsername(String username) {
        this.globalStateModel.setUsername(username);
    }


    public void setUserAsGameHost() {
        this.setHost(this.globalStateModel.getUsername());
    }

    public SimpleGameModel.GameState getGameState() {
        return this.globalStateModel.getGameState();
    }

    public void setGameState(SimpleGameModel.GameState gameState) {
        this.globalStateModel.setGameState(gameState);

        if (gameState.equals(SimpleGameModel.GameState.ACTIVE)) {
            navigatorController.changeScreen(NavigationModel.NavigationScreen.GAME);
        }
    }

    public void setGameId(String gameId) {
        this.globalStateModel.setGameId(gameId);
    }

}