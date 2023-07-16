package com.burke.fight;

import android.util.Log;
import android.widget.ImageView;

import java.io.Serializable;

public class Enemy extends Character implements Serializable{

    private int level;

    public Enemy(Character character, int level) {
        super(character);
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
