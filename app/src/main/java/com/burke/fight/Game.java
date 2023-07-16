package com.burke.fight;

import java.io.Serializable;
import java.util.ArrayList;

public class Game implements Serializable {

    private static Game instance;
    private Player player;
    private ArrayList<Enemy> enemies;
    private Enemy currentEnemy;
    private int stage;

    private Game() {
        player = new Player(CharacterFactory.mathTeacher(3,4));
        enemies = new ArrayList<Enemy>();
        stage = 1;
    }

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    public Player getPlayer() {
        if (player == null) {
            player = new Player(CharacterFactory.mathTeacher(2,3));
        }
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ArrayList<Enemy> getEnemies() {
        if (enemies.isEmpty()) {
            enemies.add(new Enemy(CharacterFactory.mathTeacher(1,2),stage));
            enemies.add(new Enemy(CharacterFactory.burgerFlipper(3,6),stage));
            enemies.add(new Enemy(CharacterFactory.belowAverageStudent(4,2),stage));
            enemies.add(new Enemy(CharacterFactory.organDonor(5,4),stage));
            enemies.add(new Enemy(CharacterFactory.wickedWitch(6,3),stage));
        }
        return enemies;
    }

    public Enemy getCurrentEnemy() {
        return currentEnemy;
    }

    public void setCurrentEnemy(Enemy currentEnemy) {
        this.currentEnemy = currentEnemy;
    }

    public int getStage() {
        return stage;
    }

    public void nextStage() {
        this.stage++;
    }
}
