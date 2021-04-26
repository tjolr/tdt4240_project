package com.mygdx.game.firebase;

public interface FirebaseInterface {
    // Using LibGDX method for interfacing with platform specific code as described
    // here: https://github.com/libgdx/libgdx/wiki/Interfacing-with-platform-specific-code

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
