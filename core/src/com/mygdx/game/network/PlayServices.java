package com.mygdx.game.network;

public interface PlayServices {

    void startAdvertising(PlayServicesListener listener);
    void startDiscovery();

    void stopAdvertising();
    void stopDiscovery();
    String getUserNickname();
}
