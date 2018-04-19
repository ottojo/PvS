package com.jonasotto.pvs.aufgabe3;

import java.util.Arrays;

public class Field {
    private int[] field;

    public Field(int length) {
        this.field = new int[length];
    }

    public void setFieldNumber(int position, int value) throws MissingFieldException {
        if (position < field.length)
            field[position] = value;
        else throw new MissingFieldException();
    }

    public int getFieldNumber(int position) throws MissingFieldException {
        if (position < field.length)
            return field[position];
        else throw new MissingFieldException();
    }

    @Override
    public String toString() {
        return Arrays.toString(field);
    }
}
