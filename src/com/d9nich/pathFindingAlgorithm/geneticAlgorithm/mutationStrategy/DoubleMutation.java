package com.d9nich.pathFindingAlgorithm.geneticAlgorithm.mutationStrategy;

public class DoubleMutation extends SingleMutation {
    @Override
    public int[] mutate(int[] gene) {
        return super.mutate(super.mutate(gene));
    }
}
