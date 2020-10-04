package ru.geekbrains.lesson3;
import java.util.Scanner;

public class Lesson3 {
    private static final int GUESS_NUMBER = 1;
    private static final int GUESS_FOOD = 2;
    public static void main (String[] args){
        /*1. Написать программу, которая загадывает случайное число от 0 до 9,
        и пользователю дается 3 попытки угадать это число.
        При каждой попытке компьютер должен сообщить больше ли указанное пользователем число чем загаданное, или меньше.
        После победы или проигрыша выводится запрос – «Повторить игру еще раз? 1 – да / 0 – нет»(1 – повторить, 0 – нет).

        2 * Создать массив из слов
        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot",
        "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea",
        "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
        При запуске программы компьютер загадывает слово, запрашивает ответ у пользователя,
        сравнивает его с загаданным словом и сообщает правильно ли ответил пользователь.
        Если слово не угадано, компьютер показывает буквы которые стоят на своих местах.
        apple – загаданное
        apricot - ответ игрока
        ap############# (15 символов, чтобы пользователь не мог узнать длину слова)
        Для сравнения двух слов посимвольно, можно пользоваться:
        String str = "apple";
        str.charAt(0); - метод, вернет char, который стоит в слове str на первой позиции
        Играем до тех пор, пока игрок не отгадает слово
        Используем только маленькие буквы
        */
        System.out.println("Hi!\nType 1 if you want to guess hidden number\nType 2 if you want to guess hidden food");
        boolean isAnswerCorrect = false;

        while (!isAnswerCorrect){
            Scanner scanner = new Scanner(System.in);
            if(scanner.hasNextInt()) {
                int answer = scanner.nextInt();
                if(answer > 2 || answer < 1){
                    System.out.println("Type only 1 or 2");
                }
                else if(answer == 1) {
                    firstTask();
                    isAnswerCorrect = true;
                }
                else{
                    secondTask();
                    isAnswerCorrect = true;
                }
            }
            else{
                System.out.println("Type only 0 or 1");
            }
        }
    }
    static void firstTask(){
        int hiddenNumber = (int)(Math.random() * 11);
        int attempts = 3;
        boolean isUserWin = false;
        boolean isUserWantToPlay = true;
        while (isUserWantToPlay ) {
            System.out.println("Try to guess a number from 0 to 10\nYou have three attempts");
            while (!isUserWin && attempts !=0) {
                isUserWin = isUserGuess(hiddenNumber);
                if (!isUserWin){
                    attempts--;
                    showAttempts(attempts, hiddenNumber);
                }
            }
            isUserWantToPlay = isUserWantToContinue();
            if (isUserWantToPlay){
                hiddenNumber = (int)(Math.random() * 11);
                isUserWin = false;
                attempts = 3;
            }
        }
        offerToPlayTo(GUESS_FOOD);
    }
    static boolean isUserGuess(int hiddenNumber){
        boolean isUserRight = false;
        Scanner scanner = new Scanner(System.in);
        int inputNumber;
        if(scanner.hasNextInt()){
            inputNumber = scanner.nextInt();
            if(inputNumber < 0 || inputNumber > 10){
                System.out.println("Type only number between 0 and 10");
            }
            else if(inputNumber < hiddenNumber){
                System.out.println("Hidden number is more");
            }
            else if(inputNumber > hiddenNumber){
                System.out.println("Hidden number is less");
            }
            else{
                System.out.println("\nYou are right!");
                isUserRight = true;
            }
        }
        else{
            System.out.println("Type only number between 0 and 10");
        }
        return isUserRight;
    }
    static void showAttempts(int attempts, int hiddenNumber){
        switch (attempts){
            case 0:
                System.out.println("Sorry, but you lose\nHidden number is " + hiddenNumber);
                break;
            case 1:
                System.out.println("It's your last attempt");
                break;
            default:
                System.out.println("You have two attempts");
        }
    }
    static boolean isUserWantToContinue(){
        boolean isUserWantToContinue = true;
        boolean isAnswerCorrect = false;
        int answer;
        System.out.println("\nDo you want to play again?\n1 - yes   0 - no");
        while (!isAnswerCorrect){
            Scanner scan = new Scanner(System.in);
            if(scan.hasNextInt()){
                answer = scan.nextInt();
                if(answer > 1 || answer < 0){
                    System.out.println("Type only 0 or 1\n1 means that you want to play again\n0 means you want to exit");
                }
                else if(answer == 1){
                    isAnswerCorrect = true;
                    System.out.println("\nOK!\n");
                }
                else {
                    isUserWantToContinue = false;
                    isAnswerCorrect = true;
                }
            }
            else{
                System.out.println("Type only 0 or 1\n1 means that you want to play again\n0 means you want to exit");
            }
        }
        return isUserWantToContinue;
    }

    static void secondTask(){
        boolean isUserWin = false;
        boolean isUserWantToPlay = true;
        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot",
                "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive",
                "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
        int indexHiddenFood = (int) (Math.random() * words.length);
        String hiddenFood = words[indexHiddenFood];
        while (isUserWantToPlay){
            System.out.println("Try to guess hidden food");
            while (!isUserWin){
                isUserWin = isUserGuessFood(hiddenFood);
            }
            isUserWantToPlay = isUserWantToContinue();
            if (isUserWantToPlay){
                isUserWin = false;
                indexHiddenFood = (int) (Math.random() * words.length);
                hiddenFood = words[indexHiddenFood];
            }
        }
        offerToPlayTo(GUESS_NUMBER);
    }

    static boolean isUserGuessFood(String hiddenFood){
        boolean isUserRight = false;
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        if(answer.equals(hiddenFood)){
            System.out.println("\nYour are right!");
            isUserRight = true;
        }
        else{
            if(answer.length() > hiddenFood.length()){
                answer = answer.substring(0,hiddenFood.length());
            }
            else{
                for (int i = 0; i < hiddenFood.length() - answer.length(); i++){
                    answer +="#";
                }
            }
            System.out.println("Almost guessed\nHidden food is");
            System.out.print(comparingEqualLetters(hiddenFood,answer));
            addHashes(hiddenFood);
        }
        return isUserRight;
    }
    static String comparingEqualLetters(String hiddenFood, String answer){
        String equalPart = "";
        for(int letterIndex = 0; letterIndex < answer.length(); letterIndex++){
            if (answer.charAt(letterIndex) == hiddenFood.charAt(letterIndex)){
                equalPart += answer.charAt(letterIndex);
            }
            else {
                equalPart += "#";
            }
        }
        return equalPart;
    }
    static void addHashes(String hiddenFood){
        for(int i = 0; i < 15 - hiddenFood.length(); i++){
            System.out.print("#");
        }
        System.out.println();
    }

    static void offerToPlayTo(int game){
        String gameName;
        switch (game){
            case GUESS_NUMBER:
                gameName = "number";
                break;
            case GUESS_FOOD:
                gameName = "food";
                break;
            default:
                gameName = "";
        }
        System.out.println("Do you want to guess hidden " + gameName + "?\n1 - Yes    0 - No");
        boolean isAnswerCorrect = false;
        while (!isAnswerCorrect){
            Scanner scanner = new Scanner(System.in);
            if(scanner.hasNextInt()) {
                int answer = scanner.nextInt();
                if(answer > 1 || answer < 0){
                    System.out.println("Type only 0 or 1");
                }
                else if(answer == 1) {
                    System.out.println("OK!");
                    switch (game) {
                        case GUESS_NUMBER:
                            firstTask();
                            break;
                        case GUESS_FOOD:
                            secondTask();
                    }
                    isAnswerCorrect = true;
                }
                else{
                    System.out.println("By!");
                    isAnswerCorrect = true;
                }
            }
            else{
                System.out.println("Type only 0 or 1");
            }
        }

    }
}
