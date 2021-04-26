package com.mygdx.game.items;

public class PlayerUpdateModel {
    // Firebase needs properties to be public to work
    public String player;
    public int health;
    public int score;

    // Default constructor required for calls to DataSnapshot.getValue(User.class)
    public PlayerUpdateModel() {}



    public PlayerUpdateModel(String player, int health, int score) {
        this.player = player;
        this.health = health;
        this.score = score;
    }

    // Methods to prevent Android Studio warnings
    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
