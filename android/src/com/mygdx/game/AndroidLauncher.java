package com.mygdx.game;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.nearby.Nearby;
import com.google.android.gms.nearby.connection.AdvertisingOptions;
import com.google.android.gms.nearby.connection.ConnectionInfo;
import com.google.android.gms.nearby.connection.ConnectionLifecycleCallback;
import com.google.android.gms.nearby.connection.ConnectionResolution;
import com.google.android.gms.nearby.connection.ConnectionsClient;
import com.google.android.gms.nearby.connection.ConnectionsStatusCodes;
import com.google.android.gms.nearby.connection.DiscoveredEndpointInfo;
import com.google.android.gms.nearby.connection.DiscoveryOptions;
import com.google.android.gms.nearby.connection.EndpointDiscoveryCallback;
import com.google.android.gms.nearby.connection.Payload;
import com.google.android.gms.nearby.connection.PayloadCallback;
import com.google.android.gms.nearby.connection.PayloadTransferUpdate;
import com.google.android.gms.nearby.connection.Strategy;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.mygdx.game.network.PlayServices;
import com.mygdx.game.network.PlayServicesListener;

public class AndroidLauncher extends AndroidApplication implements PlayServices {


	private ConnectionsClient connectionsClient;
	private static final String SERVICE_ID ="com.mygdx.game";
	private static final Strategy STRATEGY = Strategy.P2P_STAR;
	private final EndpointDiscoveryCallback endpointDiscoveryCallback = new EndpointDiscoveryCallback(){

		@Override
		public void onEndpointFound(@NonNull String endpointId, @NonNull DiscoveredEndpointInfo discoveredEndpointInfo) {
			// An endpoint was found. We request a connection to it.
			connectionsClient
					.requestConnection(getUserNickname(), endpointId, connectionLifecycleCallback)
					.addOnSuccessListener(
							new OnSuccessListener<Void>() {
								@Override
								public void onSuccess(Void aVoid) {
									//TODO
									// We successfully requested a connection. Now both sides
									// must accept before the connection is established.
								}
							})
					.addOnFailureListener(
							new OnFailureListener() {
								@Override
								public void onFailure(@NonNull Exception e) {
									//TODO
									// Nearby Connections failed to request the connection.
								}
							});
		}

		@Override
		public void onEndpointLost(@NonNull String s) {
			//TODO
			// A previously discovered endpoint has gone away.
		}
	} ;
	private final PayloadCallback payloadCallback = new PayloadCallback() {
		@Override
		public void onPayloadReceived(@NonNull String s, @NonNull Payload payload) {
		//TODO
		}

		@Override
		public void onPayloadTransferUpdate(@NonNull String s, @NonNull PayloadTransferUpdate payloadTransferUpdate) {
			//TODO
		}
	};
	private final ConnectionLifecycleCallback connectionLifecycleCallback =
			new ConnectionLifecycleCallback() {
				@Override
				public void onConnectionInitiated(String endpointId, ConnectionInfo connectionInfo) {
					// Automatically accept the connection on both sides.
					connectionsClient.acceptConnection(endpointId, payloadCallback);
				}

				@Override
				public void onConnectionResult(String endpointId, ConnectionResolution result) {
					switch (result.getStatus().getStatusCode()) {
						case ConnectionsStatusCodes.STATUS_OK:
							// We're connected! Can now start sending and receiving data.
							//TODO
							break;
						case ConnectionsStatusCodes.STATUS_CONNECTION_REJECTED:
							// The connection was rejected by one or both sides.
							//TODO
							break;
						case ConnectionsStatusCodes.STATUS_ERROR:
							// The connection broke before it was able to be accepted.
							//TODO
							break;
						default:
							// Unknown status code
							//TODO
					}
				}

				@Override
				public void onDisconnected(String endpointId) {
					// We've been disconnected from this endpoint. No more data can be
					// sent or received.
					//TODO
				}
			};


	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new MyGdxGame(), config);
		connectionsClient = Nearby.getConnectionsClient(this);

	}


	@Override
	public void startAdvertising(PlayServicesListener listener) {
		AdvertisingOptions advertisingOptions =
				new AdvertisingOptions.Builder().setStrategy(STRATEGY).build();
		connectionsClient
				.startAdvertising(
						getUserNickname(), SERVICE_ID, connectionLifecycleCallback, advertisingOptions)
				.addOnSuccessListener(new OnSuccessListener<Void>() {
					@Override
					public void onSuccess(Void aVoid) {
						//TODO: onSuccess
						// We're advertising!
					}
				})
				.addOnFailureListener(
						new OnFailureListener() {
							@Override
							public void onFailure(@NonNull Exception e) {
								//TODO: onFailure
								// We were unable to start advertising.

							}
						});
	}

	@Override
	public void startDiscovery() {

		DiscoveryOptions discoveryOptions =
				new DiscoveryOptions.Builder().setStrategy(STRATEGY).build();
		connectionsClient
				.startDiscovery(SERVICE_ID, endpointDiscoveryCallback, discoveryOptions)
				.addOnSuccessListener(
						new OnSuccessListener<Void>(){

							@Override
							public void onSuccess(Void aVoid) {
								//TODO
								// We're discovering!

							}
						})
				.addOnFailureListener(new OnFailureListener() {
					@Override
					public void onFailure(@NonNull Exception e) {
						//TODO
						// We're unable to start discovering.

					}
				});
	}


	@Override
	public void stopAdvertising() {

	}

	@Override
	public void stopDiscovery() {

	}
	public Strategy getStrategy(){
		return Strategy.P2P_STAR;
	}

	@Override
	public String getUserNickname() {
		return null;
	}
}
