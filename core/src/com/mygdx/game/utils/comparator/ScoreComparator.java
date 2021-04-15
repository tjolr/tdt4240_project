package com.mygdx.game.utils.comparator;

import com.mygdx.game.items.PlayerUpdateModel;

import java.util.Comparator;
import java.util.Map;

public class ScoreComparator implements Comparator<Map.Entry<String, PlayerUpdateModel>> {

    @Override
    public int compare(Map.Entry<String, PlayerUpdateModel> o1, Map.Entry<String, PlayerUpdateModel> o2) {
        return o2.getValue().score - o1.getValue().score;
    }
}
