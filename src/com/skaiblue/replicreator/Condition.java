package com.skaiblue.replicreator;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Condition condition = (Condition) o;
        return north == condition.north &&
                west == condition.west &&
                center == condition.center &&
                east == condition.east &&
                south == condition.south &&
                transitionValue == condition.transitionValue;
    }

    @Override
    public int hashCode() {
        return Objects.hash(north, west, center, east, south);
    }
}
