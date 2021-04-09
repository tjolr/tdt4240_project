package com.mygdx.game.firebase;

import com.mygdx.game.game_state.GameStateController;
import com.mygdx.game.items.SimpleGameModel;
import com.mygdx.game.screens.game_setup.GameSetupController;

import java.util.ArrayList;

public class FirebaseController implements FirebaseInterface {
    private static FirebaseController firebaseControllerInstance = null;
    private FirebaseInterface firebaseInterface;
    private GameSetupController gameSetupController;
    private GameStateController gameStateController;

    private FirebaseController() {
        gameSetupController = GameSetupController.getInstance();
        gameStateController = GameStateController.GameStateController();
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

    @Override
    public void stopListenToAvailableGames() {
        firebaseInterface.stopListenToAvailableGames();
    }

    @Override
    public void listenToPlayersInGame(String gameId) {
        firebaseInterface.listenToPlayersInGame(gameId);
    }

    @Override
    public void stopListenToPlayersInGame() {

    }


    public void setAvailableGames(ArrayList<SimpleGameModel> availableGames) {
        gameSetupController.addAvailableGame(availableGames);
    }

    public void addPlayer(String player) {
        gameStateController.addPlayer(player);
    }
}
