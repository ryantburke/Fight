package com.burke.fight;

import java.io.Serializable;

public class Player extends Character implements Serializable {

    private int level;
    private int numEnemiesDefeated;


    public Player(Character character) {
        super(character);
        this.level = 0;
        this.numEnemiesDefeated = 0;
    }

    public void levelUp(){
        level++;
    }

    public void defeatEnemy() {
        numEnemiesDefeated++;
    }

    public void addMaxHp(){
        hp_max += 100 * (level / 5 + 1);
        hp += 100 * (level / 5 + 1);
    }

}
