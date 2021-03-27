package com.mygdx.game.network;

public interface PlayServicesListener {
    void onEndpointFound();
    void onEndpointLost(String endpointId);
}
