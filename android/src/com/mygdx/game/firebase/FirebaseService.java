package com.mygdx.game.firebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;
import com.mygdx.game.items.PlayerUpdateModel;
import com.mygdx.game.items.SimpleGameModel;

import java.util.ArrayList;

public class FirebaseService implements FirebaseInterface {

    FirebaseDatabase db;
    DatabaseReference ref;
    FirebaseController firebaseController;

    String gameId;

    ValueEventListener availableGamesListener;
    DatabaseReference availableGamesRef;

    ChildEventListener playersInGameListener;
    DatabaseReference playersInGameRef;

    ValueEventListener gameStateInGameListener;
    DatabaseReference gameStateInGameRef;

    ChildEventListener playerUpdateModelListeneer;
    DatabaseReference playerUpdateModelRef;


    public FirebaseService() {
        this.db = FirebaseDatabase.getInstance("https://progark-game-default-rtdb.europe-west1.firebasedatabase.app/");
        this.ref = db.getReference();
        this.firebaseController = FirebaseController.getInstance();
    }

    // Parameter fullTarget is a string on format "parent.child.grandchild"
    // returns a reference to the deepest child in fullTarget
    private DatabaseReference getRefFromFullTarget(String fullTarget){
        String[] targets = fullTarget.split("\\.");

        ref = db.getReference();
        for (String target: targets) {
            ref = ref.child(target);
        }
        return ref;
    }

    @Override
    public void writeToDb(String fullTarget, Object value) {
        ref = getRefFromFullTarget(fullTarget);
        ref.setValue(value);
    }

    @Override
    public void appendToArrayInDb(String target, Object value, boolean playerUpdateModel) {
        ref = getRefFromFullTarget(target).push();
        ref.setValue(value);

        if (playerUpdateModel) {
            String itemKey = ref.getKey();
            firebaseController.setPlayerUpdateModelId(itemKey);
        }
    }

    @Override
    public void incrementValueInDb(String target, int incrementValue) {
        ref = getRefFromFullTarget(target);
        ref.runTransaction(new Transaction.Handler() {
            @NonNull
            @Override
            public Transaction.Result doTransaction(@NonNull MutableData currentData) {
                if (currentData.getValue() == null) {
                    currentData.setValue(0);
                } else {
                    // Using a Transaction to increment the server value
                    currentData.setValue((Long) currentData.getValue() + incrementValue);
                }
                return Transaction.success(currentData);
            }

            @Override
            public void onComplete(@Nullable DatabaseError error, boolean committed, @Nullable DataSnapshot currentData) {
                if (error != null) {
                    System.out.println("Firebase increment failed.");
                }
            }
        });
    }

    @Override
    public void decrementValueInDb(String target, int decrementValue) {
        ref = getRefFromFullTarget(target);
        ref.runTransaction(new Transaction.Handler() {
            @NonNull
            @Override
            public Transaction.Result doTransaction(@NonNull MutableData currentData) {
                if (currentData.getValue() == null) {
                    currentData.setValue(0);
                } else if (currentData.getValue(int.class) <= 0) {
                    currentData.setValue(0);
                } else {
                    currentData.setValue((Long) currentData.getValue() - decrementValue);
                }
                return Transaction.success(currentData);
            }

            @Override
            public void onComplete(@Nullable DatabaseError error, boolean committed, @Nullable DataSnapshot currentData) {
                if (error != null) {
                    System.out.println("Firebase decrement failed.");
                }
            }
        });
    }

    @Override
    public void listenToAvailableGames() {
        availableGamesRef = db.getReference().child("game");

        availableGamesListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<SimpleGameModel> availableGames = new ArrayList<>();
                for (DataSnapshot child: snapshot.getChildren()) {
                    SimpleGameModel simpleGameModel = child.getValue(SimpleGameModel.class);

                    if (simpleGameModel.gameState == SimpleGameModel.GameState.SETUP){
                        availableGames.add(simpleGameModel);
                    }
                }
                firebaseController.setAvailableGames(availableGames);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println(error);
            }
        };
        availableGamesRef.addValueEventListener(availableGamesListener);
    }

    @Override
    public void stopListenToAvailableGames() {
        availableGamesRef.removeEventListener(availableGamesListener);
    }

    @Override
    public void listenToPlayersInGame(String gameId) {
        this.gameId = gameId;
        playersInGameRef = db.getReference().child("game").child(gameId).child("players");

        playersInGameListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String player = snapshot.getValue(String.class);
                firebaseController.addPlayer(player);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        playersInGameRef.addChildEventListener(playersInGameListener);
    }

    @Override
    public void stopListenToPlayersInGame() {
        playersInGameRef.removeEventListener(playersInGameListener);
    }

    @Override
    public void listenToGameStateInGame(String gameId) {
        this.gameId = gameId;
        gameStateInGameRef = db.getReference().child("game").child(gameId).child("gameState");

        gameStateInGameListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                firebaseController.setGameStateInGame(snapshot.getValue(SimpleGameModel.GameState.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        gameStateInGameRef.addValueEventListener(gameStateInGameListener);

    }

    @Override
    public void stopListenToGameStateInGame() {
        if (gameStateInGameRef != null && gameStateInGameListener != null){
            gameStateInGameRef.removeEventListener(gameStateInGameListener);
        }
    }

    @Override
    public void listenToPlayerUpdateModelsInGame(String gameId) {
        this.gameId = gameId;

        playerUpdateModelRef = db.getReference().child("game").child(gameId).child("playerUpdateModels");

        playerUpdateModelListeneer = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                PlayerUpdateModel playerUpdateModel = snapshot.getValue(PlayerUpdateModel.class);
                firebaseController.setPlayerUpdateModel(playerUpdateModel);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                PlayerUpdateModel playerUpdateModel = snapshot.getValue(PlayerUpdateModel.class);
                firebaseController.setPlayerUpdateModel(playerUpdateModel);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        playerUpdateModelRef.addChildEventListener(playerUpdateModelListeneer);
    }

    @Override
    public void stopListenToPlayerUpdateModelsInGame() {
        playerUpdateModelRef.removeEventListener(playerUpdateModelListeneer);
    }
}
