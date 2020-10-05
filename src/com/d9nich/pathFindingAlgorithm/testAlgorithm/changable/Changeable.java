package com.d9nich.pathFindingAlgorithm.testAlgorithm.changable;

public interface Changeable<T> {
    void change();

    boolean hasNext();

    void fromStart();

    T get();
}
