package com.d9nich.pathFindingAlgorithm.geneticAlgorithm.crossoverStrategy;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.IntStream;

public class CycleCrossing extends CrossingStrategy {
    private static int[] bornChild(int firstSplitPoint, int secondSplitPoint, int[] firstFather, int[] secondFather) {
        int[] firstChild = new int[firstFather.length];
        Set<Integer> set = new HashSet<>();
        IntStream.of(firstFather).forEach(set::add);

        int position = 0;
        while (set.contains(firstFather[position])) {
            set.remove(firstFather[position]);
            firstChild[position] = firstFather[position];
            position = positionOfElement(firstFather, secondFather[position]);
        }


        while (!set.isEmpty()) {
            position = positionOfElement(secondFather, set.iterator().next());
            set.remove(secondFather[position]);
            firstChild[position] = secondFather[position];
        }
        return firstChild;
    }

    private static int positionOfElement(int[] array, int value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value)
                return i;
        }
        return -1;
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
