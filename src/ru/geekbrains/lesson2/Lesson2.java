package ru.geekbrains.lesson2;

import java.util.Arrays;

public class Lesson2 {
    public static void main(String[] args) {
        /*
        # 1. Задать целочисленный массив, состоящий из элементов 0 и 1.
             Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ].
             С помощью цикла и условия заменить 0 на 1, 1 на 0;
         */
        printTaskNumber(1);
        int[] arr = new int[10];
        setRandomArrayIndex(arr,0,1);
        printArray(arr);
        invertArray(arr);
        printArray(arr);

        /*
        # 2. Задать пустой целочисленный массив размером 8.
             С помощью цикла заполнить его значениями 0 3 6 9 12 15 18 21;
        */
        printTaskNumber(2);
        arr = new int[8];
        printArray(arr);
        setArrayIndex(arr);
        printArray(arr);

        /*
        # 3. Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ]
             пройти по нему циклом, и числа меньшие 6 умножить на 2;
         */
        printTaskNumber(3);
        arr = new int[]{1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        printArray(arr);
        multiplyArrayIndex(arr);
        printArray(arr);

        /*
        # 4. Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое),
             и с помощью цикла(-ов) заполнить его диагональные элементы единицами;
         */
        printTaskNumber(4);
        int[][] array2D = new int[10][10];
        print2DArray(array2D);
        System.out.println();
        changeDiagonalArrayIndex(array2D);
        print2DArray(array2D);

        /*
        # 5. Задать одномерный массив и найти в нем минимальный и максимальный элементы (без помощи интернета);
         */

        printTaskNumber(5);
        setRandomArrayIndex(arr,0,100);
        printArray(arr);
        System.out.println("The minimal array index is " + MinArrayIndex(arr));
        System.out.println("The maximum array index is " + MaxArrayIndex(arr));

        /*
        # 6. Написать метод, в который передается не пустой одномерный целочисленный массив, метод должен вернуть true,
             если в массиве есть место, в котором сумма левой и правой части массива равны.
             Примеры: checkBalance([2, 2, 2, 1, 2, 2, || 10, 1]) → true,
             checkBalance([1, 1, 1, || 2, 1]) → true, граница показана символами ||, эти символы в массив не входят.
         */
        printTaskNumber(6);
        setRandomArrayIndex(arr,0,1);
        printArray(arr);
        findEqualPartsOfArray(arr);

        /*
        # 7. Написать метод, которому на вход подается одномерный массив и число n
        (может быть положительным, или отрицательным),
        при этом метод должен сместить все элементы массива на n позиций.
        Для усложнения задачи нельзя пользоваться вспомогательными массивами.
         */
        printTaskNumber(7);

        int interval = (int) (Math.random() * 11 - 5);
        setRandomArrayIndex(arr,1,9);
        System.out.println("Move indexes of the array");
        printArray(arr);
        System.out.print(Math.abs(interval) + " positions ");

        if (interval < 0){
            System.out.println("to the left");
            moveIndexes(arr, interval);
        }
        else if (interval > 0){
            System.out.println("to the right");
            moveIndexes(arr, interval);
        }
        printArray(arr);
    }

    static void printTaskNumber(int number) {
        System.out.println();
        System.out.println("Task # " + number);
    }


    static void printArray(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    static void invertArray(int[] arr) {
        for (int j = 0; j < arr.length; j++) {
            switch (arr[j]) {
                case 0:
                    arr[j] = 1;
                    break;
                case 1:
                    arr[j] = 0;
                    break;
            }
        }
    }

    static void setArrayIndex(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            arr[i] = arr[i - 1] + 3;
        }
    }

    static void multiplyArrayIndex(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 6) {
                arr[i] *= 2;
            }
        }
    }

    static void changeDiagonalArrayIndex(int[][] array2D) {
        for (int i = 0; i < array2D.length; i++) {
            for (int j = 0; j < array2D[i].length; j++) {
                if (i == j) {
                    array2D[i][j] = 1;
                }
            }
        }
    }

    static void print2DArray(int[][] array2D) {
        for (int i = 0; i < array2D.length; i++) {
            System.out.println(Arrays.toString(array2D[i]));
        }
    }

    static void setRandomArrayIndex(int[] arr, int from, int to) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((Math.random() * (to + 1 - from)) + from);
        }
    }

    static int MinArrayIndex(int[] arr) {
        int minArrayIndex = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < minArrayIndex) {
                minArrayIndex = arr[i];
            }
        }
        return minArrayIndex;
    }

    static int MaxArrayIndex(int[] arr) {
        int maxArrayIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > maxArrayIndex) {
                maxArrayIndex = arr[i];
            }
        }
        return maxArrayIndex;
    }

    static void findEqualPartsOfArray(int[] arr){

        int countBorder =0;
        for(int index = 0; index < arr.length; index++){
            if (hasEqualParts(arr,index)){
                countBorder++;
                if (countBorder > 1){
                    System.out.print("      also ");
                }
                printBorderOfEqualParts(arr, index, sumOfLeftPart(arr, index));
            }
        }
        if(countBorder == 0){
            System.out.println("this array doesn't have equal parts");
        }
    }

    static boolean hasEqualParts(int[] arr, int index){
        boolean hasEqualParts = false;
        if(sumOfLeftPart(arr, index) == sumOfRightPart(arr, index)){
            hasEqualParts = true;
        }
        return hasEqualParts;
    }

    static int sumOfLeftPart(int[] arr, int index){
        int leftSum = 0;
        for(int i = 0; i < index; i++){
            leftSum+=arr[i];
        }
        return leftSum;
    }

    static int sumOfRightPart(int[] arr, int index){
        int rightSum =0;
        for(int i = index; i < arr.length; i++){
            rightSum+=arr[i];
        }
        return rightSum;
    }

    static void printBorderOfEqualParts(int[] arr, int index, int sum){
        int[] leftPart = Arrays.copyOfRange(arr, 0, index);
        int[] rightPart = Arrays.copyOfRange(arr, index, arr.length);
        System.out.println("this array has equal parts with a sum of "+ sum);
        System.out.println("and their border is located here:");
        System.out.println(Arrays.toString(leftPart) + " " + Arrays.toString(rightPart));
    }

    static void moveIndexes(int[] arr, int shiftTo){
        int loseIndex;
        if(shiftTo > 0){
            for(int j = 0; j < shiftTo; j++){
                loseIndex = arr[arr.length - 1];
                for (int i = arr.length - 1; i > 0; i--){
                    arr[i] = arr[i - 1];
                }
                arr[0] = loseIndex;
            }
        }
        else if (shiftTo < 0){
            for (int j = 0; j < -shiftTo; j++){
                loseIndex = arr[0];
                for(int i = 0; i < arr.length - 1; i++){
                    arr[i] = arr[i + 1];
                }
                arr[arr.length - 1] = loseIndex;
            }
        }


    }
}


