package com.d9nich;

import com.d9nich.pathFindingAlgorithm.GreedyAlgorithm;
import com.d9nich.pathFindingAlgorithm.geneticAlgorithm.GeneticWorld;
import com.d9nich.pathFindingAlgorithm.geneticAlgorithm.crossoverStrategy.PartiallyMapped;
import com.d9nich.pathFindingAlgorithm.geneticAlgorithm.localStrategy.OnePointLocal;
import com.d9nich.pathFindingAlgorithm.geneticAlgorithm.mutationStrategy.SingleMutation;
import com.d9nich.pathFindingAlgorithm.geneticAlgorithm.selectionStrategy.RandomSelection;

public class Main {

    public static void main(String[] args) {
        int[][] matrix = MatrixDistanceGenerator.generate(300);
        System.out.println(new GreedyAlgorithm(matrix).getLength());
        GeneticWorld geneticWorld = new GeneticWorld(matrix, 50, new RandomSelection<>(), new PartiallyMapped(),
                new SingleMutation(), new OnePointLocal(1.0 / 100_000_000), 300);
        for (int i = 0; i < 1_000_000; i++) {
            if (i % 1_000 == 0)
                System.out.println("Length: " + geneticWorld.getLength());
            geneticWorld.iterate();
        }
    }
}
