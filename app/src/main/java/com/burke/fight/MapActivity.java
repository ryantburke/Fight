package com.burke.fight;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class MapActivity extends AppCompatActivity {

    private Button[][] btnsMove = new Button[8][8];
    private Context context = this;
    private Game game = Game.getInstance();

    private Player player;
    private ImageView ivPlayer;
    private ArrayList<Enemy> enemies;
    private ImageView[] ivEnemies;

    private Handler handler = new Handler();
    private Runnable runnableMoveEnemies;
    private boolean continueMoving;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        player = game.getPlayer();

        ivPlayer = findViewById(R.id.iv_player);
        player.setImageView(ivPlayer);

        initializeMap();
        initializeEnemies();
        moveEnemies();
        movePlayer();
    }

    private void initializeMap(){
        btnsMove[0][0] = (Button) findViewById(R.id.btn_00);
        btnsMove[0][1] = findViewById(R.id.btn_01);
        btnsMove[0][2] = findViewById(R.id.btn_02);
        btnsMove[0][3] = findViewById(R.id.btn_03);
        btnsMove[0][4] = findViewById(R.id.btn_04);
        btnsMove[0][5] = findViewById(R.id.btn_05);
        btnsMove[0][6] = findViewById(R.id.btn_06);
        btnsMove[0][7] = findViewById(R.id.btn_07);

        btnsMove[1][0] = findViewById(R.id.btn_10);
        btnsMove[1][1] = findViewById(R.id.btn_11);
        btnsMove[1][2] = findViewById(R.id.btn_12);
        btnsMove[1][3] = findViewById(R.id.btn_13);
        btnsMove[1][4] = findViewById(R.id.btn_14);
        btnsMove[1][5] = findViewById(R.id.btn_15);
        btnsMove[1][6] = findViewById(R.id.btn_16);
        btnsMove[1][7] = findViewById(R.id.btn_17);

        btnsMove[2][0] = findViewById(R.id.btn_20);
        btnsMove[2][1] = findViewById(R.id.btn_21);
        btnsMove[2][2] = findViewById(R.id.btn_22);
        btnsMove[2][3] = findViewById(R.id.btn_23);
        btnsMove[2][4] = findViewById(R.id.btn_24);
        btnsMove[2][5] = findViewById(R.id.btn_25);
        btnsMove[2][6] = findViewById(R.id.btn_26);
        btnsMove[2][7] = findViewById(R.id.btn_27);

        btnsMove[3][0] = findViewById(R.id.btn_30);
        btnsMove[3][1] = findViewById(R.id.btn_31);
        btnsMove[3][2] = findViewById(R.id.btn_32);
        btnsMove[3][3] = findViewById(R.id.btn_33);
        btnsMove[3][4] = findViewById(R.id.btn_34);
        btnsMove[3][5] = findViewById(R.id.btn_35);
        btnsMove[3][6] = findViewById(R.id.btn_36);
        btnsMove[3][7] = findViewById(R.id.btn_37);

        btnsMove[4][0] = findViewById(R.id.btn_40);
        btnsMove[4][1] = findViewById(R.id.btn_41);
        btnsMove[4][2] = findViewById(R.id.btn_42);
        btnsMove[4][3] = findViewById(R.id.btn_43);
        btnsMove[4][4] = findViewById(R.id.btn_44);
        btnsMove[4][5] = findViewById(R.id.btn_45);
        btnsMove[4][6] = findViewById(R.id.btn_46);
        btnsMove[4][7] = findViewById(R.id.btn_47);

        btnsMove[5][0] = findViewById(R.id.btn_50);
        btnsMove[5][1] = findViewById(R.id.btn_51);
        btnsMove[5][2] = findViewById(R.id.btn_52);
        btnsMove[5][3] = findViewById(R.id.btn_53);
        btnsMove[5][4] = findViewById(R.id.btn_54);
        btnsMove[5][5] = findViewById(R.id.btn_55);
        btnsMove[5][6] = findViewById(R.id.btn_56);
        btnsMove[5][7] = findViewById(R.id.btn_57);

        btnsMove[6][0] = findViewById(R.id.btn_60);
        btnsMove[6][1] = findViewById(R.id.btn_61);
        btnsMove[6][2] = findViewById(R.id.btn_62);
        btnsMove[6][3] = findViewById(R.id.btn_63);
        btnsMove[6][4] = findViewById(R.id.btn_64);
        btnsMove[6][5] = findViewById(R.id.btn_65);
        btnsMove[6][6] = findViewById(R.id.btn_66);
        btnsMove[6][7] = findViewById(R.id.btn_67);

        btnsMove[7][0] = findViewById(R.id.btn_70);
        btnsMove[7][1] = findViewById(R.id.btn_71);
        btnsMove[7][2] = findViewById(R.id.btn_72);
        btnsMove[7][3] = findViewById(R.id.btn_73);
        btnsMove[7][4] = findViewById(R.id.btn_74);
        btnsMove[7][5] = findViewById(R.id.btn_75);
        btnsMove[7][6] = findViewById(R.id.btn_76);
        btnsMove[7][7] = findViewById(R.id.btn_77);
    }

    private void initializeEnemies() {

        enemies = game.getEnemies();

        for (Enemy enemy:enemies) {
            Log.d("enemy",enemy.getName());
        }

        ivEnemies = new ImageView[5];
        ivEnemies[0] = findViewById(R.id.iv_enemy1);
        ivEnemies[1] = findViewById(R.id.iv_enemy2);
        ivEnemies[2] = findViewById(R.id.iv_enemy3);
        ivEnemies[3] = findViewById(R.id.iv_enemy4);
        ivEnemies[4] = findViewById(R.id.iv_enemy5);
        for (int i=0; i<enemies.size(); i++) {
            enemies.get(i).setImageView(ivEnemies[i]);
        }

        //move player and enemies
        moveCharacter(player);

        for (Enemy enemy:enemies) {
            moveCharacter(enemy);
        }

    }

    private void moveEnemies() {

        continueMoving = true;

        checkIfFight();

        handler = new Handler(Looper.getMainLooper());
        runnableMoveEnemies = new Runnable() {
            @Override
            public void run() {
                if (continueMoving) {
                    for (int i=0; i< enemies.size(); i++) {
                        moveEnemy(enemies.get(i));
                    }
                    moveEnemies();
                }

            }
        };
        handler.postDelayed(runnableMoveEnemies,250);

    }

    private void moveEnemy(Enemy enemy){
        double chance = Math.random();
        if (chance < .5 && enemy.getHp() > 0) {
            int dir = (int) (Math.random() * 4);

            if (dir == 0 && enemy.getY() > 0 && !enemyAdjacent(enemy)[0]) {
                enemy.moveUp();
                moveCharacter(enemy);
            }
            else if (dir == 1 && enemy.getY() < btnsMove.length - 1 && !enemyAdjacent(enemy)[1]) {
                enemy.moveDown();
                moveCharacter(enemy);
            }
            else if (dir == 2 && enemy.getX() > 0 && !enemyAdjacent(enemy)[2]) {
                enemy.moveLeft();
                moveCharacter(enemy);
            }
            else if (dir == 3 && enemy.getX() < btnsMove[0].length-1 && !enemyAdjacent(enemy)[3]) {
                enemy.moveRight();
                moveCharacter(enemy);
            }
            else {
                moveEnemy(enemy);
            }
        }
    }

    private boolean[] enemyAdjacent(Enemy enemyCur) {
        boolean[] isNextTo = new boolean[4];
        for (Enemy enemy:enemies) {
            if ((enemyCur.getY() - enemy.getY()) == 1) {
                isNextTo[0] = true;
            }
            if ((enemyCur.getY() - enemy.getY()) == -1) {
                isNextTo[1] = true;
            }
            if ((enemyCur.getX() - enemy.getX()) == -1 || (enemyCur.getX() - enemy.getX()) == -2) {
                isNextTo[2] = true;
            }
            if ((enemyCur.getX() - enemy.getX()) == 1 || (enemyCur.getX() - enemy.getX()) == 2) {
                isNextTo[3] = true;
            }
        }
        return isNextTo;
    }

    private void movePlayer() {

        checkIfFight();

        for (int r=0; r<btnsMove.length; r++) {
            for (int c = 0; c< btnsMove[0].length; c++) {
                Log.d("button",""+r + c);
                btnsMove[r][c].setBackgroundColor(ContextCompat.getColor(context, R.color.green));
                btnsMove[r][c].setClickable(false);
            }
        }

        Button current = btnsMove[player.getY()][player.getX()];

        current.setBackgroundColor(ContextCompat.getColor(context, R.color.white));

        //left
        if (player.getX() > 0) {
            Button btn = btnsMove[player.getY()][player.getX()-1];
            btn.setBackgroundColor(ContextCompat.getColor(context,R.color.map_btn_tint));
            btn.setClickable(true);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    player.moveLeft();
                    moveCharacter(player);
                    movePlayer();
                }
            });
        }

        //right
        if (player.getX() < btnsMove[0].length - 1 ) {
            Button btn = btnsMove[player.getY()][player.getX()+1];
            btn.setBackgroundColor(ContextCompat.getColor(context,R.color.map_btn_tint));
            btn.setClickable(true);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    player.moveRight();
                    moveCharacter(player);
                    movePlayer();
                }
            });
        }

        //up
        if (player.getY() > 0 ) {
            Button btn = btnsMove[player.getY()-1][player.getX()];
            btn.setBackgroundColor(ContextCompat.getColor(context,R.color.map_btn_tint));
            btn.setClickable(true);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    player.moveUp();
                    moveCharacter(player);
                    movePlayer();
                }
            });
        }

        //down
        if (player.getY() < btnsMove.length - 1 ) {
            Button btn = btnsMove[player.getY()+1][player.getX()];
            btn.setBackgroundColor(ContextCompat.getColor(context,R.color.map_btn_tint));
            btn.setClickable(true);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    player.moveDown();
                    moveCharacter(player);
                    movePlayer();
                }
            });
        }

    }

    private void moveCharacter(Character character) {

        ImageView ivCharacter = character.getImageView();

        ConstraintLayout layout = findViewById(R.id.constraint_map);
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(layout);

        Button current = btnsMove[character.getY()][character.getX()];

        constraintSet.connect(ivCharacter.getId(), ConstraintSet.START,    current.getId(), ConstraintSet.START);
        constraintSet.connect(ivCharacter.getId(), ConstraintSet.END,      current.getId(), ConstraintSet.END);
        constraintSet.connect(ivCharacter.getId(), ConstraintSet.TOP,      current.getId(), ConstraintSet.TOP);
        constraintSet.connect(ivCharacter.getId(), ConstraintSet.BOTTOM,   current.getId(), ConstraintSet.BOTTOM);

        TransitionManager.beginDelayedTransition(layout);
        constraintSet.applyTo(layout);
        ivCharacter.invalidate();

    }

    private void checkIfFight(){
        for (Enemy enemy:enemies){
            if (player.getX() == enemy.getX() && player.getY() == enemy.getY() && enemy.getHp() > 0) {
                continueMoving = false;
                handler.removeCallbacks(runnableMoveEnemies);
                Intent intentFight = new Intent(context,FightActivity.class);
                Log.d("match",enemy.getName());
                game.setCurrentEnemy(enemy);
                startActivity(intentFight);
                return;
            }
        }
    }

}