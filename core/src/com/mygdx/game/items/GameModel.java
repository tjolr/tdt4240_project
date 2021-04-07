package com.mygdx.game.items;

import java.util.ArrayList;


public class GameModel {
    // Firebase needs properties to be public to work
    public String gameId;
    public GameState gameState;
    public String host;
    public ArrayList<String> players;
    public ArrayList<PlayerUpdateModel> playerUpdateModels;

    public enum GameState {
        SETUP,
        ACTIVE,
        RESULT
    }

    public GameModel(String gameId, String host, ArrayList<String> players, ArrayList<PlayerUpdateModel> playerUpdateModels) {
        this.gameId = gameId;
        this.gameState = GameState.SETUP;
        this.host = host;
        this.players = players;
        this.playerUpdateModels = playerUpdateModels;
    }
}
