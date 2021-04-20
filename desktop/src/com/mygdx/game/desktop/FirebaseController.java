package com.mygdx.game.desktop;


import com.mygdx.game.firebase.FirebaseInterface;

public class FirebaseController implements FirebaseInterface {
    @Override
    public void writeToDb(String target, Object value) {

    }

    @Override
    public void appendToArrayInDb(String target, Object value, boolean playerUpdateModel) {

    }

    @Override
    public void incrementValueInDb(String target, int incrementValue) {

    }

    @Override
    public void decrementValueInDb(String target, int decrementValue) {

    }

    @Override
    public void listenToAvailableGames() {

    }

    @Override
    public void stopListenToAvailableGames() {

    }

    @Override
    public void listenToPlayersInGame(String gameId) {

    }

    @Override
    public void stopListenToPlayersInGame() {

    }

    @Override
    public void listenToGameStateInGame(String gameId) {

    }

    @Override
    public void stopListenToGameStateInGame() {

    }

    @Override
    public void listenToPlayerUpdateModelsInGame(String gameId) {

    }

    @Override
    public void stopListenToPlayerUpdateModelsInGame() {

    }
}
