package com.d9nich.pathFindingAlgorithm.geneticAlgorithm.crossoverStrategy;

import java.util.*;

public class PartiallyMapped extends CrossingStrategy {

    private static int[] bornChild(int firstSplitPoint, int secondSplitPoint, int[] firstFather, int[] secondFather) {
        int maxValue = firstFather.length;
        int[] firstChild = new int[maxValue];
        System.arraycopy(firstFather, 0, firstChild, 0, maxValue);
        Set<Integer> used = new HashSet<>();
        Map<Integer, Integer> changes = new HashMap<>();
        for (int i = firstSplitPoint; i < secondSplitPoint; i++) {
            used.add(firstChild[i]);
            changes.put(firstChild[i], i);
        }

        for (int i = 0; i < firstSplitPoint; i++) {
            int fatherGene = secondFather[i];
            for (int j = 0; j < 1000 && used.contains(fatherGene); j++) {
                fatherGene = secondFather[changes.get(fatherGene)];
            }
            firstChild[i] = fatherGene;
            used.add(fatherGene);
        }

        for (int i = secondSplitPoint; i < firstChild.length; i++) {
            int fatherGene = secondFather[i];
            while (used.contains(fatherGene)) {
                fatherGene = secondFather[changes.get(fatherGene)];
            }
            firstChild[i] = fatherGene;
            used.add(fatherGene);
        }

        return firstChild;
    }

    @Override
    public void crossAnimal() {
        Random random = new Random();
        int maxValue = firstFather.length;
        final int middleOfGene = maxValue / 2;
        int firstSplitPoint = random.nextInt(middleOfGene);
        int secondSplitPoint = random.nextInt(middleOfGene) + middleOfGene;
        //Born of first child
        firstChild = bornChild(firstSplitPoint, secondSplitPoint, firstFather, secondFather);
        secondChild = bornChild(firstSplitPoint, secondSplitPoint, secondFather, firstFather);

    }

}
