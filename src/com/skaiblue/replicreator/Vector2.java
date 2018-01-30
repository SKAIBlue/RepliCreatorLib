package com.skaiblue.replicreator;

public class Vector2 {


    public int x;

    public int y;

    public Vector2() {
    }


    public Vector2(int x) {
        this.x = x;
    }


    public Vector2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector2 north()
    {
        return new Vector2(x, y - 1);
    }

    public Vector2 south()
    {
        return new Vector2(x, y + 1);
    }

    public Vector2 east()
    {
        return new Vector2(x + 1, y );
    }

    public Vector2 west()
    {
        return new Vector2(x - 1, y );
    }


}
