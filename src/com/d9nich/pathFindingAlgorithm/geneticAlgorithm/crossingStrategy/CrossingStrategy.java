package com.d9nich.pathFindingAlgorithm.geneticAlgorithm.crossingStrategy;

import com.d9nich.pathFindingAlgorithm.geneticAlgorithm.Liveable;

import java.util.ArrayList;

public abstract class CrossingStrategy<T extends Liveable> {
    protected ArrayList<T> animals;
    protected ArrayList<T> parents = new ArrayList<>();

    public void setAnimals(ArrayList<T> animals) {
        this.animals = animals;
    }

    public abstract void chooseParents(int numberOfParents);

    public T[] getParents() {
        return (T[]) parents.toArray();
    }
}
