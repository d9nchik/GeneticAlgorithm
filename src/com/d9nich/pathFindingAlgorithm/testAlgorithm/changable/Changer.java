package com.d9nich.pathFindingAlgorithm.testAlgorithm.changable;

public class Changer {
    private final Changeable<Object>[] array;
    private final int NUMBER_OF_CYCLES;
    private int position = 0;
    private int cycleNumber = 0;


    public Changer(Changeable<Object>[] array, int number_of_cycles) {
        if (array.length == 0)
            throw new IllegalArgumentException("Length of array can not be zero");
        this.array = array;
        NUMBER_OF_CYCLES = number_of_cycles;
    }

    public boolean hasNext() {
        return NUMBER_OF_CYCLES != cycleNumber;
    }

    public void iterate() {
        final Changeable<Object> changeable = array[position];
        if (changeable.hasNext()) {
            changeable.setTesting(true);
            changeable.change();
        } else {
            changeable.setTesting(false);
            changeable.fromStart();
            position++;
            System.out.println("Check parameter number: " + (position % array.length + 1));
            if (position >= array.length) {
                position = 0;
                cycleNumber++;
                System.out.println("Cycle: " + (cycleNumber + 1));
            }
            iterate();
        }
    }

    public void setOptimal() {
        array[position].setOptimal();
    }
}
