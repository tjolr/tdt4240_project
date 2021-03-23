package com.mygdx.game.screens.game_setup;

public class GameSetupController {
    private GameSetupModel gameSetupModel;

    public GameSetupController(GameSetupModel gameSetupModel) {
        this.gameSetupModel = gameSetupModel;
        // Temporary names just to render something on the UI
        gameSetupModel.addAvailableGame("Anders");
        gameSetupModel.addAvailableGame("Tjol");
        gameSetupModel.addAvailableGame("Vidar");
        gameSetupModel.addAvailableGame("Yalda");
        gameSetupModel.addAvailableGame("Erna Solberg");
    }

}
