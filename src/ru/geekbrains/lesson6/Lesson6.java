package ru.geekbrains.lesson6;

import java.util.ArrayList;
import java.util.Scanner;

public class Lesson6 {
    public static void main(String[] args)
    {
        ArrayList<Animal> animals = new ArrayList<>();
        animals.add(new Dog("Bella"));
        animals.add(new Dog("Molly"));
        animals.add(new Cat("Leo"));
        animals.add(new Cat("Max"));
        printAnimals(animals);

        int answer;
        do {
            System.out.println
            (
                    "\n1 - Buy a dog\n" +
                    "2 - Buy a cat\n" +
                    "3 - Let the animals run\n" +
                    "4 - Let the animals swim\n" +
                    "5 - Try to run certain animal\n" +
                    "6 - Try to swim certain animal\n" +
                    "7 - Check the animals\n" +
                    "0 - Exit\n"
            );
            answer = getIntegerOnlyBetween(0, 7);
            switch (answer)
            {
                case 1:
                    addDog(animals);
                    break;
                case 2:
                    addCat(animals);
                    break;
                case 3:
                    if(animals.size() == 0){
                        printAnimals(animals);
                    }
                    else{
                        letToRun(animals);
                    }
                    break;
                case 4:
                    if(animals.size() == 0){
                        printAnimals(animals);
                    }
                    else {
                        letToSwim(animals);
                    }
                    break;
                case 5:
                    if(animals.size() == 0){
                        printAnimals(animals);
                    }
                    else {
                        tryToRun(animals);
                    }
                    break;
                case 6:
                    if(animals.size() == 0){
                        printAnimals(animals);
                    }
                    else {
                        trySwim(animals);
                    }
                    break;
                case 7:
                    printAnimals(animals);
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
        Animal dog = new Dog();
        dog.setName(checkName(animals));
        animals.add(dog);
        System.out.println("The dog " + dog.getName() + " is added\n");
    }
    static void addCat(ArrayList<Animal> animals)
    {
        Cat cat = new Cat();
        cat.setName(checkName(animals));
        animals.add(cat);
        System.out.println("The cat " + cat.getName() + " is added\n");
    }
    static void printAnimals(ArrayList<Animal> animals)
    {
        if(animals.size() == 0){
            System.out.println("\nYou haven't animals");
        }
        else{
            System.out.println("\nYou have:\n");
            for(Animal animal:animals)
            {
                System.out.print(animals.indexOf(animal) + 1);
                if (animal instanceof Cat)
                {
                    System.out.print(". Cat ");
                }
                else
                {
                    System.out.print(". Dog ");
                }
                System.out.println(animal.getName());
            }
        }
        System.out.println("\nType anything to show the menu\n");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
    static void letToRun(ArrayList<Animal> animals)
    {
        for(int i = 0; i < animals.size(); i++)
        {
            animals.get(i).run();
            if(!isComeBack(animals, animals.get(i)))
            {
                i--;
            }
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
            else
            {
                if(!isComeBack(animals, animals.get(i)))
                {
                    i--;
                }
            }
        }
    }
    static String checkName(ArrayList<Animal> animals)
    {
        System.out.println("\nType the name\n");
        do
        {
            Scanner scanner = new Scanner(System.in);
            String name = scanner.nextLine();
            if (animals.size() == 0)
            {
                return name;
            }
            int count = 0;
            for(Animal animal: animals)
            {
                if(!animal.getName().equals(name))
                {
                    count++;
                }
                else
                {
                    count = 0;
                    System.out.println("An animal with the same name already exists. Write another name");
                }

                if(count == animals.size())
                {
                    System.out.println();
                    return name;
                }
            }
        }
        while (true);

    }
    static Animal findAnimalByName(ArrayList<Animal> animals)
    {

        System.out.println("Type the animal's name");
        String name;
        do
        {
            Scanner scanner = new Scanner(System.in);
            name = scanner.nextLine();
            if(name.equals("0"))
            {
                break;
            }
            for(Animal animal:animals)
            {
                if(name.equals(animal.getName()))
                {
                    System.out.print("Animal is found.");
                    return animal;
                }
            }
            System.out.println("Animal is not found. Try again.\n(0 - Exit)");
        }
        while (true);
        return null;
    }
    static void tryToRun(ArrayList<Animal> animals)
    {
        Animal animal = findAnimalByName(animals);
        if (!(animal == null))
        {
            System.out.println("Type the distance in meters");
            int distance = getIntegerFromConsole();
            if(distance > animal.getMaxRunDistance())
            {
                System.out.print("The " + animal.getName() + " could run only " + animal.getMaxRunDistance() + " meters");
            }
            else
            {
                System.out.print("The " + animal.getName() + " ran " + distance + " meters");
            }
            isComeBack(animals, animal);
        }
    }
    static void trySwim(ArrayList<Animal> animals)
    {
        Animal animal = findAnimalByName(animals);
        if (!(animal == null))
        {

            System.out.println("Type the distance in meters");
            int distance = getIntegerFromConsole();
            if(animal instanceof Cat)
            {
                animal.swim();
                animals.remove(animal);
            }
            else if(distance > animal.getMaxRunDistance())
            {
                System.out.print("The " + animal.getName() + " could swam only "
                        + animal.getMaxRunDistance() + " meters." + " Swam ashore");
                isComeBack(animals, animal);
            }
            else
            {
                System.out.print("The " + animal.getName() + " swam " + distance + " meters. Swam ashore");
                isComeBack(animals, animal);
            }
        }

    }
    static boolean isComeBack(ArrayList<Animal> animals, Animal animal)
    {
        if((int) (Math.random() * 3) == 0)
        {
                System.out.println(" and ran away");
                animals.remove(animal);
                return false;
        }
        else{
            System.out.println(" and came back home");
            return true;
        }
    }


}
