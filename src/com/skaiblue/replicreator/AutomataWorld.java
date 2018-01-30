package com.skaiblue.replicreator;

public class AutomataWorld {


    private AutomataCell[][] cells;

    private int width = 10;

    private int height = 10;

    public AutomataWorld(int width, int height)
    {
        cells = new AutomataCell[height][];
        for(int i = 0 ; i < height ; i +=1)
        {
            cells[i] = new AutomataCell[width];
            for(int j = 0 ; j < width; j +=1)
            {
                cells[i][j] = new AutomataCell();
            }
        }
    }

    public AutomataCell get(Vector2 pos)
    {
        if(pos.x >= 0 && pos.x < width && pos.y >= 0 && pos.y < height)
            return cells[pos.y][pos.x];
        return new AutomataCell();
    }


    public int getState(Vector2 pos)
    {
        if(pos.x >= 0 && pos.x < width && pos.y >= 0 && pos.y < height)
            return cells[pos.y][pos.x].state;
        return 0;
    }



    public interface OnTransition
    {
        int transition(AutomataWorld world, Vector2 pos);
    }

}
