package com.example.company;


import java.util.InputMismatchException;
import java.util.Scanner;

public class Fighting {
    private Player player = new Player();
    private Monster monster;
    private Scanner scanner = new Scanner(System.in);
    //monster health which will go down when attacked/used items
    private int monsterHealth;
    //if they don't declare which monster they want to attack, they encounter a random monster
    public Fighting() {
        //will be used to create a random monster, if necessary
        Monster randomMonster = Monster.getRandomMonster();
        monster = new Monster(randomMonster.getName(),randomMonster.getHealth(),randomMonster.getAttack());
        monsterHealth = monster.getHealth();
    }
    //if they do declare a monster, it makes their fight be against that specific monster
    public Fighting(String name, int health, int attack){
    monster = new Monster(name,health,attack);
    monsterHealth = monster.getHealth();
    }
    public void startFight() {
        displayMonsterData();
        int decision;
        while (monsterHealth > 0 && player.getHealth() > 0) {
            displayBattleInfo();
            while(true) {
                try {
                    decision = scanner.nextInt();
                    break;
                }catch (InputMismatchException e){
                    System.out.println("Not a number");
                }
            }
            scanner.nextLine();
            if(decision == 1){
                attack();
                createSpace();
                displayMonsterData();
                pressEnter();
                displayCharacterData();
            }else if(decision ==2){
                String itemChosen = useItem();
                //if user uses escape scroll, flees without calling flee method
                if(itemChosen.equals("escape scroll")){
                    break;
                }
            }else if(decision == 3){
                boolean successful = flee();
                if(successful){
                    break;
                }
            }else{
                System.out.println("That is not a correct key, please try again");
            }
        }
        //runs after the fight, so if you either kill the monster or you die, fleeing skips these lines
        if(monsterHealth <= 0){
            System.out.println("＼(＾O＾)／");
            System.out.println("You have defeated the " + monster.getName() + "!");
            pressEnter();
        }else if(player.getHealth() <= 0){
            createSpace();
            drawGrave();
            System.out.println("You died");
            while(true){
                //infinite loop, doesn't let the player do anything once they die
            }
        }
    }

    private void attack() {
        System.out.println("You attack the " + monster.getName() + "\n" +
                "You deal " + player.getAttack() + " damage!");
        pressEnter();
        monsterHealth -= player.getAttack();
        //for when you kill the monster, he won't attack you back
        if (monsterHealth > 0) {
            System.out.println("The " + monster.getName() + " attacks you" + "\n" +
                    "He deals " + monster.getAttack() + " damage!");
            //subtracts the amount of damage the monster does from the player's health
            //if health will be negative after the attack, sets it to 0, or else subtracts damage from health
            if(player.getHealth() - monster.getAttack() < 0){
                player.setHealth(0);
            }else {
                player.setHealth(player.getHealth() - monster.getAttack());
            }
            pressEnter();
        }
    }
    private String useItem(){
        //wont run if there are no items to use
        if(monster.getName().equalsIgnoreCase("vampire")){
            System.out.println("Do you really think items work against me?");
            return("boss");
        }
        if(player.getItems().isEmpty()){
            System.out.println("You have no items in your inventory");
            return("empty");
        }
        //contains the index of the item the user wants to use
        int itemChoosen;
        createSpace();
        //gets items from player class and splits them at the "," so it splits the items separately
        String items = player.getItems().toString();
        //splits the items individually, with the name and definition
        String[] individualItems = items.split(",");
        //starts from 1 so it makes more sense
        for(int i = 0; i < individualItems.length; i++){
            System.out.println("[" + i + "]" + individualItems[i]);
            System.out.println();
        }
        pressEnter();
        while(true) {
            try {
                System.out.println("Enter the number corresponding to which item you would like to choose, or press " + individualItems.length + " to go back\n" +
                        "Number:");
                itemChoosen = scanner.nextInt();
                scanner.nextLine();
                //ends code, as user has chosen to go back
                if (itemChoosen == individualItems.length) {
                    return ("");
                }
                if (itemChoosen < individualItems.length) {
                    break;
                } else {
                    System.out.println("That value is not correct, please try again");
                    pressEnter();
                }
            }catch (InputMismatchException e){
                System.out.println("Not a number\n" +
                        "Try again");
                scanner.next();
            }
        }
        String item = individualItems[itemChoosen];
        //item string contains brackets, this code removes the first bracket, so it can obtain the name correctly
        String filteredItem = item.replace("{","");
        filteredItem = filteredItem.replaceAll(" ","");
        //splits the item into just its name and description, used to get the 0th index(name) in order to remove it when used
        String[] itemName = filteredItem.split("=");
        System.out.println(itemName[0]);
        //adds 50 health back to user
        if(itemName[0].equals("HealthPotion")) {
            if(player.getHealth() > 50){
                player.setHealth(100);
            }else{
                player.setHealth(player.getHealth() + 50);
            }
            System.out.println("Health potion has been used\n" +
                    "50 health has been restored\n" +
                    "New Health:" + player.getHealth());
            player.removeItem("Health Potion");
            pressEnter();
            return("health potion");
        }
        //damages the opponent for 50% of their health
        else if(itemName[0].equals("SmallKnives")) {
            System.out.println("Small Knives has been used\n" +
                    monsterHealth /2+" damage has been dealt");
            monsterHealth/=2;
                    System.out.println("Monster Health:" + monsterHealth);
            player.removeItem("Small Knives");
            pressEnter();
            return("small knives");
        }
        else if(itemName[0].equals("EscapeScroll"))  {
            System.out.println("Escape Scroll has been used");
            player.removeItem("Escape Scroll");
            pressEnter();
            return("escape scroll");
        }
        else if(itemName[0].equals("Bomb"))  {
            System.out.println("Bomb has been used \n" +
                    "Monster's health has been reduced to 0");
            monsterHealth = 0;
            player.removeItem("Bomb");
            pressEnter();
            return("bomb");
        }
        else if(itemName[0].equals("LargeHealthPotion"))  {
            System.out.println("Health Potion has been used\n" +
                    "Health has been restored back to 100");
            player.setHealth(100);
            player.removeItem("Large Health Potion");
            pressEnter();
            return ("large health potion");
        }
        return("Shouldn't get here");
    }
    public boolean flee(){
        if(monster.getName().equalsIgnoreCase("vampire")){
            System.out.println("Don't even try, you can't outrun me");
            return(false);
        }
        if(player.getHealth() > 50){
            System.out.println("Fleeing will cost you half of your current health \n" +
                    "Current health:" + player.getHealth());
            pressEnter();
            System.out.println("Are you sure you want to flee? \n" +
                    "(yes/no)");
            String decision = RandomEncounter.yesNoCase();
            if(decision.equals("yes")) {
                player.setHealth(player.getHealth() / 2);
                System.out.println("Fleed Succesfully \n" +
                        "New Health:" + player.getHealth());
                pressEnter();
                return(true);
            }else{
                return(false);
            }
        }else{
            System.out.println("Unable to flee, can only flee when you are over 50% health");
            return(false);
        }
    }
    public void displayBattleInfo() {
        System.out.println("Press 1 to attack \n" +
                "Press 2 to view items \n" +
                "Press 3 to flee");
    }
    public void displayCharacterData(){
        System.out.println("Character Info\n" +
                "-----------------------------\n" +
                "Health:" + player.getHealth() + "\n" +
                "Attack:" + player.getAttack() + "\n" +
                "Weapon:" + player.getWeapon() + "\n" +
                "Items:" + player.getItems() + "\n");
        if(player.getGender().equals("male")){
            player.printMale();
        }else if(player.getGender().equals("female")){
            player.printFemale();
        }
        pressEnter();
    }
    public void displayMonsterData() {
        switch (monster.getName()) {
            case ("Centaur"):
                drawCentaur();
                break;
            case ("Dragon"):
                drawDragon();
                break;
            case ("Ghost"):
                drawGhost();
                break;
            case ("Skeleton"):
                drawSkeleton();
                break;
            case ("Spider"):
                drawSpider();
                break;
            case("Vampire"):
                drawVampire();
                break;
        }
        //prevents program from printing a negative health value
       if(monsterHealth < 0){
            monsterHealth = 0;
        }
        System.out.println("Name:" + monster.getName() + "\n" +
                "Health:" + monsterHealth + "\n" +
                "Attack:" + monster.getAttack());
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

    public void createSpace(){
        for(int i = 0; i < 30; i++){
            System.out.println();
        }
    }
    public void drawGrave(){
        System.out.println("" +
                "           ____/      \\\\_____\n" +
                "          |  _     ___   _   ||\n" +
                "          | | \\     |   | \\  ||\n" +
                "          | |  |    |   |  | ||\n" +
                "          | |_/     |   |_/  ||\n" +
                "          | | \\     |   |    ||\n" +
                "          | |  \\    |   |    ||\n" +
                "          | |   \\. _|_. | .  ||\n" +
                "          |                  ||\n" +
                "          |                  ||\n" +
                "          |                  ||\n" +
                "          |                  |        ");
    }
    public void drawCentaur() {
        System.out.println("                __\n" +
                "               / _\\ #\n" +
                "               \\c /  #\n" +
                "               / \\___ #\n" +
                "               \\`----`#==>  \n" +
                "               |  \\  #\n" +
                "    ,%.-\"\"\"---'`--'\\#_\n" +
                "   %%/             |__`\\\n" +
                "  .%'\\     |   \\   /  //\n" +
                "  ,%' >   .'----\\ |  [/\n" +
                "     < <<`       ||\n" +
                "      `\\\\\\       ||\n" +
                "        )\\\\      )\\\n" +
                "^^^^^^^^\"\"\"^^^^^^\"\"^^^^^^^^^^");
    }

    public void drawDragon() {
        System.out.println("                 \\||/\n" +
                "                |  @___oo\n" +
                "      /\\  /\\   / (__,,,,|\n" +
                "     ) /^\\) ^\\/ _)\n" +
                "     )   /^\\/   _)\n" +
                "     )   _ /  / _)\n" +
                " /\\  )/\\/ ||  | )_)\n" +
                "<  >      |(,,) )__)\n" +
                " ||      /    \\)___)\\\n" +
                " | \\____(      )___) )___\n" +
                "  \\______(_______;;; __;;;");
    }

    public void drawGhost() {
        System.out.println("     .-.\n" +
                "   .'   `.\n" +
                "   :g g   :\n" +
                "   : o    `.\n" +
                "  :         ``.\n" +
                " :             `.\n" +
                ":  :         .   `.\n" +
                ":   :          ` . `.\n" +
                " `.. :            `. ``;\n" +
                "    `:;             `:'\n" +
                "       :              `.\n" +
                "        `.              `.     .\n" +
                "          `'`'`'`---..,___`;.-'");
    }

    public void drawSkeleton() {
        System.out.println("      .-.\n" +
                "     (o.o)\n" +
                "      |=|\n" +
                "     __|__\n" +
                "   //.=|=.\\\\\n" +
                "  // .=|=. \\\\\n" +
                "  \\\\ .=|=. //\n" +
                "   \\\\(_=_)//\n" +
                "    (:| |:)\n" +
                "     || ||\n" +
                "     () ()\n" +
                "     || ||\n" +
                "     || ||\n" +
                "    ==' '==");
    }

    public void drawSpider() {
        System.out.println("               (\n" +
                "               )\n" +
                "              (\n" +
                "        /\\  .-\"\"\"-.  /\\\n" +
                "       //\\\\/  ,,,  \\//\\\\\n" +
                "       |/\\| ,;;;;;, |/\\|\n" +
                "       //\\\\\\;-\"\"\"-;///\\\\\n" +
                "      //  \\/   .   \\/  \\\\\n" +
                "     (| ,-_| \\ | / |_-, |)\n" +
                "       //`__\\.-.-./__`\\\\\n" +
                "      // /.-(() ())-.\\ \\\\\n" +
                "     (\\ |)   '---'   (| /)\n" +
                "      ` (|           |) `\n" +
                "        \\)           (/");
    }
    public void drawVampire(){
        System.out.println("         " +
                "               __.......__\n" +
                "            .-:::::::::::::-.\n" +
                "          .:::''':::::::''':::.\n" +
                "        .:::'     `:::'     `:::. \n" +
                "   .'\\  ::'   ^^^  `:'  ^^^   '::  /`.\n" +
                "  :   \\ ::   _.__       __._   :: /   ;\n" +
                " :     \\`: .' ___\\     /___ `. :'/     ; \n" +
                ":       /\\   (_|_)\\   /(_|_)   /\\       ;\n" +
                ":      / .\\   __.' ) ( `.__   /. \\      ;\n" +
                ":      \\ (        {   }        ) /      ; \n" +
                " :      `-(     .  ^\"^  .     )-'      ;\n" +
                "  `.       \\  .'<`-._.-'>'.  /       .'\n" +
                "    `.      \\    \\;`.';/    /      .'\n" +
                "      `._    `-._       _.-'    _.'\n" +
                "       .'`-.__ .'`-._.-'`. __.-'`.\n" +
                "     .'       `.         .'       `.\n" +
                "   .'           `-.   .-'           `.");
    }
}
