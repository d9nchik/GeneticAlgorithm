package com.d9nich.pathFindingAlgorithm.geneticAlgorithm.crossoverStrategy;

import com.d9nich.pathFindingAlgorithm.geneticAlgorithm.PathSearchingAnimal;

public abstract class CrossingStrategy {
    private PathSearchingAnimal first;
    private PathSearchingAnimal second;

    private int[] firstChild;
    private int[] secondChild;


    public void setFirst(PathSearchingAnimal first) {
        this.first = first;
    }

    public void setSecond(PathSearchingAnimal second) {
        this.second = second;
    }

    public int[] getFirstChild() {
        return firstChild;
    }

    public int[] getSecondChild() {
        return secondChild;
    }

    public abstract void crossAnimal();
}
