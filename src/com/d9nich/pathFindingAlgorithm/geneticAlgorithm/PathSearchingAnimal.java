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

    public int[] makeCrossoverGene(PathSearchingAnimal parent) {
        if (parent.gene.length != gene.length)
            throw new InputMismatchException("Parent gene is not the same size as other parent");
        Set<Integer> notUsedGene = new HashSet<>();
        IntStream.of(parent.gene).forEach(notUsedGene::add);
        int pointOfSplit = new Random().nextInt(parent.gene.length / 2) + 1;

        int[] geneOfChildAnimal = new int[parent.gene.length];
        //Copying first part of gene
        for (int i = 0; i < pointOfSplit; i++) {
            geneOfChildAnimal[i] = parent.gene[i];
            notUsedGene.remove(parent.gene[i]);
        }
        //Copying second part of genes
        AtomicInteger positionInNewGene = new AtomicInteger(pointOfSplit);
        for (int i = pointOfSplit; i < geneOfChildAnimal.length; i++) {
            int pieceOfGene = gene[i];
            if (notUsedGene.contains(pieceOfGene)) {
                notUsedGene.remove(pieceOfGene);
                geneOfChildAnimal[positionInNewGene.getAndIncrement()] = pieceOfGene;
            }
        }
        //Copying missing genes
        notUsedGene.forEach(e -> geneOfChildAnimal[positionInNewGene.getAndIncrement()] = e);
        return geneOfChildAnimal;
    }

    @Override
    public double fitToLive() {
        return 1.0 / path;
    }
}
