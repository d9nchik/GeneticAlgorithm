package com.d9nich.pathFindingAlgorithm.geneticAlgorithm.crossingStrategy;

import com.d9nich.pathFindingAlgorithm.geneticAlgorithm.Liveable;

import java.util.ArrayList;

public interface Crossable<T extends Liveable> {
    void setAnimals(ArrayList<T> animals);

    void choosePair();

    T getFirstParent();

    T getSecondParent();
}
