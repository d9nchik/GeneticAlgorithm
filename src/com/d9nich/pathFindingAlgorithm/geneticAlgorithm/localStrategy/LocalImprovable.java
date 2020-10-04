package com.d9nich.pathFindingAlgorithm.geneticAlgorithm.localStrategy;

import com.d9nich.pathFindingAlgorithm.geneticAlgorithm.Liveable;

import java.util.ArrayList;

public interface LocalImprovable {
    void improve(ArrayList<? extends Liveable> animals, int liveFit);
}
