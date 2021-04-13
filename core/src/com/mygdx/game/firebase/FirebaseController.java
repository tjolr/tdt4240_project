package com.mygdx.game.firebase;

import com.mygdx.game.game_state.GlobalStateController;
import com.mygdx.game.items.PlayerUpdateModel;
import com.mygdx.game.items.SimpleGameModel;
import com.mygdx.game.screens.game_setup.GameSetupController;

import java.util.ArrayList;

public class FirebaseController implements FirebaseInterface {
    private static FirebaseController firebaseControllerInstance = null;
    private FirebaseInterface firebaseInterface;
    private GameSetupController gameSetupController;
    private GlobalStateController globalStateController;

    private FirebaseController() {
        gameSetupController = GameSetupController.getInstance();
        globalStateController = GlobalStateController.getInstance();
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
        firebaseInterface.stopListenToPlayersInGame();
    }

    @Override
    public void listenToGameStateInGame(String gameId) {
        firebaseInterface.listenToGameStateInGame(gameId);
    }

    @Override
    public void stopListenToGameStateInGame() {
        firebaseInterface.stopListenToGameStateInGame();
    }

    @Override
    public void listenToPlayerUpdateModelsInGame(String gameId) {
        firebaseInterface.listenToPlayerUpdateModelsInGame(gameId);
    }

    @Override
    public void stopListenToPlayerUpdateModelsInGame() {
        firebaseInterface.stopListenToPlayerUpdateModelsInGame();
    }

    public void setGameStateInGame(SimpleGameModel.GameState gameState) {
        globalStateController.setGameState(gameState);
    }

    public void setAvailableGames(ArrayList<SimpleGameModel> availableGames) {
        gameSetupController.addAvailableGame(availableGames);
    }

    public void addPlayer(String player) {
        globalStateController.addPlayer(player);
    }

    public void setPlayerUpdateModel(PlayerUpdateModel playerUpdateModel) {
        globalStateController.setPlayerUpdateModel(playerUpdateModel);
    }
}
