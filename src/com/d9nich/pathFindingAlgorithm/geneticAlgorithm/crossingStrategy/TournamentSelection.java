package com.d9nich.pathFindingAlgorithm.geneticAlgorithm.crossingStrategy;

import com.d9nich.pathFindingAlgorithm.geneticAlgorithm.Liveable;

import java.util.Collections;

public class TournamentSelection<T extends Liveable> extends CrossingStrategy<T> {
    @Override
    public void chooseParents(int numberOfParents) {
        if (numberOfParents > animals.size())
            throw new IllegalArgumentException("numberOfParents can not be bigger than number of animals");
        Collections.sort(animals);
        parents.clear();
        for (int i = animals.size() - 1; i >= animals.size() - numberOfParents; i--) {
            parents.add(animals.get(i));
        }
    }
}
