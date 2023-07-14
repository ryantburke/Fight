package com.burke.fight;

import java.io.Serializable;

public class Player extends Character implements Serializable {

    private int level;


    public Player(Character character) {
        super(character);
        this.level = 0;
    }

}
