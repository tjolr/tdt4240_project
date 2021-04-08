package com.mygdx.game.screens.game_setup;


import com.mygdx.game.items.GameModel;

import java.util.ArrayList;

public class GameSetupModel {
    private static GameSetupModel gameSetupModelInstance = null;
    private ArrayList<GameModel> availableGames;

    private GameSetupModel() {
        this.availableGames = new ArrayList<>();
    }

    public static GameSetupModel getInstance() {
        if (gameSetupModelInstance == null) {
            gameSetupModelInstance = new GameSetupModel();
        }
        return gameSetupModelInstance;
    }

    public ArrayList<GameModel> getAvailableGames() {
        return availableGames;
    }

    public void addAvailableGame(GameModel availableGame) {
        this.availableGames.add(availableGame);
    }

}
