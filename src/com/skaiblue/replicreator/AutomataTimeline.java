package com.skaiblue.replicreator;

import java.util.ArrayList;
import java.util.List;

public class AutomataTimeline {

    private List<AutomataWorld> history = new ArrayList<>();

    public AutomataTimeline()
    {
        history.add(new AutomataWorld(2, 2));
    }

    public void setOnTransitionListener(AutomataWorld.OnTransitionListener transitionListener) {
        for(int i = 0 ; i < history.size() ; i+=1)
        {
            history.get(i).setOnTransitionListener(transitionListener);
        }
    }

    public void prev()
    {
        history.remove(getIndex());
    }

    public void next()
    {
        history.add(new AutomataWorld(getWorld()));
        getWorld().transition();
    }

    public int getIndex()
    {
        return history.size() - 1;
    }

    public AutomataWorld getWorld()
    {
        return history.get(getIndex());
    }

}
