package com.mygdx.game.game_state;

import java.util.ArrayList;

public class GameStateModel {

    private static GameStateModel gameStateModelInstance = null;

    private String username;
    private String host;
    private ArrayList<String> players;

    private GameStateModel() {
        this.username = null;
        this.players = new ArrayList<>();
    }

    // Singleton
    public static GameStateModel GameStateModel() {
        if (gameStateModelInstance == null)
        {
            gameStateModelInstance = new GameStateModel();
        }
        return gameStateModelInstance;
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
}
