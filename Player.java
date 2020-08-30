package com.example.company;

import java.lang.reflect.Array;
import java.util.*;

public final class Player{
    Scanner scanner = new Scanner(System.in);
    public List<String> weaponList  = Arrays.asList("Rusty Sword", "Golden Blade", "Boomerang", "Bow and Arrow", "Crossbow", "Longbow", "Flamethrower", "Spear", "Sling", "Bayonet", "Club", "Dagger", "Halberd", "Lance", "Pike", "Quarterstaff", "Sabre", "Sword", "Tomahawk");
    private static String name;
    private static int age;
    private static String gender;
    private static int attack = 5;
    private static String weapon = "Magical Taco";
    private static Map<String,String> items = new HashMap<>();
    private static String[] itemNames = {"Large Health Potion","Small Knives","Escape Scroll","Bomb","Health Potion"};
    private static String[] itemDescriptions = {"Heals you back to full health", "Damages the opponent for 50% of their health",
            "Allows you to flee without the health penalty","Instantly kills your opponent",
            "Heals you for 50% of your health"};
    private static int health = 100;
    public Player(){

    }

    public void printData(){
        System.out.println("Character Info\n" +
                "-----------------------------\n" +
                "Health:" + this.health + "\n" +
                "Attack:" + this.attack + "\n" +
                "Weapon:" + this.weapon + "\n" +
                "Items:" + this.items + "\n");
        if(gender.equalsIgnoreCase("male")){
            printMale();
        }else if(gender.equalsIgnoreCase("female")){
            printFemale();
        }
        pressEnter();
        for(int i = 0; i < 30; i++){
            System.out.println();
        }
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public Map<String, String> getItems() {
        return items;
    }

    public void addItem(String name, String description) {
        this.items.put(name, description);
    }

    public void removeItem(String name){
        this.items.remove(name);
    }
    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List getWeaponList() {
        return weaponList;
    }

    public String getWeaponList(int index){
        return weaponList.get(index);
    }



    public String[] getItemNames() {
        return itemNames;
    }

    public String[] getItemDescriptions() {
        return itemDescriptions;
    }

    public void printMale(){
        System.out.println("     ////^\\\\\\\\\n" +
                "      | ^   ^ |\n" +
                "     @ (o) (o) @\n" +
                "      |   <   |\n" +
                "      |  ___  |\n" +
                "       \\_____/\n" +
                "     ____|  |____\n" +
                "    /    \\__/    \\\n" +
                "   /              \\\n" +
                "  /\\_/|        |\\_/\\\n" +
                " / /  |        |  \\ \\\n" +
                "( <   |        |   > )\n" +
                " \\ \\  |        |  / /\n" +
                "  \\ \\ |________| / /");
    }

    public void printFemale(){
        System.out.println(" /////////////\\\\\\\\\n" +
                "(((((((((((((( \\\\\\\\\n" +
                "))) ~~      ~~  (((\n" +
                "((( (*)     (*) )))\n" +
                ")))     <       (((\n" +
                "((( '\\______/`  )))\n" +
                ")))\\___________/(((\n" +
                "       _) (_\n" +
                "      / \\_/ \\\n" +
                "     /(     )\\\n" +
                "    // )___( \\\\");
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
