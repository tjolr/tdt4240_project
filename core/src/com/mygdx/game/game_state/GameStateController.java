package com.mygdx.game.game_state;


public class GameStateController {

    private static GameStateController gameStateControllerInstance = null;
    private GameStateModel gameStateModel;

    private GameStateController() {
        this.gameStateModel = GameStateModel.GameStateModel();
    }

    // Singleton
    public static GameStateController GameStateController() {
        if (gameStateControllerInstance == null)
        {
            gameStateControllerInstance = new GameStateController();
        }
        return gameStateControllerInstance;
    }

    public void addPlayer(String player) {
        this.gameStateModel.addPlayer(player);
    }

    public void setHost(String host) {
        this.gameStateModel.setHost(host);
    }

    public void setUsername(String username) {
        this.gameStateModel.setUsername(username);
    }


    public void setUserAsGameHost() {
        this.setHost(this.gameStateModel.getUsername());
    }


}
