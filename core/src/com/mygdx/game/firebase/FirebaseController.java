package com.mygdx.game.firebase;

public class FirebaseController implements FirebaseInterface {
    private static FirebaseController firebaseControllerInstance = null;
    private FirebaseInterface firebaseInterface;

    private FirebaseController() {}

    public static FirebaseController getInstance() {
        if (firebaseControllerInstance == null) {
            firebaseControllerInstance = new FirebaseController();
        }
        return firebaseControllerInstance;
    }

    public void setFirebaseInterface(FirebaseInterface firebaseInterface) {
        this.firebaseInterface = firebaseInterface;
    }

    @Override
    public void writeToDb(String target, String value) {
        firebaseInterface.writeToDb(target, value);
    }
}
