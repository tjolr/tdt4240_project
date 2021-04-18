package com.mygdx.game.screens.room;

import com.mygdx.game.firebase.FirebaseController;
import com.mygdx.game.game_state.GlobalStateController;
import com.mygdx.game.game_state.GlobalStateModel;
import com.mygdx.game.items.SimpleGameModel;

public class RoomController {
    private FirebaseController firebaseController;
    private GlobalStateModel globalStateModel;
    private GlobalStateController globalStateController;

    public RoomController() {
        this.firebaseController = FirebaseController.getInstance();
        this.globalStateModel = GlobalStateModel.getInstance();
        this.globalStateController = GlobalStateController.getInstance();
    }

    public void setGameStateActive() {
        globalStateController.setGameState(SimpleGameModel.GameState.ACTIVE);
        firebaseController.writeToDb("game."+ globalStateModel.getGameId()+".gameState", SimpleGameModel.GameState.ACTIVE);
    }

    public void disposeRoomListeners(){
        firebaseController.stopListenToPlayersInGame();
        firebaseController.stopListenToGameStateInGame();
    }
}
