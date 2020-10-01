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
    //TODO: put in constructor
    private final int PERCENT_OF_MUTATION = 50;

    public GeneticWorld(int[][] MATRIX_OF_DISTANCE) {
        this.MATRIX_OF_DISTANCE = MATRIX_OF_DISTANCE;
        //TODO: number of animals shouldn't be const
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
            animals.add(implementGene(gene));
        }
    }

    public void iterate() {
        //Random choose of parent
        //TODO: implement pattern 'Strategy'
        Random random = new Random();
        int firstAnimal = random.nextInt(animals.size());
        int secondAnimal;
        while (firstAnimal == (secondAnimal = random.nextInt(animals.size()))) {//DRY - don't repeat yourself
        }
        final Animal childAnimal = implementGene(animals.get(firstAnimal).makeCrossoverGene(animals.get(secondAnimal)));
        animals.add(childAnimal);

        if (random.nextInt(101) < PERCENT_OF_MUTATION)
            animals.add(implementGene(childAnimal.mutate()));

        killOfAnimals();

        //Choosing best
        Animal best = Collections.min(animals);
        path = best.getGene();
        length = best.getAttemptToLive();
    }

    /**
     * Killing of not good fit to live animals
     */
    private void killOfAnimals() {
        for (int i = animals.size() - numberOfAnimals; i >= 0; i--) {
            Animal worst = Collections.max(animals);
            animals.remove(worst);
        }
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

    private Animal implementGene(int[] gene) {
        return new Animal(gene, calculatePathLength(gene));
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
