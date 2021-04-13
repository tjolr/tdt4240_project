package com.mygdx.game.game_state;

import com.mygdx.game.items.PlayerUpdateModel;
import com.mygdx.game.items.SimpleGameModel;

import java.util.ArrayList;
import java.util.HashMap;

public class GlobalStateModel {

    private static GlobalStateModel globalStateModelInstance = null;

    private String username;
    private String host;
    private ArrayList<String> players;

    private HashMap<String, PlayerUpdateModel> playerUpdateModels;
    private SimpleGameModel.GameState gameState;
    private String gameId;

    private GlobalStateModel() {
        this.username = null;
        this.players = new ArrayList<>();
        this.playerUpdateModels = new HashMap<String, PlayerUpdateModel>();

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

    public HashMap<String, PlayerUpdateModel> getPlayerUpdateModels() {
        return playerUpdateModels;
    }

    public void setPlayerUpdateModels(HashMap<String, PlayerUpdateModel> playerUpdateModels) {
        this.playerUpdateModels = playerUpdateModels;
    }

    public void setPlayerUpdateModel(PlayerUpdateModel playerUpdateModel) {
        this.playerUpdateModels.put(playerUpdateModel.player, playerUpdateModel);
    }
}
