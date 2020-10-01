package com.d9nich.pathFindingAlgorithm.geneticAlgorithm;

import com.d9nich.pathFindingAlgorithm.PathFindable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GeneticWorld implements PathFindable {
    private final int[][] MATRIX_OF_DISTANCE;
    private final int numberOfAnimals;
    private final ArrayList<Animal> animals = new ArrayList<>();
    private int[] path;
    private int length = Integer.MAX_VALUE / 2;

    public GeneticWorld(int[][] MATRIX_OF_DISTANCE) {
        this.MATRIX_OF_DISTANCE = MATRIX_OF_DISTANCE;
        numberOfAnimals = 100;
        makePopulation();
        iterate();
    }

    private void makePopulation() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < MATRIX_OF_DISTANCE.length; i++) {
            list.add(i);
        }
        for (int i = 0; i < numberOfAnimals; i++) {
            Collections.shuffle(list);
            int[] gene = new int[list.size()];
            for (int j = 0; j < gene.length; j++) {
                gene[j] = list.get(j);
            }
            animals.add(new Animal(gene, calculatePathLength(gene)));
        }
    }

    public void iterate() {
        Random random = new Random();
        int firstAnimal = random.nextInt(animals.size());
        int secondAnimal;
        while (firstAnimal == (secondAnimal = random.nextInt(animals.size()))) {
        }
        int[] childGenes = animals.get(firstAnimal).makeCrossoverGene(animals.get(secondAnimal));
        final Animal childAnimal = new Animal(childGenes, calculatePathLength(childGenes));
        animals.add(childAnimal);
        childGenes = childAnimal.mutate();
        animals.add(new Animal(childGenes, calculatePathLength(childGenes)));
        for (int i = animals.size() - numberOfAnimals; i >= 0; i--) {
            Animal worst = Collections.max(animals);
            animals.remove(worst);
        }

        Animal best = Collections.min(animals);
        path = best.getGene();
        length = best.getAttemptToLive();
    }

    public int calculatePathLength(int[] path) {
        int previousPoint = path[path.length - 1];
        int totalPath = 0;
        for (int j : path) {
            totalPath += MATRIX_OF_DISTANCE[previousPoint][j];
            previousPoint = j;
        }
        return totalPath;
    }

    @Override
    public int[] getPath() {
        int[] result = new int[path.length];
        System.arraycopy(path, 0, result, 0, path.length);
        return result;
    }

    @Override
    public double getLength() {
        return length;
    }
}
