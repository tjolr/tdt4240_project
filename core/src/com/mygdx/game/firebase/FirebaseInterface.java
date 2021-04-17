package com.mygdx.game.firebase;

public interface FirebaseInterface {
    public void writeToDb(String target, Object value);
    public void appendToArrayInDb(String target, Object value, boolean playerUpdateModel);
    public void incrementValueInDb(String target, int incrementValue);
    public void decrementValueInDb(String target, int decrementValue);
    public void listenToAvailableGames();
    public void stopListenToAvailableGames();
    public void listenToPlayersInGame(String gameId);
    public void stopListenToPlayersInGame();
    public void listenToGameStateInGame(String gameId);
    public void stopListenToGameStateInGame();
    public void listenToPlayerUpdateModelsInGame(String gameId);
    public void stopListenToPlayerUpdateModelsInGame();
}
