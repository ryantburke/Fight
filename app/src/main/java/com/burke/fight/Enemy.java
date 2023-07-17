package com.burke.fight;

import android.util.Log;
import android.widget.ImageView;

import java.io.Serializable;

public class Enemy extends Character implements Serializable{

    private int level;

    public Enemy(Character character, int level) {
        super(character);
        this.level = level;
        this.imageId = imageIds[(level-1) % imageIds.length];
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void levelUp(){
        level++;
        for (Attack attack:attacks) {
            attack.levelUp();
        }
        hp_max *= 1.5;
        hp = hp_max;
        if (level > 4) {
            attacks[3].unlock();
        }
    }
}
