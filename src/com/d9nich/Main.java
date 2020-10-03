package com.d9nich;

import com.d9nich.pathFindingAlgorithm.GreedyAlgorithm;
import com.d9nich.pathFindingAlgorithm.geneticAlgorithm.GeneticWorld;

public class Main {

    public static void main(String[] args) {
        int[][] matrix = MatrixDistanceGenerator.generate(150);
        System.out.println(new GreedyAlgorithm(matrix).getLength());
        GeneticWorld geneticWorld = new GeneticWorld(matrix);
        for (int i = 0; i < 1_000_000; i++) {
            if (i % 1_000 == 0)
                System.out.println("Length: " + geneticWorld.getLength());
            geneticWorld.iterate();
        }
    }
}
