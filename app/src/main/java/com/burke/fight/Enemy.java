package com.burke.fight;

import android.util.Log;

import java.io.Serializable;

public class Enemy extends Character{

    int level;

    public Enemy(Character character, int level) {
        super(character);
        this.level = level;
    }



}
