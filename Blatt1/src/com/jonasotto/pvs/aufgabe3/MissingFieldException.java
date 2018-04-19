package com.jonasotto.pvs.aufgabe3;

public class MissingFieldException extends Exception {
    @Override
    public String getMessage() {
        return "Field not existent.";
    }
}
