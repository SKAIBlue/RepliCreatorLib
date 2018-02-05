package com.skaiblue.replicreator;

import java.util.Objects;

public class DirectionValue {

    private int north;

    private int west;

    private int center;

    private int east;

    private int south;

    public DirectionValue(int north, int west, int center, int east, int south) {
        this.north = north;
        this.west = west;
        this.center = center;
        this.east = east;
        this.south = south;
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

    @Override
    public String toString() {
        return String.format("%10d%10d%10d%10d%10d", north, west, center, east, south);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DirectionValue that = (DirectionValue) o;
        return north == that.north &&
                west == that.west &&
                center == that.center &&
                east == that.east &&
                south == that.south;
    }

    @Override
    public int hashCode() {
        return Objects.hash(north, west, center, east, south);
    }
}
