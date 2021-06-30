package ru.geekbrains.lesson7;

public class Lesson7 {
    public static void main(String[] args)
    {
        Cat[] cats = new Cat[5];
        Plate plate = new Plate(10);

        fillArray(cats);

        plate.info();
        System.out.println();
        feed(cats, plate);
        plate.addFood(12);
        feed(cats, plate);
    }
    static void feed(Cat[] cats, Plate plate)
    {
        for (Cat cat:cats)
        {
            cat.eat(plate);
            cat.info();
            plate.info();
            System.out.println();
        }

    }
    static void fillArray(Cat[] cats)
    {
        cats[0] = new Cat("Max", 3);
        cats[1] = new Cat("Tom", 5);
        cats[2] = new Cat("Kitty", 7);
        cats[3] = new Cat("Luna", 4);
        cats[4] = new Cat("Zoe", 3);
    }

}
