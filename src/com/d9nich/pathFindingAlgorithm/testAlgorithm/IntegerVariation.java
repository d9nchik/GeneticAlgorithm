package com.d9nich.pathFindingAlgorithm.testAlgorithm;

public class IntegerVariation implements Changeable<Integer> {
    private final int MAX;
    private final int MIN;
    private int currentValue;

    public IntegerVariation(int MAX, int MIN) {
        this.MAX = MAX;
        this.MIN = MIN;
        this.currentValue = MIN;
    }

    @Override
    public void change() {
        currentValue++;
        if (currentValue > MAX)
            throw new UnsupportedOperationException("Overflow");
    }

    @Override
    public boolean hasNext() {
        return currentValue != MAX;
    }

    @Override
    public void fromStart() {
        currentValue = MIN;
    }

    @Override
    public Integer get() {
        return currentValue;
    }
}
