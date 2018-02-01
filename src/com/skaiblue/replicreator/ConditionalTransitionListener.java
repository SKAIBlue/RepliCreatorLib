package com.skaiblue.replicreator;

import java.util.*;

public class ConditionalTransitionListener implements AutomataWorld.OnTransitionListener {

    public Map<DirectionValue, Integer> conditions = new HashMap<>();

    @Override
    public void onStartTransition() {

    }

    @Override
    public int onTransition(int north, int west, int center, int east, int south) {
        DirectionValue directionValue = new DirectionValue(north, west, center, east,south);
        if(conditions.containsKey(directionValue))
        {
            return conditions.get(directionValue);
        }
        conditions.put(directionValue, center);
        return 0;
    }

    @Override
    public void onEndTransition() {

    }


}
