package com.mygdx.game.items;

import java.util.ArrayList;

public class GameModel extends SimpleGameModel {
    // Firebase needs properties to be public to work
    public ArrayList<String> players;
    public ArrayList<PlayerUpdateModel> playerUpdateModels;

    // Default constructor required for calls to DataSnapshot.getValue(User.class)
    public GameModel () {
        super();
    }

    public GameModel(String gameId, GameState gameState, String host, ArrayList<String> players, ArrayList<PlayerUpdateModel> playerUpdateModels) {
        super(gameId, gameState, host);
        this.players = players;
        this.playerUpdateModels = playerUpdateModels;
    }

    public ArrayList<String> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<String> players) {
        this.players = players;
    }

    public ArrayList<PlayerUpdateModel> getPlayerUpdateModels() {
        return playerUpdateModels;
    }

    public void setPlayerUpdateModels(ArrayList<PlayerUpdateModel> playerUpdateModels) {
        this.playerUpdateModels = playerUpdateModels;
    }
}
