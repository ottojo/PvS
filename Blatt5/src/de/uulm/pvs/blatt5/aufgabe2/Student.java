package de.uulm.pvs.blatt5.aufgabe2;

import java.util.Observable;
import java.util.Observer;

@SuppressWarnings("deprecation")
public class Student implements Observer {
    public String name;
    private BlackBoard blackBoard;

    public Student(String name, BlackBoard blackBoard) {
        this.name = name;
        this.blackBoard = blackBoard;
        blackBoard.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof BlackBoard && arg instanceof String)
            System.out.println("Student " + name + " received: " + arg);
    }

    public void addMessageToBlackBoard(String message) {
        blackBoard.addMessage(message);
    }
}
