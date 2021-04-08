package com.mygdx.game.firebase;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mygdx.game.items.SimpleGameModel;

import java.util.ArrayList;

public class FirebaseService implements FirebaseInterface {
    FirebaseDatabase db;
    DatabaseReference ref;
    FirebaseController firebaseController;

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
    public void appendToArrayInDb(String target, Object value) {
        ref = getRefFromFullTarget(target);
        ref.push().setValue(value);
    }

    @Override
    public void listenToAvailableGames() {
        ref = db.getReference();
        ref.child("game").addValueEventListener(new ValueEventListener() {

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
        });
    }




}
