package com.mygdx.game.items;

public class PlayerUpdateModel {
    // Firebase needs properties to be public to work
    public String player;
    public float health;
    public Integer score;

    // Default constructor required for calls to DataSnapshot.getValue(User.class)
    public PlayerUpdateModel() {}

    public PlayerUpdateModel(String player, float health, Integer score) {
        this.player = player;
        this.health = health;
        this.score = score;
    }
}
