package com.skaiblue;

import com.skaiblue.replicreator.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    private static Scanner scan = new Scanner(System.in);

    public static String inputString()
    {
        return scan.nextLine();
    }

    public static int inputInt()
    {
        int input = 0;
        boolean correct = false;
        do{
            String str = scan.nextLine();
            try {
                input = Integer.valueOf(str);
                correct = true;
            }catch (Exception e) {

            }
        }while(!correct);
        return input;
    }

    public static void main(String[] args) {
        AutomataTimeline timeline = new AutomataTimeline();

        int[][] pattern = {
                {0, 1, 1, 3, 0},
                {3, 2, 2, 2, 1},
                {1, 2, 1, 2, 1},
                {1, 2, 2, 2, 3},
                {0, 3, 1, 1, 0}
        };

        AutomataWorld world = timeline.getWorld();
        ConditionalTransitionListener listener = new ConditionalTransitionListener(world);
        timeline.setOnTransitionListener(listener);

        for(int i = 0 ; i < 5 ; i +=1)
        {
            for(int j = 0 ; j < 5 ; j +=1)
            {
                world.set(new Vector2(j + 1, i + 1), pattern[i][j]);
            }
        }
        timeline.next();
        print(timeline.getWorld());
        String input;
        do{
            System.out.print("Input: ");
            input = inputString();
            if("set".equals(input))
            {
                System.out.print("x: ");
                int x = inputInt();
                System.out.print("y: ");
                int y = inputInt();
                System.out.print("state: ");
                int state = inputInt();
                Vector2 pos = new Vector2(x, y);
                AutomataWorld now = timeline.getWorld();
                DirectionValue directionValue = new DirectionValue(now.getState(pos.north()), now.getState(pos.west()), now.getState(pos), now.getState(pos.east()), now.getState(pos.south()));
                System.out.println(directionValue);
                if(listener.isContain(directionValue))
                {
                    System.out.println("변경할 수 없는 셀 입니다.");
                }
                else
                {
                    timeline.getWorld().set(pos, state);
                }
            }
            else if("next".equals(input))
            {
                timeline.next();
                print(timeline.getWorld());
                //System.out.println(listener.toString());
            }
            else if("prev".equals(input))
            {
                timeline.prev();
                print(timeline.getWorld());
            }
            else if("print".equals(input))
            {
                print(timeline.getWorld());
            }
            else if("printc".equals(input))
            {
                System.out.println(listener.toString());
            }
            else if("time".equals(input))
            {
                System.out.println(String.format("Now %d", timeline.getIndex()));
            }
            else if("help".equals(input))
            {
                System.out.println(String.format("%s: %s", "set", "셀에 상태를 입력합니다."));
                System.out.println(String.format("%s: %s", "next", "다음 상태로 이동합니다."));
                System.out.println(String.format("%s: %s", "prev", "이전 상태로 돌아갑니다."));
                System.out.println(String.format("%s: %s", "print", "셀을 출력합니다."));
                System.out.println(String.format("%s: %s", "printc", "만들어진 조건을 출력합니다."));
                System.out.println(String.format("%s: %s", "time", "현재 시간 위치를 출력합니다."));
            }
            else
            {
                System.out.println("존재하지 않는 명령어");
            }
        }while(!"end".equals(input));
    }

    private static void print(AutomataWorld world) {
        System.out.println("_________________________________________________________________________________________");
        System.out.println(String.format("Size = (%d, %d)", world.getWidth(), world.getHeight()));
        System.out.print(String.format("%4s ", "x"));
        for(int j = 0 ; j < world.getWidth(); j +=1)
        {
            System.out.print(String.format("%4d", j));
        }
        System.out.println();
        for(int i = 0 ; i < world.getHeight(); i +=1)
        {
            System.out.print(String.format("%4d:", i));
            for(int j = 0 ; j < world.getWidth(); j +=1)
            {
                System.out.print(String.format("%4d", world.getState(new Vector2(j, i))));
            }
            System.out.println();
        }
    }

}
