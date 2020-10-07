package com.d9nich.pathFindingAlgorithm.geneticAlgorithm;

import com.d9nich.pathFindingAlgorithm.PathFindable;
import com.d9nich.pathFindingAlgorithm.geneticAlgorithm.crossoverStrategy.CrossingStrategy;
import com.d9nich.pathFindingAlgorithm.geneticAlgorithm.localStrategy.LocalImprovable;
import com.d9nich.pathFindingAlgorithm.geneticAlgorithm.mutationStrategy.Mutable;
import com.d9nich.pathFindingAlgorithm.geneticAlgorithm.selectionStrategy.SelectionStrategy;

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
    private final SelectionStrategy<PathSearchingAnimal> selectionStrategy;
    private final CrossingStrategy crossingStrategy;
    private final Mutable mutable;
    private final LocalImprovable localImprovable;
    private final int PERCENT_OF_MUTATION;

    public GeneticWorld(int[][] MATRIX_OF_DISTANCE, int PERCENT_OF_MUTATION,
                        SelectionStrategy<PathSearchingAnimal> selectionStrategy, CrossingStrategy crossingStrategy,
                        Mutable mutable, LocalImprovable localImprovable, int numberOfAnimals) {
        this.MATRIX_OF_DISTANCE = MATRIX_OF_DISTANCE;
        this.PERCENT_OF_MUTATION = PERCENT_OF_MUTATION;
        this.selectionStrategy = selectionStrategy;
        this.crossingStrategy = crossingStrategy;
        this.mutable = mutable;
        this.localImprovable = localImprovable;
        this.numberOfAnimals = numberOfAnimals;
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
            pathSearchingAnimals.add(implementGene(gene));
        }
    }

    public void iterate() {
        //Choose of parent
        final ArrayList<PathSearchingAnimal> parents = (ArrayList<PathSearchingAnimal>) pathSearchingAnimals.clone();
        selectionStrategy.setAnimals(parents);
//        for (int i = 0; i < parents.size() / 2; i++) {
            Random random = new Random();
            selectionStrategy.choosePair();
            parents.remove(selectionStrategy.getFirstParent());
            parents.remove(selectionStrategy.getSecondParent());

            crossingStrategy.setFirstFather(selectionStrategy.getFirstParent().getGene());
            crossingStrategy.setSecondFather(selectionStrategy.getSecondParent().getGene());
            crossingStrategy.crossAnimal();
            pathSearchingAnimals.add(implementGene(crossingStrategy.getFirstChild()));
            pathSearchingAnimals.add(implementGene(crossingStrategy.getSecondChild()));

            if (random.nextInt(101) < PERCENT_OF_MUTATION) {
                pathSearchingAnimals.add(implementGene(mutable.mutate(crossingStrategy.getFirstChild())));
                pathSearchingAnimals.add(implementGene(mutable.mutate(crossingStrategy.getSecondChild())));
            }
//        }


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
        for (int i = pathSearchingAnimals.size() - numberOfAnimals; i > 0; i--) {
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
        final PathSearchingAnimal pathSearchingAnimal = new PathSearchingAnimal(gene, calculatePathLength(gene));
        localImprovable.improve(pathSearchingAnimals, pathSearchingAnimal.fitToLive());
        return pathSearchingAnimal;
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
