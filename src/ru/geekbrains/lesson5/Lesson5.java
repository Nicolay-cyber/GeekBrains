package ru.geekbrains.lesson5;

import java.util.ArrayList;
import java.util.Scanner;

public class Lesson5 {
    public static void main(String[] args){
        int answer;
        ArrayList<Employee> employees = new ArrayList<>(5);
        createDefaultEmployees(employees);
        do {
            System.out.println("\n1 - Show all employees\n2 - Add new employee\n3 - Delete employee\n0 - Exit\n");
            answer = getNumberFromConsoleOnlyBetween(0,3);
            switch (answer){
                case 1:
                    showAllEmployees(employees);
                    break;
                case 2:
                    addNewEmployees(employees);
                    break;
                case 3:
                    deleteEmployee(employees);
            }
        }while (answer != 0);

    }
    static int getNumberFromConsoleOnlyBetween(int from, int to)
    {
        do{
            int inputNumber = getIntegerFromConsole();
            if (inputNumber >= from && inputNumber <= to){
                return inputNumber;
            }
            System.out.println("Type only integer between " + from + " and " + to);
        }
        while (true);
    }
    static int getIntegerFromConsole()
    {
        Scanner scanner = new Scanner(System.in);
        do
        {
            if(scanner.hasNextInt()){
                return scanner.nextInt();
            }
            System.out.println("Type only integer");
            scanner.nextLine();
        }
        while (true);
    }

    static void createDefaultEmployees( ArrayList<Employee> employees)
    {
        employees.add(new Employee("John Dorian", "Doctor", "JD@mail.com", "892312333", 30000, 27));
        employees.add(new Employee("Christopher Turk", "Surgeon", "Christopher@mail.com", "892312312", 50000, 27));
        employees.add(new Employee("Elliot Reid", "Physician", "Reid@mail.com", "892312444", 30000, 28));
        employees.add(new Employee("Perry Cox", "Attending physician", "Cox@mail.com", "892312552", 70000, 45));
        employees.add(new Employee("Cleaner", "Cleaner", "cleaner@mailbox.com", "855512312", 20000, 35));
        employees.add(new Employee("Somebody", "unknown", "???", "unknown", 0, 0));
    }
    static void showAllEmployees(ArrayList<Employee> employees)
    {
        for (int i = 0; i < employees.size(); i++){
            employees.get(i).getFullInfo();
        }
    }
    static void addNewEmployees(ArrayList<Employee> employees)
    {
        Employee employee = new Employee();

        System.out.println("Type full name");
        Scanner scanner = new Scanner(System.in);
        employee.setFullName(scanner.nextLine());

        System.out.println("Type position");
        scanner = new Scanner(System.in);
        employee.setPosition(scanner.nextLine());

        System.out.println("Type email");
        scanner = new Scanner(System.in);
        employee.setEmail(scanner.nextLine());

        System.out.println("Type phone number");
        scanner = new Scanner(System.in);
        employee.setPhoneNumber(scanner.nextLine());

        System.out.println("Type salary");
        employee.setSalary(getIntegerFromConsole());

        System.out.println("Type age");
        employee.setAge(getNumberFromConsoleOnlyBetween(18, 65));
        employees.add(employee);

        System.out.println("\nThe " + employee.getFullName() + " is added");

    }
    static void deleteEmployee (ArrayList<Employee> employees)
    {
        String answer;
        System.out.println("Type full name of employee\n 0 - Exit");
        do
        {
            Scanner scanner = new Scanner(System.in);
            answer = scanner.nextLine();
            if(getEmployeeByName(answer, employees) != null)
            {
                getEmployeeByName(answer,employees).getFullInfo();
                System.out.println("\nAre you sure that you want delete info about " +
                        getEmployeeByName(answer,employees).getFullName() + "?");
                System.out.println("1 - Yes\n0 - No");
                switch (getNumberFromConsoleOnlyBetween(0,1))
                {
                    case 1:
                        System.out.println("\n" + getEmployeeByName(answer,employees).getFullName() +
                                " is deleted");
                        employees.remove(getEmployeeByName(answer,employees));
                        answer = "0";
                        break;
                    case 0:
                        answer = "0";
                }
            }
        }
        while (!answer.equals("0"));

    }
    static Employee getEmployeeByName(String fullName, ArrayList<Employee> employees){
        for (Employee employee : employees) {
            if (fullName.equals(employee.getFullName())) {
                return employee;
            }
        }
        System.out.println("Employee is not found\n0 - Exit");
        return null;
    }
}
