package com.d9nich.pathFindingAlgorithm.geneticAlgorithm.localStrategy;

import com.d9nich.pathFindingAlgorithm.geneticAlgorithm.Liveable;

import java.util.ArrayList;

public class OnePointLocal implements LocalImprovable {
    private final double lambda;

    public OnePointLocal(double lambda) {
        this.lambda = lambda;
    }

    @Override
    public void improve(ArrayList<? extends Liveable> animals, double liveFit) {
        animals.removeIf(liveable -> liveable.fitToLive() <= liveFit && liveFit - liveable.fitToLive() < lambda);
    }
}
