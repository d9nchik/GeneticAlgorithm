package com.d9nich.pathFindingAlgorithm;

public class Edge implements Comparable<Edge> {
    private final double distance;
    private final int number;

    public Edge(double distance, int number) {
        this.distance = distance;
        this.number = number;
    }

    public double getDistance() {
        return distance;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public int compareTo(Edge o) {
        return Double.compare(distance, o.distance);
    }

    @Override
    public String toString() {
        return "Edge{" + "distance=" + distance +
                ", number=" + number +
                '}';
    }
}
