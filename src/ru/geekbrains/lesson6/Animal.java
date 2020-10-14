package ru.geekbrains.lesson6;


public abstract class Animal
{
    private int maxRunDistance;
    private int maxSwimDistance;
    private int maxJumpHeight;
    private String name;
    Animal(int maxRunDistance, int maxSwimDistance, int maxJumpHeight)
    {
        this.maxSwimDistance = (int) (Math.random() * ((maxSwimDistance + 3) - (maxSwimDistance - 3) + 1) + (maxSwimDistance - 3));
        this.maxRunDistance = (int) (Math.random() * ((maxRunDistance + 100) - (maxRunDistance - 100) + 1) + (maxRunDistance - 100));
        this.maxJumpHeight = (int) (Math.random() * ((maxJumpHeight + 20) - (maxJumpHeight - 20) + 1) + (maxJumpHeight - 20));
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public void setMaxSwimDistance(int maxSwimDistance)
    {
        this.maxSwimDistance = maxSwimDistance;
    }
    public String getName()
    {
        return name;
    }
    public void run()
    {
        System.out.print("The " + name + " ran " + maxRunDistance +
                " metres and jumped over " + maxJumpHeight + "-cm fence");
    }
    public void swim()
    {
        if(maxSwimDistance == 0){
            System.out.println("The " + name + " drowned!");

        }
        else
        {
            System.out.print("The " + name + " swam " + maxSwimDistance + " meters");
        }
    }

    public int getMaxRunDistance() {
        return maxRunDistance;
    }

    public int getMaxSwimDistance() {
        return maxSwimDistance;
    }

    public int getMaxJumpHeight() {
        return maxJumpHeight;
    }
}

