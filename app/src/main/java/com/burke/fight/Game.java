package com.burke.fight;

import java.io.Serializable;

public class Game implements Serializable {

    private static Game instance;
    private Player player;
    private Enemy[] enemies;
    private int stage;

    private Game() {
        player = (Player) CharacterFactory.mathTeacher(2,3);
        enemies = new Enemy[5];
    }

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Enemy[] getEnemies() {
        return enemies;
    }

    public int getStage() {
        return stage;
    }

    public void nextStage() {
        this.stage++;
    }
}
