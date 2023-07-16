package com.burke.fight;

import java.io.Serializable;

public class Attack implements Serializable {

    private String name;
    private int damage;
    private double chance;
    private int numUsesLeft;
    private int maxNumUsesLeft;
    private boolean isUnlocked;

    public Attack(String name, int damage, double chance, int maxNumUsesLeft, boolean isUnlocked) {
        this.name = name;
        this.damage = damage;
        this.chance = chance;
        this.maxNumUsesLeft = maxNumUsesLeft;
        this.numUsesLeft = maxNumUsesLeft;
        this.isUnlocked = isUnlocked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public double getChance() {
        return chance;
    }

    public void setChance(double chance) {
        this.chance = chance;
    }

    public int getNumUsesLeft() {
        return numUsesLeft;
    }

    public void setNumUsesLeft(int numUsesLeft) {
        this.numUsesLeft = numUsesLeft;
    }

    public int getMaxNumUsesLeft() {
        return maxNumUsesLeft;
    }

    public void setMaxNumUsesLeft(int maxNumUsesLeft) {
        this.maxNumUsesLeft = maxNumUsesLeft;
    }

    public boolean isUnlocked() {
        return isUnlocked;
    }

    public boolean isEmpty() {
        if (numUsesLeft <= 0) {
            return true;
        }
        return false;
    }

    public void unlock() {
        this.isUnlocked = true;
    }

    public void resetMaxUses(){
        numUsesLeft = maxNumUsesLeft;
    }

    public void levelUp(){
        name = "+" + name;
        damage *= 1.5;
    }

    public boolean useOn(Character character) {
        double rand = Math.random();
        if (rand < chance){
            character.takeDamage(damage);
            numUsesLeft--;
            return true;
        }
        return false;
    }

}
