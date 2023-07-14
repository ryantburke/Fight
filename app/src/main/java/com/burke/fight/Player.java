package com.burke.fight;

import java.io.Serializable;

public class Player extends Character implements Serializable {

    private int level;


    public Player(Character character) {
        this.name = character.getName();
        this.hp_max = character.getHp_max();
        this.hp = character.getHp();
        this.attacks = character.getAttacks();
        this.winMessage = character.getWinMessage();
        this.loseMessage = character.getLoseMessage();
        this.y= character.getY();
        this.x= character.getX();
        this.level = 0;
    }

}
