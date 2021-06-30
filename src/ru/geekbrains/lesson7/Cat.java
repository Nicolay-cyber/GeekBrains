package ru.geekbrains.lesson7;

public class Cat {
    private String name;
    private int appetite;
    private boolean isFull;

    public Cat(String name, int appetite)
    {
        this.name = name;
        this.appetite = appetite;
    }

    public void eat(Plate plate)
    {
        if(!isFull)
        {
            isFull = plate.isDecreaseFood(appetite);
        }
    }

    public void info()
    {
        System.out.println("Cat: " + name );
        System.out.println("Is full: " + isFull);
    }
}
