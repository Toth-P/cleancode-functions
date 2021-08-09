package com.epam.training.exercise1;

public class Details {
    private int max;
    private int min;
    private final int stepY;
    private final int stepX;

    public Details(int max, int min, int stepY, int stepX) {
        this.max = max;
        this.min = min;
        this.stepY = stepY;
        this.stepX = stepX;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getStepY() {
        return stepY;
    }

    public int getStepX() {
        return stepX;
    }

}
