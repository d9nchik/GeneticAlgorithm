package com.d9nich.pathFindingAlgorithm.testAlgorithm;

import java.util.InputMismatchException;

public class ObjectVariation<T> implements Changeable<T> {
    private final T[] array;
    private int position;

    public ObjectVariation(T[] array) {
        this.array = array;
        position = 0;
    }

    @Override
    public void change() {
        position++;
        if (position >= array.length)
            throw new InputMismatchException("Element not found");
    }

    @Override
    public boolean hasNext() {
        return position < array.length;
    }

    @Override
    public void fromStart() {
        position = 0;
    }

    @Override
    public T get() {
        return array[position];
    }
}
