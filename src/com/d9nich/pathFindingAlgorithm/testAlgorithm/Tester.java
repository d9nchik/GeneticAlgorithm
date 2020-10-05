package com.d9nich.pathFindingAlgorithm.testAlgorithm;

import com.d9nich.MatrixDistanceGenerator;
import com.d9nich.pathFindingAlgorithm.geneticAlgorithm.GeneticWorld;
import com.d9nich.pathFindingAlgorithm.geneticAlgorithm.PathSearchingAnimal;
import com.d9nich.pathFindingAlgorithm.geneticAlgorithm.crossoverStrategy.CrossingStrategy;
import com.d9nich.pathFindingAlgorithm.geneticAlgorithm.crossoverStrategy.CycleCrossing;
import com.d9nich.pathFindingAlgorithm.geneticAlgorithm.crossoverStrategy.OrderCrossing;
import com.d9nich.pathFindingAlgorithm.geneticAlgorithm.crossoverStrategy.PartiallyMapped;
import com.d9nich.pathFindingAlgorithm.geneticAlgorithm.localStrategy.LocalImprovable;
import com.d9nich.pathFindingAlgorithm.geneticAlgorithm.localStrategy.NullLocal;
import com.d9nich.pathFindingAlgorithm.geneticAlgorithm.localStrategy.OnePointLocal;
import com.d9nich.pathFindingAlgorithm.geneticAlgorithm.mutationStrategy.DoubleMutation;
import com.d9nich.pathFindingAlgorithm.geneticAlgorithm.mutationStrategy.Mutable;
import com.d9nich.pathFindingAlgorithm.geneticAlgorithm.mutationStrategy.SingleMutation;
import com.d9nich.pathFindingAlgorithm.geneticAlgorithm.selectionStrategy.*;
import com.d9nich.pathFindingAlgorithm.testAlgorithm.changable.Changeable;
import com.d9nich.pathFindingAlgorithm.testAlgorithm.changable.Changer;
import com.d9nich.pathFindingAlgorithm.testAlgorithm.changable.IntegerVariation;
import com.d9nich.pathFindingAlgorithm.testAlgorithm.changable.ObjectVariation;

public class Tester {
    public static void main(String[] args) {
        Changeable<Integer> percentOfMutation = new IntegerVariation(100, 0);
        Changeable<SelectionStrategy<PathSearchingAnimal>> selectionStrategy =
                new ObjectVariation<SelectionStrategy<PathSearchingAnimal>>(new SelectionStrategy[]{
                        new InversedSelection<PathSearchingAnimal>(), new MixedSelection<PathSearchingAnimal>(),
                        new RandomSelection<PathSearchingAnimal>(), new TournamentSelection<PathSearchingAnimal>()});
        Changeable<CrossingStrategy> crossingStrategy = new ObjectVariation<>(new CrossingStrategy[]{
                new CycleCrossing(), new OrderCrossing(), new PartiallyMapped()});
        Changeable<Mutable> mutable = new ObjectVariation<>(new Mutable[]{
                new SingleMutation(), new DoubleMutation()});
        Changeable<LocalImprovable> localImprovable = new ObjectVariation<>(new LocalImprovable[]{
                new OnePointLocal(1.0 / 100_000_00), new NullLocal()});
        Changeable<Integer> numberOfAnimals = new IntegerVariation(600, 20);
        Changer changer = new Changer(new Changeable[]{percentOfMutation, selectionStrategy, crossingStrategy, mutable,
                localImprovable, numberOfAnimals}, 5);

        double best = Double.POSITIVE_INFINITY;
        final int[][] MATRIX_OF_DISTANCE = MatrixDistanceGenerator.generate(300);
        while (changer.hasNext()) {
            changer.iterate();
            double newWorldLength = workOfWorld(new GeneticWorld(MATRIX_OF_DISTANCE, percentOfMutation.get(),
                    selectionStrategy.get(), crossingStrategy.get(), mutable.get(), localImprovable.get(),
                    numberOfAnimals.get()));
            if (newWorldLength < best) {
                best = newWorldLength;
                changer.setOptimal();
                System.out.println(best);
            }
        }
        System.out.println("Results of work:");
        System.out.println("Percent of mutation: " + percentOfMutation.getOptimalNumber());
        System.out.println("Selection strategy: " + selectionStrategy.getOptimalNumber());
        System.out.println("Crossing strategy: " + crossingStrategy.getOptimalNumber());
        System.out.println("Mutation strategy: " + mutable.getOptimalNumber());
        System.out.println("Local improvement: " + localImprovable.getOptimalNumber());
        System.out.println("Number of animals: " + numberOfAnimals.getOptimalNumber());
        System.out.println("Best: " + best);
    }

    private static double workOfWorld(GeneticWorld geneticWorld) {
        for (int i = 0; i < 5_000; i++) {
            geneticWorld.iterate();
        }
        return geneticWorld.getLength();
    }
}
