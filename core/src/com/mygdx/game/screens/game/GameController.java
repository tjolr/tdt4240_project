package com.mygdx.game.screens.game;

import com.mygdx.game.firebase.FirebaseController;
import com.mygdx.game.game_state.GlobalStateModel;
import com.mygdx.game.items.PlayerUpdateModel;
import com.mygdx.game.screens.navigation.NavigationController;
import com.mygdx.game.screens.navigation.NavigationModel;

public class GameController {
    // Singleton
    private static GameController gameControllerInstance = null;
    private FirebaseController firebaseController;
    private GlobalStateModel globalStateModel;
    private NavigationController navigationController;

    private GameController() {
        this.globalStateModel = GlobalStateModel.getInstance();
        this.firebaseController = FirebaseController.getInstance();
        this.navigationController = NavigationController.getInstance();

        firebaseController.listenToPlayerUpdateModelsInGame(globalStateModel.getGameId());
    }

    public static GameController getInstance() {
        if (gameControllerInstance == null) {
            gameControllerInstance = new GameController();
        }
        return gameControllerInstance;
    }

    public void checkIfPlayerHasDied(){
        if(
            globalStateModel != null &&
            globalStateModel.getPlayerUpdateModels().size() > 0 &&
            globalStateModel.getPlayerUpdateModels().get(globalStateModel.getUsername()).health <= 0
        ) {
            navigationController.changeScreen(NavigationModel.NavigationScreen.RESULT);
        }
    }
}
