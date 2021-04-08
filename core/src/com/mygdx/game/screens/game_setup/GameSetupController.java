package com.mygdx.game.screens.game_setup;

import com.mygdx.game.firebase.FirebaseController;
import com.mygdx.game.game_state.GameStateController;
import com.mygdx.game.game_state.GameStateModel;
import com.mygdx.game.items.GameModel;
import com.mygdx.game.items.PlayerUpdateModel;

import java.util.ArrayList;

public class GameSetupController {
    private static GameSetupController gameSetupControllerInstance = null;
    private GameSetupModel gameSetupModel;
    private GameStateController gameStateController;
    private GameStateModel gameStateModel;
    private FirebaseController firebaseController;

    private GameSetupController() {
        this.gameSetupModel = GameSetupModel.getInstance();
        this.gameStateController = GameStateController.GameStateController();
        this.gameStateModel = GameStateModel.GameStateModel();

    }

    public static GameSetupController getInstance() {
        if (gameSetupControllerInstance == null) {
            gameSetupControllerInstance = new GameSetupController();
        }
        return gameSetupControllerInstance;
    }

    public void setFirebaseController(FirebaseController firebaseController) {
        this.firebaseController = firebaseController;
        this.listenToAvailableGames();
    }


    public void hostCreateGame(){
        gameStateController.setUserAsGameHost();

        // Creating the arrays, and initializing with the first player which is the host itself.
        PlayerUpdateModel hostPlayerUpdateModel = new PlayerUpdateModel(gameStateModel.getHost(), 100f, 0);
        ArrayList<PlayerUpdateModel> playerUpdateModels = new ArrayList<>();
        playerUpdateModels.add(hostPlayerUpdateModel);

        ArrayList<String> players = new ArrayList<>();
        players.add(gameStateModel.getHost());

        // tmp gameId
        String gameId = gameStateModel.getHost()+" game";
        GameModel gameModel = new GameModel(
            gameId,
            GameModel.GameState.SETUP,
            gameStateModel.getHost(),
            players,
            playerUpdateModels
        );

        firebaseController.writeToDb("game." + gameId, gameModel);
    }

    public void listenToAvailableGames() {
        this.firebaseController.listenToAvailableGames();
    }

    public void addAvailableGame(GameModel gameModel) {
        gameSetupModel.addAvailableGame(gameModel);
    }

}
