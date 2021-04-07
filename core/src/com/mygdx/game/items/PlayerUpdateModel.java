package com.mygdx.game.items;

public class PlayerUpdateModel {
    // Firebase needs properties to be public to work
    public String player;
    public float health;
    public Integer score;

    public PlayerUpdateModel(String player, float health, Integer score) {
        this.player = player;
        this.health = health;
        this.score = score;
    }
}
