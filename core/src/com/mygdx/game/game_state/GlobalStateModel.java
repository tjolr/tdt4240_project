package com.mygdx.game.game_state;

import com.mygdx.game.items.SimpleGameModel;

import java.util.ArrayList;

public class GlobalStateModel {

    private static GlobalStateModel globalStateModelInstance = null;

    private String username;
    private String host;
    private ArrayList<String> players;

    private SimpleGameModel.GameState gameState;
    private String gameId;

    private GlobalStateModel() {
        this.username = null;
        this.players = new ArrayList<>();
    }

    // Singleton
    public static GlobalStateModel getInstance() {
        if (globalStateModelInstance == null)
        {
            globalStateModelInstance = new GlobalStateModel();
        }
        return globalStateModelInstance;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public ArrayList<String> getPlayers() {
        return players;
    }

    public void addPlayer(String player) {
        this.players.add(player);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public SimpleGameModel.GameState getGameState() {
        return gameState;
    }

    public void setGameState(SimpleGameModel.GameState gameState) {
        this.gameState = gameState;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

}
