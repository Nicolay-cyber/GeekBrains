package ru.geekbrains.lesson1;

import java.util.Scanner;

public class Lesson1 {
    public static void main(String[] args){

        //TASK # 2

        byte myByte = 0;
        short myShort = 1;
        int myInt = 2;
        long myLong = 3L;
        float myFloat = 4.56f;
        double myDouble = 7.89;
        char myChar = '\u263A';
        boolean myBoolean = true;
        String myString = "My string";

        // TASK # 3
        float  firstNumber = (float) Math.random();
        System.out.print("a = " + firstNumber);
        System.out.print("; b = " + (firstNumber + 2));
        System.out.print("; c = " + (firstNumber * 10));
        System.out.println("; d = " + (firstNumber / 2));
        System.out.print("The result of a * (b + (c / d)) equation is ");
        System.out.println(myEquation(firstNumber, firstNumber ++, firstNumber --,firstNumber * 2));

        // TASK # 4
        int  secondNumber = (int) (Math.random() * (20 + 1)) - 10;
        if(toCheckRange(secondNumber, secondNumber) == true){
            System.out.println("The sum of two  numbers " + secondNumber + "is in the range from 10 to 20");
        }
        else {
            System.out.println("The sum of two numbers " + secondNumber + " isn't in the range from 10 to 20");
        }

        // TASK # 5

        toCheckNumberSignAndPrint(secondNumber);

        // TASK # 6

        System.out.print("The " + secondNumber + " number is negative ? - ");
        System.out.println(toCheckNumberSign(secondNumber));

        // TASK # 7

        System.out.println("Type your name");
        Scanner scanner = new Scanner(System.in);
        String userName = scanner.nextLine();
        toSayHiTo(userName);

        // TASK # 8

        System.out.println("Type year number");
        scanner = new Scanner(System.in);
        int year = scanner.nextInt();
        toCheckYear(year);
        
    }



    public static float myEquation (float a, float b, float c, float d){
        return a * (b + (c / d));
    }

    public static boolean toCheckRange(int first, int second){
        boolean result = false;
        if (first + second >= 10 && first + second <= 20){
            result = true;
        }
        else{
            result = false;
        }
        return result;
    }

    public static void toCheckNumberSignAndPrint(int number){
        String result;
        if(number < 0){
            result = "The number " + number + " is negative";
        }
        else{
            result = "The number " + number + " is positive";
        }
        System.out.println(result);
    }

    public static boolean toCheckNumberSign(int number){
        boolean checking;
        if(number < 0){
            checking = true;
        }
        else{
            checking = false;
        }
        return  checking;
    }

    private static void toSayHiTo(String userName) {
        System.out.println("Hi, " + userName + " !");
    }

    public static void toCheckYear(int year) {
        if (year%4 == 0){
            if (year%100 == 0){
                if (year%400 == 0) System.out.println("This year is leap");
                else System.out.println("This year isn't leap");
            }
            else System.out.println("This year is leap");
        }
        else System.out.println("This year isn't leap");
    }
}
    