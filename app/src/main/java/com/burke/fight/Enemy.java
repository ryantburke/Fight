package com.burke.fight;

import android.util.Log;

import java.io.Serializable;

public class Enemy extends Character{


    public Enemy(String name, int hp_max, Attack[] attacks, String winMessage, String loseMesssage, int x, int y){
        super(name, hp_max, attacks, winMessage, loseMesssage, x, y);
        Log.d("enemy",toString());
    }



}
