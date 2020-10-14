package ru.geekbrains.lesson6;

public class Cat extends Animal {
    Cat()
    {
        super(200, 0,2000);
        setMaxSwimDistance(0);
    }
    Cat(String name)
    {
        super(200, 0,2000);
        setMaxSwimDistance(0);
        setName(name);
    }

}
