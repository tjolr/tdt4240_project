package com.mygdx.game.items;

public class SimpleGameModel {
    // Firebase needs properties to be public to work
    public String gameId;
    public GameState gameState;
    public String host;

    public enum GameState {
        SETUP,
        ACTIVE,
        RESULT
    }

    // Default constructor required for calls to DataSnapshot.getValue(User.class)
    public SimpleGameModel() {}

    public SimpleGameModel(String gameId, GameState gameState, String host){
        this.gameId = gameId;
        this.gameState = gameState;
        this.host = host;

    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
