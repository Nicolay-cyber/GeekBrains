package ru.structureAndArchitectureData.ArraysandSort;

import java.util.Arrays;

public class ArrayAndSort {
    static int[] arr;
    static int[] initArr;
    static long startTime;
    static long endTime;

    public static void main(String[] args)
    {

        // 1. Создать массив большого размера (100000 элементов).
        initArr = new int[100000];

        // 2. Заполнить массив случайными числами.
        fillArr();

        // 3. Написать методы, реализующие рассмотренные виды сортировок,
        // и проверить скорость выполнения каждой.

        // Пузырьковая сортировка
        System.out.println("Bubble sort");
        startTime();
        bubbleSort();
        endTime();

        // Сортировка методом выбора
        setInitArray();
        System.out.println("Select sort");
        startTime();
        selectSort();
        endTime();

        // Сортировка методом вставки
        setInitArray();
        System.out.println("Insert sort");
        startTime();
        insertSort();
        endTime();
    }
    public static void fillArr()
    {
        for (int i = 0; i < initArr.length; i++)
        {
            initArr[i] = (int) (Math.random() * 1000);
        }
        setInitArray();
    }
    private static void setInitArray()
    {
        arr = Arrays.copyOf(initArr, initArr.length);
    }
    public static void bubbleSort()
    {
        int outer = arr.length;
        int var;
        do {
            for (int i = 1; i < outer; i++) {
                if (arr[i] < arr[i - 1]) {
                    var = arr[i - 1];
                    arr[i - 1] = arr[i];
                    arr[i] = var;
                }
            }
            outer--;
        } while (outer != 0);
    }
    private static void insertSort()
    {
        int temp;
        for(int outer = 1; outer < arr.length; outer ++)
        {
            temp = arr[outer];
            for (int inner = outer; inner > 0; inner--)
            {
                if(arr[inner - 1] > temp)
                {
                    arr[inner] = arr[inner - 1];
                }
                else
                {
                    arr[inner] = temp;
                    break;
                }
                if(inner == 1){
                    arr[0] = temp;
                }
            }
        }
    }
    private static void selectSort()
    {
        int var;
        int min;
        for(int i = 0; i < arr.length; i++)
        {
            var = i;
            min = arr[i];
            for (int j = i; j < arr.length; j++)
            {
                if(arr[j] < min)
                {
                    min = arr[j];
                    var = j;
                }
            }
            arr[var] = arr[i];
            arr[i] = min;
        }
    }
    private static void startTime()
    {
        startTime = System.currentTimeMillis();
    }
    private static void endTime()
    {
        endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime - startTime) / 1000 + " sec");
    }
}
