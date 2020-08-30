package com.example.company;

import javax.print.attribute.IntegerSyntax;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Game{
    Scanner scanner = new Scanner(System.in);
    Player player = new Player();
    RandomEncounter encounter = new RandomEncounter();
    public Game(){
    }

    //Gets information about the player and instantiates it in the player class
    public void getInformation() {
        System.out.println("Before starting, please input your name, age, and gender");
        System.out.println("Name:");
        String name = scanner.nextLine();
        player.setName(name);
        getAge();
        scanner.nextLine();
        System.out.println("Gender (Female or Male):");
        while(true) {
            String gender = scanner.nextLine();
            if (gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("female")) {
                player.setGender(gender);
                break;
            }else{
                System.out.println("That's not a valid gender, please try again");
                System.out.println("Gender:");
            }
        }
        //New page
        for (int i = 0; i < 30; i++) {
            System.out.println("");
        }
        //Makes it clear where the next line of text will come
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    public void meetingFamily() {
        System.out.println("Hello " + player.getName() + "\n" +
                "You are a " + player.getAge() + " year old " + player.getGender() + " living the perfect life" + "\n" +
                "You have a mother, father, two sisters, and a brother" + "\n" +
                "Here are your parents");
        printParents();
        System.out.println("Would you like to speak to them?(Yes/No)");
        while (true) {
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("yes")) {
                System.out.println("After the long conversation, they give you a taco to eat before you head off to school" + "\n" +
                        "You decide to catch up with your brother and sisters as they're actually responsible and were on time");
                break;
            } else if (choice.equalsIgnoreCase("no")) {
                System.out.println("That's too bad, you'll regret ignoring them");
                System.out.println("You wait by the door staring at the window, taco still in your right hand, while waiting for your brother and sisters to finish speaking to your parents" + "\n" +
                        "Because they're actually good children");
                break;
            } else {
                System.out.println("That's not an option, please try again");
            }
        }
        pressEnter();
    }
    //This runs right after the conversation with your parents
    public void everythingBeforeEnteringCave() {
        System.out.println("As you start to walk with your brother and sisters up to the bus, taco still in your right hand......." + "\n" +
                "ᵧₒᵤ ₕₑₐᵣ ₐₙ ₑₓₚₗₒₛᵢₒₙ");
        pressEnter();
        //Prints bomb going up
        printFirstBomb();
        pressEnter();
        //Prints bomb going down
        printSecondBomb();
        pressEnter();
        //Prints mushroom cloud
        printExplosion();
        pressEnter();
        for(int i = 0; i < 30; i++){
            System.out.println();
        }
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("You are knocked unconscious, but later wake up in a unknown location, your taco, still gripped firmly in your right hand \n" +
                "You look around, and are startled when a old man in a bathrobe approaches you");
        pressEnter();
        printWizard();
        pressEnter();
        System.out.println("He informs you that the explosion destroyed your town, and sent you flying to a nearby town \n" +
                "It's a miracle you survived...... \n" +
                "However, your family all survived as well, and if you are to want to reunite with them again, you must venture into the cave which he brought you to \n" +
                "They will be found if you can reach the 15th floor \n" +
                "In order to assist you, he infuses your taco with magical powers");
        pressEnter();
        printInfusingTaco();
        pressEnter();
        System.out.println("Although you're scared, you know you must do everything possible to save your parents, you walk into the cave, frightened, but determined");
        pressEnter();
    }
    public void inTheCave(){
        //adds health potion to character inventory
        player.addItem("Health Potion","Heals you for 50% of your health");
        //adds all items into a list but not into character inventory
        player.printData();
        printInstructions();
        encounter.startMoving();
    }

    //gets the age from the user and sets it in the player class
    public void getAge(){
        System.out.println("Age:");
            try{
                int age = scanner.nextInt();
                player.setAge(age);
            }
            catch (InputMismatchException e){
                System.out.println("That's not correct, please try again");
                System.out.println();
                scanner.next();
                getAge();
            }
        }
    //Prints instructions on what to do once you enter cave
    public static void printInstructions(){
        System.out.println("------------------------------");
        System.out.println("Floor:" + RandomEncounter.floorNum + "\n" +
                "-------" + "\n" +
                "Type north to travel North \n" +
                "Type east to travel East \n" +
                "Type south to travel South \n" +
                "Type west to travel West \n" +
                "Type data to see character data \n" +
                "Type info to read these instructions again");
    }
    //Prints the parents
    private void printParents(){
        System.out.println("   -(|)-      /\\\\ \\\n" +
                "  /\\|||/\\    /     \\\n" +
                "  |-O_O-|    |-o-o-|\n" +
                "  d  ^  b    C  V  D        \n" +
                "  O\\-=-/O    | ___ |\n" +
                "    \\_/       \\___/\n" +
                "  __| |__   _/<|_|>\\_\n" +
                " /  \\_/  \\ / |/\\_/\\| \\\n" +
                "/  o   o  |    |\\|    |\n" +
                "|/ __o__ \\| |  |\\|  | |\n" +
                "|\\ o   o /| |  |\\|  | |\n" +
                "||)=====( \\ \\\\ |\\|  | |\n" +
                "|| o   o \\ (())\\_/__| |\n" +
                "((   o   |  |   |   |_|\n" +
                " | o   o |  |   Y   |))\\\n" +
                " |   o   |  |   |   | ||\n" +
                " | o   o |  |   |   | ||\n" +
                " |_______|  |   |   | ||\n" +
                "   |_|_|    |___|___| ||\n" +
                "   /X|X\\    /qp| |qp\\ ||\n" +
                "  (__|__)  (___/ \\___)||");
    }
    //Prints the bomb going up
    private void printFirstBomb(){
        System.out.println("  /\\           \n" +
                " /  \\    \n" +
                " |  |     \n" +
                " |  |     \n" +
                "/ == \\    \n" +
                "|/**\\|     ");
    }
    //Prints the bomb going down
    private void printSecondBomb(){
        System.out.println("|\\**/|\n" +
                "\\ == /\n" +
                " |  |\n" +
                " |  |\n" +
                " \\  /\n" +
                "  \\/");
    }
    //Prints the explosion
    private void printExplosion(){
        System.out.println("     _.-^^---....,,--       \n" +
                " _--                  --_  \n" +
                "<                        >)\n" +
                "|                         | \n" +
                " \\._                   _./  \n" +
                "    ```--. . , ; .--'''       \n" +
                "          | |   |             \n" +
                "       .-=||  | |=-.   \n" +
                "       `-=#$%&%$#=-'   \n" +
                "          | ;  :|     \n" +
                " _____.,-#%&$@%#&#~,._____");
    }
    private void printWizard(){
        System.out.println("              _,-'|\n" +
                "           ,-'._  |\n" +
                " .||,      |####\\ |\n" +
                "\\.`',/     \\####| |\n" +
                "= ,. =      |###| |\n" +
                "/ || \\    ,-'\\#/,'`.\n" +
                "  ||     ,'   `,,. `.\n" +
                "  ,|____,' , ,;' \\| |\n" +
                " (3|\\    _/|/'   _| |\n" +
                "  ||/,-''  | >-'' _,\\\\\n" +
                "  ||'      ==\\ ,-'  ,'\n" +
                "  ||       |  V \\ ,|\n" +
                "  ||       |    |` |\n" +
                "  ||       |    |   \\\n" +
                "  ||       |    \\    \\\n" +
                "  ||       |     |    \\\n" +
                "  ||       |      \\_,-'\n" +
                "  ||       |___,,--\")_\\\n" +
                "  ||         |_|   ccc/\n" +
                "  ||        ccc/\n" +
                "  ||               ");
    }
    //Prints mage infusing taco
    private void printInfusingTaco(){
        System.out.println("\n" +
                "                             /\\\n" +
                "                            /  \\\n" +
                "                           |    |\n" +
                "                         --:'''':--\n" +
                "                           :'_' :\n" +
                "                           _:\"\":\\___\n" +
                "            ' '      ____.' :::     '._\n" +
                "           . *=====<<=)           \\    :\n" +
                "            .  '      '-'-'\\_      /'._.'\n" +
                "                             \\====:_ \"\"\n" +
                "                            .'     \\\\\n" +
                "                           :       :\n" +
                "                          /   :    \\\n" +
                "                         :   .      '.\n" +
                "         ,. _            :  : :      :\n" +
                "      '-'    ).          :__:-:__.;--'\n" +
                "    (        '  )        '-'   '-'\n" +
                " ( -   .00.   - _\n" +
                "(    .'  _ )     )\n" +
                "'-  ()_.\\,\\,   -");
    }
    private void pressEnter() {
        System.out.println("Press enter to continue");
        while(true) {
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
}
