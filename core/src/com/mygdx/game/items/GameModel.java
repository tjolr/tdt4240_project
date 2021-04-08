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

    // Default constructor required for calls to DataSnapshot.getValue(User.class)
    public GameModel() {}

    public GameModel(String gameId, GameState gameState, String host, ArrayList<String> players, ArrayList<PlayerUpdateModel> playerUpdateModels) {
        this.gameId = gameId;
        this.gameState = gameState;
        this.host = host;
        this.players = players;
        this.playerUpdateModels = playerUpdateModels;
    }
}
