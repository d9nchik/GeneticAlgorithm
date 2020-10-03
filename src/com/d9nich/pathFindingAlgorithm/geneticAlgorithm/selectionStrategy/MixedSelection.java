package com.d9nich.pathFindingAlgorithm.geneticAlgorithm.selectionStrategy;

import com.d9nich.pathFindingAlgorithm.geneticAlgorithm.Liveable;

import java.util.Collections;
import java.util.Random;

/**
 * Selection of the best parent and one random
 *
 * @param <T>
 */
public class MixedSelection<T extends Liveable> extends SelectionStrategy<T> {

    @Override
    public void choosePair() {
        Random random = new Random();
        firsParent = Collections.max(animals);
        while (firsParent != (secondParent = animals.get(random.nextInt(animals.size())))) {//DRY - don't repeat yourself
        }
    }
}
