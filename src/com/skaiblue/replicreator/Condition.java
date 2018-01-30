package com.skaiblue.replicreator;

public class Condition {

    private int north;

    private int west;

    private int center;

    private int east;

    private int south;

    private int transitionValue;

    public Condition(int north, int west, int center, int east, int south, int transitionValue) {
        this.north = north;
        this.west = west;
        this.center = center;
        this.east = east;
        this.south = south;
        this.transitionValue = transitionValue;
    }

    public int getNorth() {
        return north;
    }

    public int getWest() {
        return west;
    }

    public int getCenter() {
        return center;
    }

    public int getEast() {
        return east;
    }

    public int getSouth() {
        return south;
    }

    public int getTransitionValue() {
        return transitionValue;
    }


    public int match(int north, int west, int center, int east, int south)
    {
        if(isMatch(north, west, center, east, south))
            return transitionValue;
        return 0;
    }


    public boolean isMatch(int north, int west, int center, int east, int south) {
        return north == this.north && west == this.west && center == this.center && east == this.east && south == this.south;
    }
}
