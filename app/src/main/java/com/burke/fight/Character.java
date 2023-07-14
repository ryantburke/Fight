package com.burke.fight;

/**
 *  Character class defines characters to be placed in world
 *  @author Ryan Burke
 *  @since 1/14/23
 */
public class Character{

    /**
     *  a cell object that acts as the player "token",
     *  represents player on the map
     */
    protected String name;

    protected int hp;
    protected int hp_max;
    protected Attack[] attacks;
    protected String winMessage;
    protected String loseMessage;
    protected int x;
    protected int y;
    protected int imageId;
    protected int ID;
    private static int numCharacters;

    public Character() {}

    public Character(String name, int imageId, int hp_max, Attack[] attacks, String winMessage, String loseMessage, int x, int y) {
        this.name = name;
        this.imageId = imageId;
        this.hp_max = hp_max;
        this.hp = hp_max;
        this.attacks = attacks;
        this.winMessage = winMessage;
        this.loseMessage = loseMessage;
        this.y=y;
        this.x=x;
        this.ID = ++numCharacters;
    }

    public Character(Character character) {
        this.name = character.getName();
        this.imageId = character.imageId;
        this.hp_max = character.getHp_max();
        this.hp = character.getHp();
        this.attacks = character.getAttacks();
        this.winMessage = character.getWinMessage();
        this.loseMessage = character.getLoseMessage();
        this.y= character.getY();
        this.x= character.getX();
        this.ID = ++numCharacters;
    }




    public String getName(){
        return name;
    }

    public int getImageId() {
        return imageId;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getHp_max() {
        return hp_max;
    }

    public void setHp_max(int hp_max) {
        this.hp_max = hp_max;
    }

    public String getWinMessage() {
        return winMessage;
    }

    public void setWinMessage(String winMessage) {
        this.winMessage = winMessage;
    }

    public String getLoseMessage() {
        return loseMessage;
    }

    public void setLoseMessage(String loseMessage) {
        this.loseMessage = loseMessage;
    }

    /**
     *  gets x coordinate of character
     *  @return  x coordinate
     */
    public int getX(){
        return x;
    }

    /**
     *  gets y coordinate of character
     *  @return  y coordinate
     */
    public int getY(){
        return y;}

    public Attack getAttack(int attack){
        return attacks[attack];
    }

    public Attack[] getAttacks() {
        return attacks;
    }

    public void setAttacks(Attack[] attacks) {
        this.attacks = attacks;
    }

    @Override
    public String toString() {
        return "Character{" +
                "name='" + name + '\'' +
                ", hp=" + hp +
                ", hp_max=" + hp_max +
                ", x=" + x +
                ", y=" + y +
                ", ID=" + ID +
                '}';
    }

    public void takeDamage(int amount){
        hp -= amount;
    }

    public void restore(){
        hp = hp_max;
        for (int i=0; i<attacks.length; i++){
            attacks[i].resetMaxUses();
        }
    }

    public void unlockSecretAttack(){
        attacks[3].unlock();
    }

    public void upAttack(int attack){
        attacks[attack].levelUp();
    }



}