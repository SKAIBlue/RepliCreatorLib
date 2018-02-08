package com.skaiblue.replicreator;

import javafx.scene.control.Cell;

public class AutomataWorld {

    private AutomataCells cells;

    private OnTransitionListener transitionListener;

    public AutomataWorld(int width, int height) {
        this.cells = new AutomataCells(width, height);
    }

    public AutomataWorld(int pattern[][]) {
        this.cells = new AutomataCells(pattern);
    }

    public AutomataWorld(AutomataWorld world)
    {
        transitionListener = world.transitionListener;
        cells = new AutomataCells(world.cells);
    }

    public AutomataCell get(Vector2 pos) {
        return cells.get(pos);
    }


    public void set(Vector2 pos, int state) {
        cells.set(pos, state);
    }


    public int getWidth() {
        return cells.getWidth();
    }


    public int getHeight() {
        return cells.getHeight();
    }


    public int getState(Vector2 pos) {
        return cells.getState(pos);
    }


    public void transition() {
        // TODO 다시
        if (transitionListener == null)
            return;
        AutomataCells nextState = new AutomataCells(getWidth(), getHeight());
        int width = this.getWidth();
        int height = this.getHeight();
        transitionListener.onStartTransition();
        for (int i = 0; i <= height; i += 1) {
            for (int j = 0; j <= width; j += 1) {
                Vector2 pos = new Vector2(j, i);
                int state = transitionListener.onTransition(getState(pos.north()), getState(pos.west()), getState(pos), getState(pos.east()), getState(pos.south()));
                nextState.set(pos, state);
            }
        }
        cells = nextState;
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
