package ru.geekbrains.lesson7;

public class Plate {
    private int food;

    public Plate(int food)
    {
        this.food = food;
    }

    public void info()
    {
        System.out.println("plate: " + food);
    }

    public boolean isDecreaseFood(int val)
    {
        if(val <= this.food)
        {
            this.food -= val;
            return true;
        }
        return false;
    }

    public void addFood(int additionalFood)
    {
        this.food +=additionalFood;
        System.out.println("The food increased (" + additionalFood + ")\n");
    }
}
