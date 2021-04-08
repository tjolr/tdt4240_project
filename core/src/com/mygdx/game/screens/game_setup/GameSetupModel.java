package com.mygdx.game.screens.game_setup;


import com.mygdx.game.items.SimpleGameModel;

import java.util.ArrayList;

public class GameSetupModel {
    private static GameSetupModel gameSetupModelInstance = null;
    private ArrayList<SimpleGameModel> availableGames;

    private GameSetupModel() {
        this.availableGames = new ArrayList<>();
    }

    public static GameSetupModel getInstance() {
        if (gameSetupModelInstance == null) {
            gameSetupModelInstance = new GameSetupModel();
        }
        return gameSetupModelInstance;
    }

    public ArrayList<SimpleGameModel> getAvailableGames() {
        return availableGames;
    }

    public void setAvailableGames(ArrayList<SimpleGameModel> availableGames) {

        this.availableGames = new ArrayList<SimpleGameModel>(availableGames);
    }

}
