package com.example.company;

public class Main {

    public static void main(String[] args) {
        //plays everything before the story starts
        StartUpScreen start = new StartUpScreen();
        start.startUpScreen();

        //starts the story, then starts the dungeon
        Game game = new Game();
        game.getInformation();
        game.meetingFamily();
        game.everythingBeforeEnteringCave();
        game.inTheCave();
    }
}
