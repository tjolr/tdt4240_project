package com.mygdx.game.firebase;

public interface FirebaseInterface {
    public void writeToDb(String target, Object value);
    public void appendToArrayInDb(String target, Object value);
    public void listenToAvailableGames();
    public void stopListenToAvailableGames();
    public void listenToPlayersInGame(String gameId);
    public void stopListenToPlayersInGame();
    public void listenToGameStateInGame(String gameId);
    public void stopListenToGameStateInGame();
}
