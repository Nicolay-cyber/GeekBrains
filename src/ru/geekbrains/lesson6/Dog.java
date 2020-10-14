package ru.geekbrains.lesson6;

public class Dog extends Animal{
    Dog()
    {
        super(500, 10, 500);
    }
    Dog(String name)
    {
        super(500, 10, 500);
        this.setName(name);
    }
}
