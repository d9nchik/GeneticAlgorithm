package com.d9nich.pathFindingAlgorithm.geneticAlgorithm.crossingStrategy;

import com.d9nich.pathFindingAlgorithm.geneticAlgorithm.Liveable;

import java.util.InputMismatchException;

public class TournamentSelection<T extends Liveable> extends Crossable<T> {
    @Override
    public void choosePair() {
        if (animals.size() < 2)
            throw new InputMismatchException("World should contain minimum two animals");
        firsParent = animals.get(0);
        for (int i = 1; i < animals.size() / 2; i++) {
            T tempParent = animals.get(i);
            if (firsParent.compareTo(tempParent) < 0)
                firsParent = tempParent;
        }
        secondParent = animals.get(animals.size() / 2);
        for (int i = animals.size() / 2 + 1; i < animals.size(); i++) {
            T tempParent = animals.get(i);
            if (secondParent.compareTo(tempParent) < 0)
                secondParent = tempParent;
        }
    }
}
