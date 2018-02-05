package com.skaiblue.replicreator;

public class AutomataCells {

    private AutomataCell[][] cells;

    private int width = 10;

    private int height = 10;

    public AutomataCells(int width, int height) {
        this.width = width;
        this.height = height;
        this.cells = initializeCell(width, height);
    }

    public AutomataCells(int pattern[][]) {
        if (pattern == null || pattern[0] == null) {
            this.cells = initializeCell(width, height);
            return;
        }
        height = pattern.length;
        width = pattern[0].length;

        cells = new AutomataCell[height][];
        for (int i = 0; i < height; i += 1) {
            cells[i] = new AutomataCell[width];
            for (int j = 0; j < width; j += 1) {
                cells[i][j] = new AutomataCell(pattern[i][j]);
            }
        }
    }

    public AutomataCells(AutomataCells src) {
        width = src.width;
        height = src.height;
        cells = new AutomataCell[height][];
        for (int i = 0; i < height; i += 1) {
            cells[i] = new AutomataCell[width];
            for (int j = 0; j < width; j += 1) {
                cells[i][j] = new AutomataCell(src.getState(new Vector2(j, i)));
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


    public AutomataCell get(Vector2 pos) {
        if (pos.x >= 0 && pos.x < width && pos.y >= 0 && pos.y < height)
            return cells[pos.y][pos.x];
        return new AutomataCell();
    }

    public int getState(Vector2 pos) {
        if (pos.x >= 0 && pos.x < width && pos.y >= 0 && pos.y < height)
            return cells[pos.y][pos.x].state;
        return 0;
    }

    public void set(Vector2 pos, int state) {
        if(getState(pos) == 0 && state == 0)
            return;
        if (pos.x >= 0 && pos.x < width && pos.y >= 0 && pos.y < height)
            cells[pos.y][pos.x].state = state;
        else if (pos.x >= 0 && pos.y >= 0)
            expandCellAndSet(pos, state);
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

    private AutomataCell[][] initializeCell(int width, int height) {
        AutomataCell[][] cells = new AutomataCell[height][];
        for (int i = 0; i < height; i += 1) {
            cells[i] = new AutomataCell[width];
            for (int j = 0; j < width; j += 1) {
                cells[i][j] = new AutomataCell();
            }
        }
        return cells;
    }
}
