package com.d9nich.pathFindingAlgorithm.geneticAlgorithm.selectionStrategy;

import com.d9nich.pathFindingAlgorithm.geneticAlgorithm.Liveable;

import java.util.Collections;

public class InversedSelection<T extends Liveable> extends SelectionStrategy<T> {
    @Override
    public void choosePair() {
        firsParent = Collections.min(animals);
        secondParent = Collections.max(animals);
    }
}
