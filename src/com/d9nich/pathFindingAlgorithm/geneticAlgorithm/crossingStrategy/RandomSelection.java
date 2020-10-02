package com.d9nich.pathFindingAlgorithm.geneticAlgorithm.crossingStrategy;

import com.d9nich.pathFindingAlgorithm.geneticAlgorithm.Liveable;

import java.util.Collections;

public class RandomSelection<T extends Liveable> extends CrossingStrategy<T> {

    @Override
    public void chooseParents(int numberOfParents) {
        if (numberOfParents > animals.size())
            throw new IllegalArgumentException("numberOfParents can not be bigger than number of animals");
        Collections.shuffle(animals);
        parents.clear();
        for (int i = 0; i < numberOfParents; i++) {
            parents.add(animals.get(i));
        }
    }
}
