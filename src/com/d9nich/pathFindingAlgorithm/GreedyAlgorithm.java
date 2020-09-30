package com.d9nich.pathFindingAlgorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class GreedyAlgorithm implements PathFindable {
    public static final int INFINITY = Integer.MAX_VALUE / 2;
    private final int[][] MATRIX_OF_DISTANCE;
    private final ArrayList<Integer> PATH = new ArrayList<>();
    private double pathLength;

    /**
     * Creates GreedyAlgorithm object
     *
     * @param MATRIX_OF_DISTANCE matrix, that represents distance between nodes.
     */
    public GreedyAlgorithm(int[][] MATRIX_OF_DISTANCE) {
        this.MATRIX_OF_DISTANCE = MATRIX_OF_DISTANCE;
        findL();
    }

    /**
     * Search approximate distance and path of travelling salesman problem
     */
    private void findL() {
        Set<Integer> pointsToVisit = new HashSet<>();
        for (int i = 1; i < MATRIX_OF_DISTANCE.length; i++) {
            pointsToVisit.add(i);
        }
        pathLength = findL(0, pointsToVisit, 0);
        PATH.add(0);
    }

    /**
     * Helper method for findL method
     *
     * @param startPoint    starting node
     * @param pointsToVisit nodes, that hasn't been visited
     * @param endPoint      point in which we should end our path
     * @return path length
     */
    private double findL(int startPoint, Set<Integer> pointsToVisit, int endPoint) {
        if (pointsToVisit.isEmpty()) {
            int finalDistance = MATRIX_OF_DISTANCE[startPoint][endPoint];
            if (finalDistance < INFINITY)
                return finalDistance;
            else
                return -1;
        }

        int[] distance = MATRIX_OF_DISTANCE[startPoint];
        ArrayList<Edge> edges = new ArrayList<>();
        pointsToVisit.forEach(e -> edges.add(new Edge(distance[e], e)));
        Collections.sort(edges);

        for (Edge edge : edges) {
            final int nextStartPoint = edge.getNumber();
            pointsToVisit.remove(nextStartPoint);
            double distanceOfPath = findL(nextStartPoint, pointsToVisit, endPoint);
            if (distanceOfPath >= 0) {
                PATH.add(nextStartPoint);
                return distanceOfPath + edge.getDistance();
            }
            pointsToVisit.add(nextStartPoint);
        }
        return -1;
    }

    /**
     * @return path
     */
    @Override
    public int[] getPath() {
        int[] result = new int[PATH.size()];
        for (int i = 0; i < PATH.size(); i++) {
            result[i] = PATH.get(i);
        }
        return result;
    }

    /**
     * @return length of path
     */
    @Override
    public double getLength() {
        return pathLength;
    }
}
