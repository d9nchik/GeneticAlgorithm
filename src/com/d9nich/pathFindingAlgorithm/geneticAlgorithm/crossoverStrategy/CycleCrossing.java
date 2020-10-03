package com.d9nich.pathFindingAlgorithm.geneticAlgorithm.crossoverStrategy;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class CycleCrossing extends CrossingStrategy {
    private static int[] bornChild(int[] firstFather, int[] secondFather) {
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
        //Born of first child
        firstChild = bornChild(firstFather, secondFather);
        secondChild = bornChild(secondFather, firstFather);
    }
}
