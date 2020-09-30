package com.d9nich;

import java.util.Random;

public class MatrixDistanceGenerator {
    public static final int INFINITY = Integer.MAX_VALUE / 2;
    public static final int MAX = 50;
    public static final int MIN = 5;

    private MatrixDistanceGenerator() {
    }

    /**
     * Generates matrix of distance, where from self to self is INFINITY value and other in range from MIN(inclusive)
     * to MAX(inclusive)
     *
     * @param length describes size of square matrix
     * @return matrix of distance
     */
    public static int[][] generate(int length) {
        int[][] distanceMatrix = new int[length][length];
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (i == j) {
                    distanceMatrix[i][j] = INFINITY;
                } else {
                    distanceMatrix[i][j] = random.nextInt(MAX - MIN + 1) + MIN;
                }
            }
        }

        return distanceMatrix;
    }
}
