package com.d9nich.pathFindingAlgorithm.geneticAlgorithm.selectionStrategy;

import com.d9nich.pathFindingAlgorithm.geneticAlgorithm.Liveable;

import java.util.Random;

public class RandomSelection<T extends Liveable> extends SelectionStrategy<T> {

    @Override
    public void choosePair() {
        Random random = new Random();
        int firstAnimal = random.nextInt(animals.size());
        firsParent = animals.get(firstAnimal);
        int secondAnimal;
        while (firstAnimal == (secondAnimal = random.nextInt(animals.size()))) {//DRY - don't repeat yourself
        }
        secondParent = animals.get(secondAnimal);
    }
}
