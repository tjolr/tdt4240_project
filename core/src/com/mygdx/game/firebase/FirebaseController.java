package com.mygdx.game.firebase;

import com.mygdx.game.items.SimpleGameModel;
import com.mygdx.game.screens.game_setup.GameSetupController;

import java.util.ArrayList;

public class FirebaseController implements FirebaseInterface {
    private static FirebaseController firebaseControllerInstance = null;
    private FirebaseInterface firebaseInterface;
    private GameSetupController gameSetupController;

    private FirebaseController() {
        gameSetupController = GameSetupController.getInstance();
    }

    public static FirebaseController getInstance() {
        if (firebaseControllerInstance == null) {
            firebaseControllerInstance = new FirebaseController();
        }
        return firebaseControllerInstance;
    }

    public void setFirebaseInterface(FirebaseInterface firebaseInterface) {
        this.firebaseInterface = firebaseInterface;
    }

    @Override
    public void writeToDb(String target, Object value) {
        firebaseInterface.writeToDb(target, value);
    }

    @Override
    public void appendToArrayInDb(String target, Object value) {
        firebaseInterface.appendToArrayInDb(target, value);
    }

    @Override
    public void listenToAvailableGames() {
        firebaseInterface.listenToAvailableGames();
    }


    public void setAvailableGames(ArrayList<SimpleGameModel> availableGames) {
        gameSetupController.addAvailableGame(availableGames);
    }
}
