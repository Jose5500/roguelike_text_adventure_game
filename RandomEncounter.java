package com.example.company;


import java.util.*;

public class RandomEncounter {
    Random random = new Random();
    Player player = new Player();
    Scanner scanner = new Scanner(System.in);
    private int event;
    public static int floorNum = 1;
    ArrayList<String> previousLocation = new ArrayList<>();
    private final int WINNING_ROUND = 15;

    public RandomEncounter() {
        previousLocation.add(null);
    }

    //Takes user input and moves accordingly/displays information
    public void startMoving() {
        //the while loop will stop one level before the winning round, as the level prior to winning is the boss stage
        while (floorNum < WINNING_ROUND - 1) {
            String newLocation = scanner.nextLine();
            if (!newLocation.equalsIgnoreCase("q") &&
                    newLocation.equalsIgnoreCase("north") ||
                    newLocation.equalsIgnoreCase("east") ||
                    newLocation.equalsIgnoreCase("south") ||
                    newLocation.equalsIgnoreCase("west") ||
                    newLocation.equalsIgnoreCase("data") ||
                    newLocation.equalsIgnoreCase("info")) {
                switch (newLocation) {

                    case ("north"):
                        floorNum++;
                        System.out.println("You have moved North");
                        createEncounter();
                        break;
                    case ("east"):
                        floorNum++;
                        System.out.println("You have moved East");
                        createEncounter();
                        break;
                    case ("south"):
                        floorNum++;
                        System.out.println("You have moved South");
                        createEncounter();
                        break;
                    case ("west"):
                        floorNum++;
                        System.out.println("You have moved West");
                        createEncounter();
                        break;
                    case ("data"):
                        player.printData();
                        Game.printInstructions();
                        break;
                    case ("info"):
                        Game.printInstructions();
                        break;
                }
            } else {
                System.out.println("That's not a valid command, please try again.");
            }
        }
        //at the 14th floor, they encounter the boss
        encounterBoss();
        //at this point, they have reached the winning round and have won the game
        createSpace();
        System.out.println("Congratulations you have reached the 15th round and won!\n" +
                "You reunited with your parents and lived happily for the rest of your life. The End.\n" +
                "(Here's your victory dance)");
        pressEnter();
        //doesn't let the user do anything after this point, only prints out the figure dancing with his taco
        while (true) {
            try {
                Thread.sleep(750L);
                System.out.println(
                        "\uD83C\uDF2E\n" +
                                "\\(._.) \n" +
                                " (  (>\n " +
                                " /   \\");
                System.out.println();
                Thread.sleep(1000L);
                System.out.println(
                        "       \uD83C\uDF2E\n" +
                                "(._.)/ \n" +
                                "<)  )\n " +
                                " /  \\");
                System.out.println();
            } catch (InterruptedException e) {
                System.out.println("Got Interrupted");
            }
        }
    }

    //occurs one floor before winning the game, encounters the boss
    public void encounterBoss() {
        createSpace();
        System.out.println("One more round left, you're almost there");
        pressEnter();
        System.out.println("You feel a strong presence as you move onto the next room");
        pressEnter();
        System.out.println("You turn around, and there he is");
        pressEnter();
        createSpace();
        Fighting fight = new Fighting("Vampire", 100, 10);
        fight.startFight();
    }

    public void createEncounter() {
        //goes from 1-100
        event = random.nextInt(99) + 1;
        //40% chance
        if (event <= 40) {
            createSpace();
            System.out.println("You have encountered a monster");
            pressEnter();
            createSpace();
            Fighting fight = new Fighting();
            fight.startFight();
            createSpace();
            Game.printInstructions();
        }
        //10% chance
        if (event > 40 && event <= 50) {
            treasureChestEncounter();
        }
        //15% chance
        if (event > 50 && event <= 65) {
            createSpace();
            drawFairy();
            System.out.println("You have stumbled across a fountain \n" +
                    "It will heal your health back to full");
            player.setHealth(100);
            pressEnter();
            createSpace();
            Game.printInstructions();
            startMoving();
        }
        //15% chance
        if (event > 65 && event <= 80) {
            oldManEncounter();
        }
        //10% chance
        if (event > 80 && event <= 90) {
            createSpace();
            drawBonfire();
            System.out.println("There is nothing in this room, you decide to catch your breath");
            pressEnter();
            createSpace();
            Game.printInstructions();
            startMoving();
        }
        //9% chance
        if (event > 90 && event <= 99) {
            weaponLyingOnFloorEncounter();
        }
        //1% chance
        if (event >= 100) {
            arnoldEncounter();
        }
    }

    private void treasureChestEncounter() {
        drawTreasureChest();
        System.out.println("You have encountered a treasure chest \n" +
                "Would you like to open it\n " +
                "(yes/no)");
        String choice = yesNoCase();
        if (choice.equals("yes")) {
            System.out.println("You have found an item");
            int secondChoice = random.nextInt(2) + 1;
            if (secondChoice == 1) {
                String weaponName = getRandomWeapon();
                int randomAttack = random.nextInt(15) + 1;
                System.out.println("You found: " + weaponName + "\n" +
                        "Attack:" + randomAttack);
                pressEnter();
                System.out.println("Would you like to equip it?\n" +
                        "(yes/no)");
                String thirdChoice = yesNoCase();
                if (thirdChoice.equals("yes")) {
                    player.setWeapon(weaponName);
                    player.setAttack(randomAttack);
                    System.out.println("You have equipped the " + weaponName);
                    System.out.println("New attack = " + randomAttack);
                } else {
                    System.out.println("You decide to walk past the weapon, leaving its powerful glory to be found by someone else.");
                }
            } else {
                //gets a random item from the item list, and splits it into two parts, the name and the definition
                String splitter = getRandomItem();
                String[] splitItem = splitter.split(":");
                System.out.println("You found a " + splitter);
                pressEnter();
                System.out.println("Would you like to add it to your inventory?\n" +
                        "(yes/no)");
                String thirdChoice = yesNoCase();
                if (thirdChoice.equals("yes")) {
                    player.addItem(splitItem[0], splitItem[1]);
                    System.out.println(splitItem[0] + " has been added to your inventory");
                } else {
                    System.out.println("You decide not to add the item to your inventory");
                }
            }
        } else {
            System.out.println("Although not the brightest idea, you decide to skip the treasure");
        }
        pressEnter();
        createSpace();
        Game.printInstructions();
        startMoving();
    }

    //creates the scenario when the old man is encountered
    public void oldManEncounter() {
        createSpace();
        drawOldMan();
        System.out.println("You see an old man sitting alone in a dark corner \n" +
                "Would you like to approach him?(yes/no)");
        String decision = yesNoCase();
        if (decision.equals("yes")) {
            System.out.println("As you get closer, he greets you\n" +
                    "He presents you with three items, but he only allows you to take one");
            //creates details for the second random weapon
            System.out.println("Item One\n" +
                    "-----------------");
            String weaponOne = getRandomWeapon();
            int firstRandomAttack = random.nextInt(15) + 1;
            System.out.println(weaponOne + "\n" +
                    "Attack:" + firstRandomAttack);
            System.out.println();
            //creates details for a random item
            System.out.println("Item Two\n" +
                    "-----------------");
            String splitter = getRandomItem();
            String[] splitItem = splitter.split(":");
            System.out.println(splitter);
            System.out.println();
            //creates details for the second random weapon
            System.out.println("Item Three\n" +
                    "-----------------");
            String weaponTwo = getRandomWeapon();
            int secondRandomAttack = random.nextInt(15) + 1;
            System.out.println(weaponTwo + "\n" +
                    "Attack:" + secondRandomAttack);
            pressEnter();
            System.out.println("Which item would you like to take/equip?\n" +
                    "(1,2,3,or 4 for none)");
            while(true) {
                try {
                    int secondDecision = scanner.nextInt();
                    scanner.nextLine();
                    //user chooses the first choice
                    if (secondDecision == 1) {
                        player.setWeapon(weaponOne);
                        player.setAttack(firstRandomAttack);
                        System.out.println("You have equipped the " + weaponOne);
                        System.out.println("New attack = " + firstRandomAttack);
                        break;
                    }
                    //user chooses the second choice
                    else if (secondDecision == 2) {
                        player.addItem(splitItem[0], splitItem[1]);
                        System.out.println(splitItem[0] + " has been added to your inventory");
                        System.out.println("Added " + splitItem[0] + " to inventory");
                        break;
                    }
                    //user chooses the third choice
                    else if (secondDecision == 3) {
                        player.setWeapon(weaponTwo);
                        player.setAttack(secondRandomAttack);
                        System.out.println("You have equipped the " + weaponTwo);
                        System.out.println("New attack = " + secondRandomAttack);
                        break;
                    }
                    //user decides they don't want any of the items
                    else if (secondDecision == 4) {
                        System.out.println("You decide you don't want any of his items and move on");
                        break;
                    }else{
                        System.out.println("Not a correct number \n" +
                                "Try again");
                        scanner.next();
                    }
                }catch (InputMismatchException e){
                    System.out.println("Not a number \n " +
                            "Try again");
                    scanner.nextLine();
                }
            }
        } else {
            System.out.println("You decide to leave him alone");
        }
        pressEnter();
        createSpace();
        Game.printInstructions();
        startMoving();
    }

    private void weaponLyingOnFloorEncounter() {
        createSpace();
        drawSwordInStone();
        System.out.println("There is a weapon lying on the floor. \n" +
                "Would you like to investigate it?(yes/no)");
        String decision = yesNoCase();
        if (decision.equals("yes")) {
            String weaponName = getRandomWeapon();
            int randomAttack = random.nextInt(15) + 1;
            System.out.println("You found: " + weaponName + "\n" +
                    "Attack:" + randomAttack);
            pressEnter();
            System.out.println("Would you like to equip it?\n" +
                    "(yes/no)");
            String secondChoice = yesNoCase();
            if (secondChoice.equals("yes")) {
                player.setWeapon(weaponName);
                player.setAttack(randomAttack);
                System.out.println("You have equipped the " + weaponName);
                System.out.println("New attack = " + randomAttack);
            } else {
                System.out.println("You decide to walk past the weapon, leaving its powerful glory to be found by someone else.");
            }
        } else {
            System.out.println("You decide to walk past the weapon, leaving its powerful glory to be found by someone else.");
        }
        pressEnter();
        createSpace();
        Game.printInstructions();
        startMoving();
    }

    private void arnoldEncounter() {
        System.out.println("You see a man pacing around in the middle of the room \n" +
                "He seems to be in a panic to reach his 'chopah' \n" +
                "Would you like to speak to him?(yes/no)");
        while (true) {
            String decision = yesNoCase();
            if (decision.equals("yes")) {
                System.out.println("As you approach this man, he sprints off in a fury\n" +
                        "However, you are able to catch a glimpse of his face");
                pressEnter();
                drawArnold();
                pressEnter();
                System.out.println("You look around and notice he left his gun lying around \n" +
                        "Would you like to take a closer look at it?(yes/no)");
                String secondDecision = yesNoCase();
                if (secondDecision.equals("yes")) {
                    System.out.println("You feel a immense power surging from it as you grab it");
                    equipWeapon("ARNOLD'S KILLING MACHINE", 9999);
                    break;
                } else {
                    System.out.println("You decide to walk past the gun\n" +
                            "As you walk away from the gun, you feel a strong aura emitting from it");
                    break;
                }
            } else {
                System.out.println("You decide to not bother the man\n" +
                        "As slowly back away, you see him kill three minotaurs with one shot of his gun");
                break;
            }
        }
        pressEnter();
        createSpace();
        Game.printInstructions();
        startMoving();
    }

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

    private String getRandomItem() {
        //gets a random item from itemList
        int randomItemIndex = random.nextInt(player.getItemNames().length);
        return (player.getItemNames()[randomItemIndex] + ":" + player.getItemDescriptions()[randomItemIndex]);
    }

    private String getRandomWeapon() {
        //gets a random weapon from weapon list
        int randomWeapon = random.nextInt(player.getWeaponList().size());
        String weaponName = player.getWeaponList(randomWeapon);
        return (weaponName);
    }

    //this method will just either get a yes or no command from the user
    //and will prompt them to try again if yes/no isn't entered
    public static String yesNoCase() {
        while (true) {
            //implemented its own scanner so the method could be static
            Scanner scanner = new Scanner(System.in);
            String decision = scanner.nextLine();
            if (decision.equalsIgnoreCase("yes")) {
                return ("yes");
            } else if (decision.equalsIgnoreCase("no")) {
                return ("no");
            } else {
                System.out.println("Not a valid command\n" +
                        "Please try again(yes/no)");
            }
        }
    }

    private void equipWeapon(String weapon, int attack) {
        System.out.println("Weapon:" + weapon + "\n" +
                "Attack:" + attack + "\n" +
                "Previous weapons:" + player.getWeapon() + "\n" +
                "-----------------------" + "\n" +
                "Previous weapons attack:" + player.getAttack() + "\n" +
                "Would you like to equip it? (yes/no)");
        String weaponDecision = yesNoCase();
        if (weaponDecision.equals("yes")) {
            player.setWeapon(weapon);
            player.setAttack(attack);
            System.out.println("Equipped:" + weapon);
            System.out.println("New attack:" + attack);
        }
    }

    private void createSpace() {
        for (int i = 0; i < 30; i++) {
            System.out.println();
        }
    }

    private void drawOldMan() {
        System.out.println("" +
                "                            |||||\n" +
                "                           | o o |\n" +
                "                           |  >  |\n" +
                "                           | \\_/ |\n" +
                "                            \\___/\n" +
                "                           __| |__\n" +
                "                          /       \\\n" +
                "                         | |     | |\n" +
                "        _________________| |     | |_____________---__\n" +
                "       /                 | |_____| |         /  /  / /|\n" +
                "      /                  /_|  _  |_\\        /  /  / / |\n" +
                "     /                    / / / /          /  /__/ / /|\n" +
                "    /____________________/ / / /__________/___\\_/_/ / |\n" +
                "    |____________________| |_| |__________________|/  |\n" +
                "    |____________________| |_| |__________________|   /\n" +
                "____|              |     | | | | ||               |  /\n" +
                "    | o          o | o         o || o           o | /\n" +
                "    |______________|_____________||_______________|/\n" +
                "_______________________________________________________");
    }

    private void drawSwordInStone() {
        System.out.println("   " +
                "            ()\n" +
                "            )(\n" +
                "         o======o\n" +
                "            ||    \n" +
                "            ||\n" +
                "            ||\n" +
                "       ,c88888888b\n" +
                "      ,88888888888b\n" +
                "      88888888888Y\"\n" +
                ",,;,,;;\"Y888888Y\",,,,,,,;;,;");
    }

    private void drawArnold() {
        System.out.println("\n" +
                "                   <((((((\\\\\\\n" +
                "                   /      . }\\\n" +
                "                   ;--..--._|}\n" +
                "(\\                 '--/\\--'  )\n" +
                " \\\\                | '-'  :'|\n" +
                "  \\\\               . -==- .-|\n" +
                "   \\\\               \\.__.'   \\--._\n" +
                "   [\\\\          __.--|       //  _/'--.\n" +
                "   \\ \\\\       .'-._ ('-----'/ __/      \\\n" +
                "    \\ \\\\     /   __>|      | '--.       |\n" +
                "     \\ \\\\   |   \\   |     /    /       /\n" +
                "      \\ '\\ /     \\  |     |  _/       /\n" +
                "       \\  \\       \\ |     | /        /\n" +
                "        \\  \\      \\        /");
    }

    private void drawTreasureChest() {
        System.out.println("*******************************************************************************\n" +
                "          |                   |                  |                     |\n" +
                " _________|________________.=\"\"_;=.______________|_____________________|_______\n" +
                "|                   |  ,-\"_,=\"\"     `\"=.|                  |\n" +
                "|___________________|__\"=._o`\"-._        `\"=.______________|___________________\n" +
                "          |                `\"=._o`\"=._      _`\"=._                     |\n" +
                " _________|_____________________:=._o \"=._.\"_.-=\"'\"=.__________________|_______\n" +
                "|                   |    __.--\" , ; `\"=._o.\" ,-\"\"\"-._ \".   |\n" +
                "|___________________|_._\"  ,. .` ` `` ,  `\"-._\"-._   \". '__|___________________\n" +
                "          |           |o`\"=._` , \"` `; .\". ,  \"-._\"-._; ;              |\n" +
                " _________|___________| ;`-.o`\"=._; .\" ` '`.\"\\` . \"-._ /_______________|_______\n" +
                "|                   | |o;    `\"-.o`\"=._``  '` \" ,__.--o;   |\n" +
                "|___________________|_| ;     (#) `-.o `\"=.`_.--\"_o.-; ;___|___________________\n" +
                "____/______/______/___|o;._    \"      `\".o|o_.--\"    ;o;____/______/______/____\n" +
                "/______/______/______/_\"=._o--._        ; | ;        ; ;/______/______/______/_\n" +
                "____/______/______/______/__\"=._o--._   ;o|o;     _._;o;____/______/______/____\n" +
                "/______/______/______/______/____\"=._o._; | ;_.--\"o.--\"_/______/______/______/_\n" +
                "____/______/______/______/______/_____\"=.o|o_.--\"\"___/______/______/______/____\n" +
                "/______/______/______/______/______/______/______/______/______/______/[TomekK]\n" +
                "*******************************************************************************");
    }

    private void drawFairy() {
        System.out.println("  _   vvvvvvvvv   _\n" +
                "  ( `-._\\...../_.-' )\n" +
                "   \\   ((('_')))   /\n" +
                "    )   ))) (((   (\n" +
                "   (   ((( v )))   )\n" +
                "    )`--' )X( `-._(\n" +
                "   /   _./   \\._   \\\n" +
                "  /  .' /     \\ `.  \\\n" +
                " (__/  /       \\  \\__)\n" +
                "      /         \\\n" +
                "     /           \\\n" +
                "    WwWwWwWwWwWwWwW");
    }

    private void drawBonfire() {
        System.out.println("" +
                "        ______\n" +
                "       /     /\\\n" +
                "      /     /  \\\n" +
                "     /_____/----\\_    (  \n" +
                "    \"     \"          ).  \n" +
                "   _ ___          o (:') o   \n" +
                "  (@))_))        o ~/~~\\~ o   \n" +
                "                  o  o  o");
    }
}
