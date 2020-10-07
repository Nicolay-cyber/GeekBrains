package ru.geekbrains.lesson4;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Lesson4 {
    static char [][] map;
    static int SIZE = 3;
    static int DOTS_TO_WIN = 3;
    static char DOT_EMPTY = '•';
    static char AI_PIECE = DOT_EMPTY;
    static char PLAYER_PIECE = DOT_EMPTY;
    static boolean IS_PLAYER_FIRST;
    static int SCORE = 0;
    static int THIS_X_MUST_BE_BLOCKED = -1;
    static int THIS_Y_MUST_BE_BLOCKED = -1;

    public static void main(String[] args)
    {
        choosePiece();
        setSize();
        setDotsToWin();
        createMap();
        chooseFirstPlayer();
        printMap();
        while (true){
            if (IS_PLAYER_FIRST){
                playerTurn();
                if(hasWin(PLAYER_PIECE)){
                    printMap();
                    System.out.println("\nYou are win!");
                    break;
                }
                if(hasDraw()){
                    printMap();
                    System.out.println("\nIt's draw!");
                    break;
                }
            }
            aiTurn();
            if(hasWin(AI_PIECE)){
                printMap();
                System.out.println("\nComputer is win");
                break;
            }
            if(hasDraw()){
                printMap();
                System.out.println("\nIt's draw!");
                break;
            }
            printMap();
        }
    }
    static void choosePiece()
    {
        System.out.println("\nChoose the game piece\n\nPress 1 for X\nPress 2 for O\n");
        switch (getNumberFromConsoleOnlyBetween(1,2)){
            case 1:
                PLAYER_PIECE = 'X';
                AI_PIECE = 'O';
                break;
            case 2:
                PLAYER_PIECE = 'O';
                AI_PIECE = 'X';
                break;
        }
        System.out.println("Your piece is " + PLAYER_PIECE);
    }
    static void setSize()
    {
        System.out.println("\nSet the size of the map from 3 to 9");
        SIZE = getNumberFromConsoleOnlyBetween(3, 9);
    }
    static void setDotsToWin()
    {
        System.out.println("\nSet the count dots to win from 2 to " + SIZE);
        DOTS_TO_WIN = getNumberFromConsoleOnlyBetween(2, SIZE);
    }
    static int getNumberFromConsoleOnlyBetween(int from, int to)
    {
        do{
            int inputNumber = getNumberFromConsole(from, to);
            if (inputNumber >= from && inputNumber <= to){
                return inputNumber;
            }
            System.out.println("Type only integer between " + from + " and " + to);
        }
        while (true);
    }
    static int getNumberFromConsole(int from, int to)
    {
        Scanner scanner = new Scanner(System.in);
        do
        {
            if(scanner.hasNextInt()){
                return scanner.nextInt();
            }
            System.out.println("Type only integer between " + from + " and " + to);
            scanner.nextLine();
        }
        while (true);
    }
    static void chooseFirstPlayer()
    {
        System.out.println("Type 1 if you want to have the first move");
        System.out.println("Type 2 if you want to have the second move");
        switch (getNumberFromConsoleOnlyBetween(1,2))
        {
            case 1:
                IS_PLAYER_FIRST = true;
                break;
            case 2:
                IS_PLAYER_FIRST = false;
                break;
        }
    }
    static void createMap()
    {
        map = new char[SIZE][SIZE];
        for(int i = 0; i < map.length; i++)
        {
            Arrays.fill(map[i], DOT_EMPTY);
        }
    }
    static void printMap()
    {
        System.out.println();
        for (int i = 0; i < map.length + 1; i++)
        {
            if (i == 0)
            {
                System.out.print("  ");
            }
            else
            {
                System.out.print(i + " ");
            }
        }
        System.out.println();
        for (int i = 0; i < map.length; i++)
        {
            System.out.print(i + 1 + " ");
            for(int j = 0; j < map[i].length; j++)
            {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    static boolean isCellEmpty(int x, int y)
    {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE)
        {
            return false;
        }
        else if (map[y][x] == DOT_EMPTY)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    static void playerTurn()
    {
        Scanner scanner = new Scanner(System.in);
        int x;
        int y;
        do
        {
            System.out.println("Type coordinates in X Y format");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        }
        while (!(isCellEmpty(x, y)));
        map[y][x] = PLAYER_PIECE;
    }
    static void aiTurn()
    {
        Random random = new Random();
        int x;
        int y;
        do
        {
            if(THIS_Y_MUST_BE_BLOCKED > -1 && THIS_X_MUST_BE_BLOCKED > -1)
            {
                x = THIS_X_MUST_BE_BLOCKED;
                y = THIS_Y_MUST_BE_BLOCKED;
                THIS_X_MUST_BE_BLOCKED = -1;
                THIS_Y_MUST_BE_BLOCKED = -1;
            }
            else
            {
                x = random.nextInt(SIZE);
                y = random.nextInt(SIZE);
            }
        }
        while (!isCellEmpty(x,y));
        System.out.println("\nThe computer moves to the point: " + (x + 1) + " " + (y + 1));
        map[y][x] = AI_PIECE;
        IS_PLAYER_FIRST = true;
    }
    static boolean hasWin(char symbol)
    {
        return checkingOfTheStrings(symbol) ||
                checkingOfTheColumns(symbol) ||
                checkingOfThePrimaryDiagonals(symbol) ||
                checkingOfTheSecondaryDiagonals(symbol);
    }
    static boolean checkingOfTheSecondaryDiagonals(char symbol)
    {
        // по побочным диагоналям
        SCORE = 0;
        for (int i = 0; i < (SIZE * 2) - 1; i++)
        {
            for(int j = 0; j < SIZE - Math.abs(SIZE - (i + 1)); j ++)
            {
                if(SCORE == DOTS_TO_WIN)
                {
                    return true;
                }
                if(i > (SIZE - 1))
                {
                    if(map[i-(SIZE - 1) + j][(SIZE - 1) - j] != symbol && SCORE > 0){
                        SCORE = 0;
                    }
                    else if(map[i-(SIZE - 1) + j][(SIZE - 1) - j] == symbol)
                    {
                        SCORE++;
                    }
                }
                else
                {
                    if(map[j][i-j] != symbol && SCORE > 0)
                    {
                        SCORE = 0;
                    }
                    else if(map[j][i-j] == symbol)
                    {
                        SCORE++;
                    }
                }
                if(j == (SIZE - Math.abs(SIZE - (i + 1))) - 1 && SCORE < DOTS_TO_WIN){

                    SCORE = 0;
                }
            }

        }

        return false;
    }
    static boolean checkingOfThePrimaryDiagonals(char symbol)
    {
        SCORE = 0;
        for (int i = 0; i < (SIZE * 2) - 1; i++)
        {
            for(int j = 0; j < SIZE - Math.abs(SIZE - (i + 1)); j ++)
            {
                if(SCORE == DOTS_TO_WIN)
                {
                    return true;
                }
                if(i > (SIZE - 1))
                {
                    if(map[j][j + i - (SIZE -1)] != symbol && SCORE > 0){
                        SCORE = 0;
                    }
                    else if(map[j][j + i - (SIZE -1)] == symbol)
                    {
                        SCORE++;
                    }
                }
                else
                {
                    if(map[(SIZE - 1) - i + j][j] != symbol && SCORE > 0)
                    {
                        SCORE = 0;
                    }
                    else if(map[(SIZE - 1) - i + j][j] == symbol)
                    {
                        SCORE++;
                    }

                }
                if(j == (SIZE - Math.abs(SIZE - (i + 1))) - 1 && SCORE < DOTS_TO_WIN){
                    SCORE = 0;
                }
            }

        }

        return false;
    }
    static boolean checkingOfTheColumns(char symbol)
    {
        SCORE = 0;
        for(int i = 0; i < map.length; i++)
        {
            for(int j = 0; j < map.length; j++)
            {
                if(SCORE == DOTS_TO_WIN)
                {
                    return true;
                }
                if(map[j][i] != symbol && SCORE > 0)
                {
                    blockRight(j, i);
                    SCORE = 0;
                }
                else if(map[j][i] == symbol)
                {
                    SCORE++;
                }
                if(j == SIZE - 1 && SCORE < DOTS_TO_WIN){
                    SCORE = 0;
                }
            }
        }
        return false;
    }
    static boolean checkingOfTheStrings(char symbol)
    {
        for(int i = 0; i < SIZE; i++)
        {
            SCORE = 0;
            for (int j = 0; j < SIZE; j++)
            {
                if((map[i][j] != symbol && SCORE > 0))
                {
                    blockLeft(i,j);
                    blockRight(i,j);
                    SCORE = 0;
                }
                else if(map[i][j] == symbol)
                {
                    SCORE++;
                }
                if(SCORE == DOTS_TO_WIN)
                {
                    return true;
                }
                if(j == SIZE - 1){
                    blockLeft(i, j);
                    SCORE = 0;
                }
            }
        }
        return false;

    }
    static void blockRight(int x, int y)
    {
        if(SCORE > 1 && map[x][y] == DOT_EMPTY)
        {
            System.out.println("RIGHT!");
            THIS_X_MUST_BE_BLOCKED = y;
            THIS_Y_MUST_BE_BLOCKED = x;
        }
    }
    static void blockTOP(int x, int y)
    {

    }
    static void blockLeft(int x, int y)
    {
        if(SCORE > 1 && map[x][y - 1] == PLAYER_PIECE)
        {
            {
                System.out.println("LEFT!");
                THIS_X_MUST_BE_BLOCKED = y - (SCORE + 1);
                if(y == SIZE - 1){
                    THIS_X_MUST_BE_BLOCKED = y - (SCORE);
                }
                THIS_Y_MUST_BE_BLOCKED = x;
            }
        }
    }
    static boolean hasDraw()
    {
        for (int i = 0; i < map.length; i++)
        {
            for (int j = 0; j < map[i].length; j++)
            {
                if (map[i][j] == DOT_EMPTY)
                {
                    return false;
                }
            }
        }
        return true;
    }
}
