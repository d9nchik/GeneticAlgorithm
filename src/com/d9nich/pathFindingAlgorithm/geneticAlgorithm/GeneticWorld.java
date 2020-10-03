package com.d9nich.pathFindingAlgorithm.geneticAlgorithm;

import com.d9nich.pathFindingAlgorithm.PathFindable;
import com.d9nich.pathFindingAlgorithm.geneticAlgorithm.crossoverStrategy.PartiallyMapped;
import com.d9nich.pathFindingAlgorithm.geneticAlgorithm.selectionStrategy.SelectionStrategy;
import com.d9nich.pathFindingAlgorithm.geneticAlgorithm.selectionStrategy.TournamentSelection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GeneticWorld implements PathFindable {
    private final int[][] MATRIX_OF_DISTANCE;
    private final int numberOfAnimals;
    private final ArrayList<PathSearchingAnimal> pathSearchingAnimals = new ArrayList<>();
    private int[] path;
    private int length = Integer.MAX_VALUE / 2;
    //TODO: put in constructor
    private final SelectionStrategy<PathSearchingAnimal> selectionStrategy = new TournamentSelection<>();
    private final int PERCENT_OF_MUTATION = 50;

    public GeneticWorld(int[][] MATRIX_OF_DISTANCE) {
        this.MATRIX_OF_DISTANCE = MATRIX_OF_DISTANCE;
        //TODO: number of animals shouldn't be const
        numberOfAnimals = 100;
        makePopulation();
        selectionStrategy.setAnimals(pathSearchingAnimals);
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
            pathSearchingAnimals.add(implementGene(gene));
        }
    }

    public void iterate() {
        //Choose of parent
        Random random = new Random();
        selectionStrategy.choosePair();
        PartiallyMapped partiallyMapped = new PartiallyMapped();
        partiallyMapped.setFirstFather(selectionStrategy.getFirstParent().getGene());
        partiallyMapped.setSecondFather(selectionStrategy.getSecondParent().getGene());
        partiallyMapped.crossAnimal();
        pathSearchingAnimals.add(implementGene(partiallyMapped.getFirstChild()));
        pathSearchingAnimals.add(implementGene(partiallyMapped.getSecondChild()));

        if (random.nextInt(101) < PERCENT_OF_MUTATION) {
            pathSearchingAnimals.add(implementGene(pathSearchingAnimals.get(pathSearchingAnimals.size() - 2).mutate()));
            pathSearchingAnimals.add(implementGene(pathSearchingAnimals.get(pathSearchingAnimals.size() - 2).mutate()));
        }

        killOfAnimals();

        //Choosing best
        //TODO: improve this part of code to O(1)
        PathSearchingAnimal best = Collections.max(pathSearchingAnimals);
        path = best.getGene();
        length = best.getPath();
    }

    /**
     * Killing of not good fit to live animals
     */
    private void killOfAnimals() {
        for (int i = pathSearchingAnimals.size() - numberOfAnimals; i >= 0; i--) {
            PathSearchingAnimal worst = Collections.min(pathSearchingAnimals);
            pathSearchingAnimals.remove(worst);
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

    private PathSearchingAnimal implementGene(int[] gene) {
        return new PathSearchingAnimal(gene, calculatePathLength(gene));
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
