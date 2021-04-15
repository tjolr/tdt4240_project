package com.mygdx.game.screens.result;

import com.mygdx.game.firebase.FirebaseController;
import com.mygdx.game.game_state.GlobalStateController;
import com.mygdx.game.items.PlayerUpdateModel;
import com.mygdx.game.utils.comparator.ScoreComparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import static java.util.stream.Collectors.toMap;

public class ResultController {
    //Singleton
    private static ResultController resultControllerInstance = null;
    private FirebaseController firebaseController;
    private GlobalStateController globalStateController;

    private ResultController() {
        this.firebaseController = FirebaseController.getInstance();
        this.globalStateController = GlobalStateController.getInstance();
    };

    public static ResultController getInstance() {
        if (resultControllerInstance == null) {
            resultControllerInstance = new ResultController();
        }
        return resultControllerInstance;
    }

    public void disposeResultController(){
        firebaseController.stopListenToPlayerUpdateModelsInGame();
        globalStateController.clearGlobalState();
    }

    public Map<String, PlayerUpdateModel> sortPlayerUpdateModelsByScore(HashMap<String, PlayerUpdateModel> playerUpdateModels) {
        return playerUpdateModels.entrySet()
                .stream()
                .sorted(new ScoreComparator())
                .collect(toMap(Map.Entry::getKey,
                    Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

    }


}
