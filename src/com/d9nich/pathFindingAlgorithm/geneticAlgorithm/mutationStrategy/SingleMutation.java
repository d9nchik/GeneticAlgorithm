package com.d9nich.pathFindingAlgorithm.geneticAlgorithm.mutationStrategy;

import java.util.Random;

public class SingleMutation implements Mutable {

    @Override
    public int[] mutate(int[] gene) {
        int[] mutated = new int[gene.length];
        System.arraycopy(gene, 0, mutated, 0, gene.length);
        Random random = new Random();
        int firstPoint = random.nextInt(gene.length);
        int secondPoint;
        while (firstPoint == (secondPoint = random.nextInt(gene.length))) {
        }
        mutated[firstPoint] = gene[secondPoint];
        mutated[secondPoint] = gene[firstPoint];
        return mutated;
    }
}
