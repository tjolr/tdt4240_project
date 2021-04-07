package com.mygdx.game.firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseService implements FirebaseInterface {
    FirebaseDatabase db;
    DatabaseReference ref;

    public FirebaseService() {
        this.db = FirebaseDatabase.getInstance("https://progark-game-default-rtdb.europe-west1.firebasedatabase.app/");
        this.ref = db.getReference();
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

}
