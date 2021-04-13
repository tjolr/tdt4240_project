package com.mygdx.game.screens.game;

import com.mygdx.game.firebase.FirebaseController;
import com.mygdx.game.game_state.GlobalStateModel;

public class GameController {
    private static GameController gameControllerInstance = null;
    private FirebaseController firebaseController;
    private GlobalStateModel globalStateModel;

    private GameController() {
        this.globalStateModel = GlobalStateModel.getInstance();
        this.firebaseController = FirebaseController.getInstance();

        firebaseController.stopListenToPlayersInGame();
        firebaseController.stopListenToGameStateInGame();

        firebaseController.listenToPlayerUpdateModelsInGame(globalStateModel.getGameId());
    }

    public static GameController getInstance() {
        if (gameControllerInstance == null) {
            gameControllerInstance = new GameController();
        }
        return gameControllerInstance;
    }


}
