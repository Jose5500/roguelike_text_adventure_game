package com.example.company;

import java.util.ArrayList;
import java.util.Random;

public class Monster {
    private String name;
    private int health;
    private int attack;



    public Monster(String name, int health, int attack){
        this.name = name;
        this.health = health;
        this.attack = attack;
    }
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public String getName(){
        return name;
    }

    public void setName(){
        this.name = name;
    }
    public static Monster getRandomMonster(){
        String[] monsterNames = {"Centaur","Dragon","Ghost","Skeleton","Spider"};
        Random randomizer = new Random();
        int randomAttack = randomizer.nextInt(9) +1;
        int randomHealth = (randomizer.nextInt(5) + 1) * 10;
        int monsterNameIndex = randomizer.nextInt(monsterNames.length);
        String randomName = monsterNames[monsterNameIndex];
        if(randomName != null) {
            return (new Monster(randomName, randomHealth, randomAttack));
        }
        return(new Monster("No name",100,100));
    }
}
