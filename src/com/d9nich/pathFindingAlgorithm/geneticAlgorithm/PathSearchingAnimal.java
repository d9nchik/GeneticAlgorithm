package com.d9nich.pathFindingAlgorithm.geneticAlgorithm;

import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class PathSearchingAnimal implements Liveable {

    private final int[] gene;
    private final int path;

    public PathSearchingAnimal(int[] gene, int path) {
        this.gene = gene;
        this.path = path;
    }

    public int[] getGene() {
        return gene;
    }

    public int getPath() {
        return path;
    }

    public int[] mutate() {
        int[] geneOfMutateAnimal = new int[gene.length];
        System.arraycopy(gene, 0, geneOfMutateAnimal, 0, gene.length);
        Random random = new Random();
        int firstGene = random.nextInt(gene.length);
        int secondGene;
        while (firstGene == (secondGene = random.nextInt(gene.length))) {
        }
        int temp = geneOfMutateAnimal[firstGene];
        geneOfMutateAnimal[firstGene] = geneOfMutateAnimal[secondGene];
        geneOfMutateAnimal[secondGene] = temp;
        return geneOfMutateAnimal;
    }

    //multiple crossover
    public int[] makeCrossoverGene(PathSearchingAnimal parent) {
        final int NUMBER_OF_SEGMENTS = 150;
        if (parent.gene.length != gene.length)
            throw new InputMismatchException("Parent gene is not the same size as other parent");
        Set<Integer> notUsedGene = new HashSet<>();
        IntStream.of(parent.gene).forEach(notUsedGene::add);
        int sizeOfSegment = gene.length / NUMBER_OF_SEGMENTS / 2;

        int[] geneOfChildAnimal = new int[gene.length];
        //Copying first part of gene
        int insertPosition = 0;
        for (int i = 0; i < NUMBER_OF_SEGMENTS; i++) {
            int stopPoint = insertPosition + sizeOfSegment;
            for (int j = insertPosition; j < stopPoint && j < gene.length; j++) {
                int tempGene = parent.gene[j];
                if (notUsedGene.contains(tempGene)) {
                    geneOfChildAnimal[insertPosition++] = tempGene;
                    notUsedGene.remove(tempGene);
                }
            }
            //Copying second part of genes
            stopPoint = insertPosition + sizeOfSegment;
            for (int j = insertPosition; j < stopPoint && j < gene.length; j++) {
                int tempGene = gene[j];
                if (notUsedGene.contains(tempGene)) {
                    geneOfChildAnimal[insertPosition++] = tempGene;
                    notUsedGene.remove(tempGene);
                }
            }
        }
        AtomicInteger positionInNewGene = new AtomicInteger(insertPosition);
        //Copying missing genes
        notUsedGene.forEach(e -> geneOfChildAnimal[positionInNewGene.getAndIncrement()] = e);
        return geneOfChildAnimal;
    }

    @Override
    public double fitToLive() {
        return 1.0 / path;
    }
}
