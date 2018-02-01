package com.skaiblue;

import com.skaiblue.replicreator.AutomataCell;
import com.skaiblue.replicreator.AutomataWorld;
import com.skaiblue.replicreator.Vector2;

import java.util.HashSet;
import java.util.Objects;

public class Main {

    private static class HashTest{
        private int a;
        private int b;

        public HashTest(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            HashTest hashTest = (HashTest) o;
            return a == hashTest.a &&
                    b == hashTest.b;
        }

        @Override
        public int hashCode() {

            return Objects.hash(a);
        }
    }

    public static void main(String[] args) {
        AutomataWorld world =  new AutomataWorld(1, 2);
        print(world);

        world.set(new Vector2(2, 2), 1);
        world.set(new Vector2(4, 2), 1);
        print(world);
    }

    private static void print(AutomataWorld world) {
        System.out.println("---------------------------------------------------------");
        System.out.println(String.format("Size = (%d, %d)", world.getWidth(), world.getHeight()));
        for(int i = 0 ; i < world.getHeight(); i +=1)
        {
            for(int j = 0 ; j < world.getWidth(); j +=1)
            {
                System.out.print(world.getState(new Vector2(j, i)) + " ");
            }
            System.out.println();
        }
    }

}
