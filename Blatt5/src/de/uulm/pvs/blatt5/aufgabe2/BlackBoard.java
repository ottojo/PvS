package de.uulm.pvs.blatt5.aufgabe2;

import java.util.Observable;

@SuppressWarnings("deprecation")
public class BlackBoard extends Observable {
    private void changeMessage(String message) {
        setChanged();
        notifyObservers(message);
    }

    public void addMessage(String message) {
        changeMessage(message);
    }
}
