package com.d9nich.pathFindingAlgorithm.geneticAlgorithm.crossingStrategy;

import com.d9nich.pathFindingAlgorithm.geneticAlgorithm.Liveable;

import java.lang.reflect.Array;
import java.util.Collections;

public class TournamentSelection<T extends Liveable> extends CrossingStrategy<T> {
    @Override
    public void chooseParents(int numberOfParents) {
        if (numberOfParents > animals.size())
            throw new IllegalArgumentException("numberOfParents can not be bigger than number of animals");
        Collections.sort(animals);
        parents = (T[]) Array.newInstance(animals.get(0).getClass(), numberOfParents);
        for (int i = 0; i < parents.length; i++) {
            parents[i] = animals.get(animals.size() - i - 1);
        }
    }
}
