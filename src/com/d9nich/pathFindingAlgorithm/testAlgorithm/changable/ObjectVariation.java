package com.d9nich.pathFindingAlgorithm.testAlgorithm.changable;

import java.util.InputMismatchException;

public class ObjectVariation<T> implements Changeable<T> {
    private final T[] array;
    private int position;
    private int optimalPosition;
    private boolean isTesting;

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
        return position < array.length - 1;
    }

    @Override
    public void fromStart() {
        position = 0;
    }

    @Override
    public T get() {
        if (isTesting)
            return array[position];
        return array[optimalPosition];
    }

    @Override
    public void setOptimal() {
        optimalPosition = position;
    }

    @Override
    public void setTesting(boolean isTesting) {
        this.isTesting = isTesting;
    }

    @Override
    public int getOptimalNumber() {
        return optimalPosition;
    }
}
