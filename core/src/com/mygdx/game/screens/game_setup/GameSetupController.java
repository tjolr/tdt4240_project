package com.mygdx.game.screens.game_setup;

import com.mygdx.game.firebase.FirebaseController;
import com.mygdx.game.game_state.GlobalStateController;
import com.mygdx.game.game_state.GlobalStateModel;
import com.mygdx.game.items.GameModel;
import com.mygdx.game.items.SimpleGameModel;
import com.mygdx.game.items.PlayerUpdateModel;

import java.util.ArrayList;

public class GameSetupController {
    private static GameSetupController gameSetupControllerInstance = null;

    private GameSetupModel gameSetupModel;
    private GlobalStateController globalStateController;
    private GlobalStateModel globalStateModel;
    private FirebaseController firebaseController;

    private GameSetupController() {
        this.gameSetupModel = GameSetupModel.getInstance();
        this.globalStateController = GlobalStateController.getInstance();
        this.globalStateModel = GlobalStateModel.getInstance();
    }

    // Singleton
    public static GameSetupController getInstance() {
        if (gameSetupControllerInstance == null) {
            gameSetupControllerInstance = new GameSetupController();
        }
        return gameSetupControllerInstance;
    }

    public void initGameSetupController() {
        this.firebaseController = FirebaseController.getInstance();
        this.listenToAvailableGames();
    }


    public void hostCreateGame() {
        /* Host starts a new game. Need to update local state, and write to db about
        * the new game*/
        globalStateController.setUserAsGameHost();

        // Creating the arrays, and initializing with the first player which is the host itself.
        PlayerUpdateModel hostPlayerUpdateModel = new PlayerUpdateModel(globalStateModel.getHost(), 100, 0);

        ArrayList<PlayerUpdateModel> playerUpdateModels = new ArrayList<>();
        ArrayList<String> players = new ArrayList<>();

        String gameId = globalStateModel.getHost()+" game";
        globalStateController.setGameId(gameId);

        GameModel gameModel = new GameModel(
            gameId,
            SimpleGameModel.GameState.SETUP,
            globalStateModel.getHost(),
            players,
            playerUpdateModels
        );

        firebaseController.writeToDb("game." + gameId, gameModel);
        firebaseController.appendToArrayInDb("game."+gameId+".playerUpdateModels", hostPlayerUpdateModel, true);
        firebaseController.appendToArrayInDb("game."+gameId+".players", globalStateModel.getHost(), false);

        firebaseController.stopListenToAvailableGames();
        firebaseController.listenToPlayersInGame(gameId);
    }

    public void listenToAvailableGames() {
        this.firebaseController.listenToAvailableGames();
    }

    public void addAvailableGame(ArrayList<SimpleGameModel> availableGames) {
        gameSetupModel.setAvailableGames(availableGames);
    }

    public void playerJoinGame(SimpleGameModel simpleGameModel) {
        /* Updates local state with the host name, set gameId*/
        globalStateController.setHost(simpleGameModel.host);
        globalStateController.setGameId(simpleGameModel.gameId);

        PlayerUpdateModel playerUpdateModel = new PlayerUpdateModel(globalStateModel.getUsername(), 100, 0);

        // Every player need to add a new object to the array of player and playerUpdateModels in database.
        firebaseController.appendToArrayInDb("game."+simpleGameModel.gameId+".playerUpdateModels", playerUpdateModel, true);
        firebaseController.appendToArrayInDb("game."+simpleGameModel.gameId+".players", globalStateModel.getUsername(), false);

        // Stop listening to other available games
        firebaseController.stopListenToAvailableGames();
        // Start listen to other players in the game
        firebaseController.listenToPlayersInGame(simpleGameModel.gameId);
        // Start listen to gamestate, e.g when the host starts the game
        firebaseController.listenToGameStateInGame(simpleGameModel.gameId);
    }

}
