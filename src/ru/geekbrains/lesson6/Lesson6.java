package ru.geekbrains.lesson6;

import java.util.ArrayList;
import java.util.Scanner;

public class Lesson6 {
    public static void main(String[] args)
    {
        ArrayList<Animal> animals = new ArrayList<>();
        animals.add(new Dog("Charlie"));
        animals.add(new Dog("Oscar"));
        animals.add(new Cat("Leo"));
        animals.add(new Cat("Max"));

        int answer;
        do {
            printAnimals(animals);

            System.out.println
            (
                    "1 - Add dog\n" +
                    "2 - Add cat\n" +
                    "3 - Let the animals run\n" +
                    "4 - Let the animals swim\n" +
                    "0 - Exit"
            );
            answer = getIntegerOnlyBetween(0, 4);
            switch (answer)
            {
                case 1:
                    addDog(animals);
                    break;
                case 2:
                    addCat(animals);
                    break;
                case 3:
                    letToRun(animals);
                    break;
                case 4:
                    letToSwim(animals);
                    break;
            }
        }
        while (answer !=0);
    }
    static int getIntegerFromConsole()
    {
        do {
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            }
            System.out.println("Type only integer");
        }
        while (true);
    }
    static int getIntegerOnlyBetween(int from, int to)
    {
        int inputNumber;
        do {
            inputNumber = getIntegerFromConsole();
            if(inputNumber >= from && inputNumber <= to)
            {
                return inputNumber;
            }
            System.out.println("Type inger only between " + from + " and " + to);
        }
        while (true);
    }
    static void addDog(ArrayList<Animal> animals)
    {
        Dog dog = new Dog();
        dog.setName();
        animals.add(dog);
        System.out.println("The dog " + dog.getName() + " is added\n");
    }
    static void addCat(ArrayList<Animal> animals)
    {
        Cat cat = new Cat();
        cat.setName();
        animals.add(cat);
        System.out.println("The cat " + cat.getName() + " is added\n");
    }
    static void printAnimals(ArrayList<Animal> animals)
    {
        if(animals.size() == 0){
            System.out.println("\nYou haven't animals");
        }
        else{
            System.out.println("\nYou have: ");
            for(Animal animal:animals)
            {
                if (animal instanceof Cat)
                {
                    System.out.print("Cat ");
                }
                else
                {
                    System.out.print("Dog ");
                }
                System.out.println(animal.getName());
            }
        }
        System.out.println();
    }
    static void letToRun(ArrayList<Animal> animals)
    {
        for(Animal animal:animals)
        {
            animal.run();
        }
    }
    static void letToSwim(ArrayList<Animal> animals)
    {
        for(int i = 0; i < animals.size(); i++)
        {
            animals.get(i).swim();
            if( animals.get(i) instanceof Cat)
            {
                animals.remove(animals.get(i));
                i--;
            }
        }
    }

}
