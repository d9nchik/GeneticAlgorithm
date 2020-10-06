package com.d9nich.pathFindingAlgorithm.testAlgorithm.changable;

public class IntegerVariation implements Changeable<Integer> {
    private final int MAX;
    private final int MIN;
    private int currentValue;
    private int optimalValue;
    private boolean isTesting;
    private final int INCREASE_VALUE;

    public IntegerVariation(int MAX, int MIN, int INCREASE_VALUE) {
        this.MAX = MAX;
        this.MIN = MIN;
        this.INCREASE_VALUE = INCREASE_VALUE;
    }

    public IntegerVariation(int MAX, int MIN) {
        this(MAX, MIN, 1);
        this.currentValue = MIN;
        this.optimalValue = currentValue;
    }

    @Override
    public void change() {
        currentValue += INCREASE_VALUE;
        if (currentValue > MAX)
            throw new UnsupportedOperationException("Overflow");
    }

    @Override
    public boolean hasNext() {
        return currentValue + INCREASE_VALUE <= MAX;
    }

    @Override
    public void fromStart() {
        currentValue = MIN;
    }

    @Override
    public Integer get() {
        if (isTesting)
            return currentValue;
        return optimalValue;
    }

    @Override
    public void setOptimal() {
        optimalValue = currentValue;
    }

    @Override
    public void setTesting(boolean isTesting) {
        this.isTesting = isTesting;
    }

    @Override
    public int getOptimalNumber() {
        return optimalValue;
    }
}
