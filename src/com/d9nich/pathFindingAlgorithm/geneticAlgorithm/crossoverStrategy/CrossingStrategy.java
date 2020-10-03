package com.d9nich.pathFindingAlgorithm.geneticAlgorithm.crossoverStrategy;

public abstract class CrossingStrategy {
    protected int[] firstFather;
    protected int[] secondFather;

    protected int[] firstChild;
    protected int[] secondChild;


    public void setFirstFather(int[] firstFather) {
        this.firstFather = firstFather;
    }

    public void setSecondFather(int[] secondFather) {
        this.secondFather = secondFather;
    }

    public int[] getFirstChild() {
        return firstChild;
    }

    public int[] getSecondChild() {
        return secondChild;
    }

    public abstract void crossAnimal();
}
