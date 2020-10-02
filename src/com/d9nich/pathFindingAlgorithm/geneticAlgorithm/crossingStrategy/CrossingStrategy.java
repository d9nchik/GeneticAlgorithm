package com.d9nich.pathFindingAlgorithm.geneticAlgorithm.crossingStrategy;

import com.d9nich.pathFindingAlgorithm.geneticAlgorithm.Liveable;

import java.util.ArrayList;

public abstract class CrossingStrategy<T extends Liveable> {
    protected ArrayList<T> animals;
    protected T[] parents;

    public void setAnimals(ArrayList<T> animals) {
        this.animals = animals;
    }

    public abstract void chooseParents(int numberOfParents);

    public T[] getParents() {
        return parents;
    }
}
