package com.skaiblue.replicreator;

import java.util.*;

public class ConditionalTransitionListener implements AutomataWorld.OnTransitionListener
{


    private Map<DirectionValue, Integer> conditions = new HashMap<>();


    private AutomataWorld world;


    public ConditionalTransitionListener(AutomataWorld world)
    {
        this.world = world;

        for (int i = 0; i < world.getHeight(); i += 1)
        {
            for (int j = 0; j < world.getWidth(); j += 1)
            {
                Vector2 pos = new Vector2(j, i);
                DirectionValue directionValue = new DirectionValue(world.getState(pos.north()), world.getState(pos.west()), world.getState(pos), world.getState(pos.east()), world.getState(pos.south()));
                conditions.put(directionValue, world.getState(pos));
            }
        }
    }

    @Override
    public void onStartTransition()
    {

    }

    @Override
    public int onTransition(int north, int west, int center, int east, int south)
    {
        DirectionValue directionValue = new DirectionValue(north, west, center, east, south);
        if (conditions.containsKey(directionValue))
        {
            return conditions.get(directionValue);
        }
        conditions.put(directionValue, center);
        return 0;
    }

    @Override
    public void onEndTransition()
    {

    }

    public Map<DirectionValue, Integer> getConditions()
    {
        return conditions;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("%10s%10s%10s%10s%10s%10s\n", "north", "west", "center", "east", "south", "ts"));
        for (DirectionValue directionValue : conditions.keySet())
        {
            builder.append(String.format("%s%10d\n", directionValue.toString(), conditions.get(directionValue)));
        }
        return builder.toString();
    }

    public boolean isContain(DirectionValue directionValue)
    {
        return conditions.containsKey(directionValue);
    }
}
