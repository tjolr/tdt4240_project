package com.mygdx.game.screens.game_setup;


import java.util.ArrayList;

public class GameSetupModel {
    private ArrayList<String> availableGames;

    public GameSetupModel() {
        this.availableGames = new ArrayList<>();
    }

    public ArrayList<String> getAvailableGames() {
        return availableGames;
    }

    public void addAvailableGame(String availableGame) {
        this.availableGames.add(availableGame);
    }

}
