package com.d9nich.pathFindingAlgorithm;

public interface PathFindable {
    /**
     * @return path found by these algorithm
     */
    int[] getPath();

    /**
     * @return length of path found by algorithm
     */
    double getLength();
}
