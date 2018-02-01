package com.skaiblue.replicreator;

import javafx.scene.control.Cell;

public class AutomataWorld {

    private AutomataCell[][] cells;

    private int width = 10;

    private int height = 10;

    private OnTransitionListener transitionListener;

    public AutomataWorld(int width, int height) {
        this.width = width;
        this.height = height;
        initializeCell(width, height);
    }

    public AutomataWorld(int pattern[][]) {
        if (pattern == null || pattern[0] == null) {
            initializeCell(width, height);
            return;
        }
        height = pattern.length;
        width = pattern[0].length;

        cells = new AutomataCell[pattern.length][];

        for (int i = 0; i < height; i += 1) {
            cells[i] = new AutomataCell[width];
            for (int j = 0; j < width; j += 1) {
                cells[i][j] = new AutomataCell(pattern[i][j]);
            }
        }
    }

    public AutomataWorld(AutomataWorld world)
    {
        width = world.width;
        height = world.height;
        transitionListener = world.transitionListener;
        cells = new AutomataCell[height][];
        for(int i = 0 ; i < height ; i +=1)
        {
            cells[i] = new AutomataCell[width];
            for(int j = 0 ; j < width ; j +=1)
            {
                cells[i][j] = new AutomataCell(world.getState(new Vector2(j, i)));
            }
        }
    }

    private void initializeCell(int width, int height) {
        cells = new AutomataCell[height][];
        for (int i = 0; i < height; i += 1) {
            cells[i] = new AutomataCell[width];
            for (int j = 0; j < width; j += 1) {
                cells[i][j] = new AutomataCell();
            }
        }
    }


    public AutomataCell get(Vector2 pos) {
        if (pos.x >= 0 && pos.x < width && pos.y >= 0 && pos.y < height)
            return cells[pos.y][pos.x];
        return new AutomataCell();
    }

    public void set(Vector2 pos, int state) {
        if (pos.x >= 0 && pos.x < width && pos.y >= 0 && pos.y < height)
            cells[pos.y][pos.x].state = state;
        else if (pos.x >= 0 && pos.y >= 0)
            expandCellAndSet(pos, state);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private void expandCellAndSet(Vector2 pos, int state) {
        int prevWidth = width;
        int prevHeight = height;
        AutomataCell[][] prevCell = cells;

        int newWidth = (pos.x + 1 > prevWidth) ? pos.x + 1 : prevWidth;
        int newHeight = (pos.y + 1 > prevHeight) ? pos.y + 1 : prevHeight;

        AutomataCell[][] newCell = new AutomataCell[newHeight][];

        for (int i = 0; i < prevHeight; i += 1) {
            newCell[i] = new AutomataCell[newWidth];
            for (int j = 0; j < prevWidth; j += 1) {
                newCell[i][j] = prevCell[i][j];
            }
        }

        for (int i = 0; i < prevHeight; i += 1) {
            for (int j = prevWidth; j < newWidth; j += 1) {
                newCell[i][j] = new AutomataCell();
            }
        }

        for (int i = prevHeight; i < newHeight; i += 1) {
            newCell[i] = new AutomataCell[newWidth];
            for (int j = 0; j < newWidth; j += 1) {
                newCell[i][j] = new AutomataCell();
            }
        }

        width = newWidth;
        height = newHeight;
        cells = newCell;
        cells[pos.y][pos.x].state = state;

    }


    public int getState(Vector2 pos) {
        if (pos.x >= 0 && pos.x < width && pos.y >= 0 && pos.y < height)
            return cells[pos.y][pos.x].state;
        return 0;
    }

    public void transition() {
        if (transitionListener == null)
            return;
        transitionListener.onStartTransition();
        for (int i = 0; i < height; i += 1) {
            for (int j = 0; j < width; j += 1) {
                Vector2 pos = new Vector2(j, i);
                transitionListener.onTransition(getState(pos.north()), getState(pos.west()), getState(pos), getState(pos.east()), getState(pos.south()));
            }
        }
        transitionListener.onEndTransition();
    }

    public void setOnTransitionListener(OnTransitionListener transitionListener) {
        this.transitionListener = transitionListener;
    }

    public interface OnTransitionListener {

        void onStartTransition();
        /**
         * if you called AutomataWorld.transition it will call onTransition method for each cells
         *
         * @param north  north value
         * @param west   west value
         * @param center center value
         * @param east   east value
         * @param south  south value
         * @return transition value
         */
        int onTransition(int north, int west, int center, int east, int south);

        void onEndTransition();
    }

}
