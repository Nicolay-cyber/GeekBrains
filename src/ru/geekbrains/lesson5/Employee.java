package ru.geekbrains.lesson5;

public class Employee {
    private String fullName;
    private String position;
    private String email;
    private String phoneNumber;
    private int salary;
    private int age;

    public Employee(String fullName, String position,
                         String email, String phoneNumber,
                         int salary, int age)
    {
        this.email = email;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.position = position;
        this.salary = salary;
        this.age = age;
    }

    public Employee() {

    }

    public int getFullInfo() {
        System.out.println("\n" + fullName);
        System.out.println("\nPosition: " + position);
        System.out.println("Email: " + email);
        System.out.println("Phone number: " + phoneNumber);
        System.out.println("Salary " + salary);
        System.out.println("Age " + age);
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getFullName() {
        return fullName;
    }
}
