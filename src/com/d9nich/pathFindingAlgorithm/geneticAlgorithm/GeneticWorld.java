package com.d9nich.pathFindingAlgorithm.geneticAlgorithm;

import com.d9nich.pathFindingAlgorithm.PathFindable;
import com.d9nich.pathFindingAlgorithm.geneticAlgorithm.crossingStrategy.CrossingStrategy;
import com.d9nich.pathFindingAlgorithm.geneticAlgorithm.crossingStrategy.RandomSelection;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class GeneticWorld implements PathFindable {
    private final int[][] MATRIX_OF_DISTANCE;
    private final int numberOfAnimals;
    private final ArrayList<PathSearchingAnimal> pathSearchingAnimals = new ArrayList<>();
    private int[] path;
    private int length = Integer.MAX_VALUE / 2;
    //TODO: put in constructor
    private final CrossingStrategy<PathSearchingAnimal> crossingStrategy = new RandomSelection<>();
    private final int PERCENT_OF_MUTATION = 50;

    public GeneticWorld(int[][] MATRIX_OF_DISTANCE) {
        this.MATRIX_OF_DISTANCE = MATRIX_OF_DISTANCE;
        //TODO: number of animals shouldn't be const
        numberOfAnimals = 1000;
        makePopulation();
        crossingStrategy.setAnimals(pathSearchingAnimals);
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
        crossingStrategy.chooseParents(100);
        final PathSearchingAnimal childPathSearchingAnimal = implementGene(makeCrossoverGene(
                crossingStrategy.getParents()));
        pathSearchingAnimals.add(childPathSearchingAnimal);

        if (random.nextInt(101) < PERCENT_OF_MUTATION)
            pathSearchingAnimals.add(implementGene(childPathSearchingAnimal.mutate()));

        killOfAnimals();

        //Choosing best
        //TODO: improve this part of code to O(1)
        PathSearchingAnimal best = Collections.max(pathSearchingAnimals);
        path = best.getGene();
        length = best.getPath();
    }

    private int[] makeCrossoverGene(PathSearchingAnimal[] animals) {
        int[] gene = animals[0].getGene();
        Set<Integer> notUsedGene = new HashSet<>();
        for (Integer partOfGene : gene)
            notUsedGene.add(partOfGene);
        int partOfEachAnimalInChildGene = gene.length / animals.length;
        for (int i = 0; i < partOfEachAnimalInChildGene; i++) {
            notUsedGene.remove(gene[i]);
        }
        int pointerInGene = partOfEachAnimalInChildGene;
        for (int i = 1; i < animals.length; i++) {
            int[] animalFatherGene = animals[i].getGene();
            int stopPoint = pointerInGene + partOfEachAnimalInChildGene;
            int fatherAnimalPointer = pointerInGene;
            for (; pointerInGene < stopPoint; pointerInGene++) {
                for (; fatherAnimalPointer < gene.length; fatherAnimalPointer++) {
                    int tempGene = animalFatherGene[fatherAnimalPointer];
                    if (notUsedGene.contains(tempGene)) {
                        gene[pointerInGene] = tempGene;
                        notUsedGene.remove(tempGene);
                        fatherAnimalPointer++;
                        break;
                    }
                }
            }
        }
        AtomicInteger pointer = new AtomicInteger(pointerInGene);
        //Copying missing genes
        notUsedGene.forEach(e -> gene[pointer.getAndIncrement()] = e);
        return gene;
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
