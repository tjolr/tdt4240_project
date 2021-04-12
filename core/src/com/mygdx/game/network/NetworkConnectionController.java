package com.mygdx.game.network;

public class NetworkConnectionController implements PlayServicesListener {
    private PlayServices playServices;


    public NetworkConnectionController(PlayServices playServices){
        this.playServices = playServices;
    }
    public void hostGame(){
        playServices.startAdvertising(this);
    }
    public void findGames(){
        playServices.startDiscovery();
    }
    public void requestConnection(){

    }

    @Override
    public void onEndpointFound() {

    }

    @Override
    public void onEndpointLost(String endpointId) {

    }
}
