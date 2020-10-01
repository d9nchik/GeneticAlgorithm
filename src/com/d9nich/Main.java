package com.d9nich;

import com.d9nich.pathFindingAlgorithm.geneticAlgorithm.GeneticWorld;

public class Main {

    public static void main(String[] args) {
        int[][] matrix = MatrixDistanceGenerator.generate(300);
        GeneticWorld geneticWorld = new GeneticWorld(matrix);
        for (int i = 0; i < 100_000; i++) {
            if (i % 20 == 0)
                System.out.println("Length: " + geneticWorld.getLength());
            geneticWorld.iterate();
        }
    }
}
