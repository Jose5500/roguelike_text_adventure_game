package com.example.company;

import java.util.InputMismatchException;
import java.util.Scanner;

public class StartUpScreen {
    Scanner scanner = new Scanner(System.in);

    public StartUpScreen() {
    }

    //The main screen shows up
    public void startUpScreen() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to a Roguelike Text Adventure Game" + "\n" +
                "Created by: Jose Lopez" + "\n" +
                "Press 1 to enter game" + "\n" +
                "Press 2 to view instructions" + "\n" +
                "Press 3 to view information about this project");
        int choice = 0;
        while(true) {
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
                if(choice == 1 || choice == 2 || choice == 3){
                    break;
                }else{
                    System.out.println("Not a correct number");
                }
            } catch (InputMismatchException e) {
                System.out.println("That's not a number\n" +
                        "Try again");
                scanner.next();
            }
        }
        switch (choice) {
            case 1:
                System.out.println("Starting game" + "\n" +
                        "Enjoy (~˘▾˘)~");
                pressEnter();
                for (int i = 0; i < 30; i++) {
                    System.out.println();
                }
                //Makes it clear where the next set of text will appear
                System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                break;
            case 2:
                viewInstructions();
                break;
            case 3:
                viewInformationAboutProject();
                break;
                default:
        }
    }

    //Gives basics details about game
    public void viewInstructions() {
        System.out.println("In this game, you will find yourself trapped in a endless cave" + "\n" +
                "In order to navigate throughout it, commands are as follow:" + "\n" +
                "N:North" + "\n" +
                "E:East" + "\n" +
                "S:South" + "\n" +
                "W:West" + "\n" +
                "Note: if you move North, then move South, you can't move directly back North" + "\n" +
                "In order to attack, commands will be given when the scenario occurs" + "\n" +
                "Press 1 to return back to the start up screen");
        goBack();
    }

    //Gives basic information about me and the project
    public void viewInformationAboutProject() {
        System.out.println("This project was created during my Junior year of high school because I was bored :^|" + "\n" +
                "Press 1 to go back to the start up menu");
        goBack();
    }

    //prompts the press enter message
    private void pressEnter() {
        System.out.println("Press enter to continue");
        while (true) {
            if (scanner.nextLine().isEmpty()) {
                System.out.println();
                break;
            } else {
                System.out.println("That is not the right key");
                System.out.println();
                System.out.println("Press enter to continue");
            }
        }
    }

    private void goBack() {
        try {
            if (scanner.nextInt() == 1) {
                scanner.nextLine();
                startUpScreen();
            } else {
                System.out.println("That's not the number one :^|" + "\n" +
                        "This is how the number one looks");
                System.out.println("1111\n" +
                        "  11\n" +
                        "  11\n" +
                        "  11\n" +
                        "111111");
                System.out.println("Try again");
                goBack();
            }
        } catch (InputMismatchException e) {
            System.out.println("That's not even a number :|\n" +
                    "Try again");
            scanner.next();
            goBack();
        }
    }
}
