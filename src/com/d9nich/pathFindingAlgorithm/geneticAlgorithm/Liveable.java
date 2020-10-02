package com.d9nich.pathFindingAlgorithm.geneticAlgorithm;

public interface Liveable extends Comparable<Liveable> {
    double fitToLive();

    @Override
    default int compareTo(Liveable o) {
        return Double.compare(fitToLive(), o.fitToLive());
    }
}
