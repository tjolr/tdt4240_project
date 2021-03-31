package com.mygdx.game.firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseService implements FirebaseInterface {
    FirebaseDatabase db;
    DatabaseReference myRef;

    public FirebaseService() {
        this.db = FirebaseDatabase.getInstance("https://progark-game-default-rtdb.europe-west1.firebasedatabase.app/");
    }

    @Override
    public void writeToDb(String target, String value) {
        System.out.println("Writing " + value + " to target " + target);
        myRef = db.getReference(target);
        myRef.setValue(value);
    }
}
