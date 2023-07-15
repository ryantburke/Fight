package com.burke.fight;

import android.os.Bundle;
import android.os.Handler;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

public class FightActivity extends AppCompatActivity {

    private Player player;
    private Enemy enemy;
    private int turn;

    private TextView tvPlayerName, tvEnemyName, tvPlayerHp, tvEnemyHp, tvDescription;
    private ImageView ivPlayer, ivEnemy;

    private Button[] btnsAttack;

    private static final long DELAY_ATTACK_BUTTONS = 300;
    private static final long DELAY_HIT_ANIMATION = 700;
    private static final long DELAY_BEFORE_NEXT_TURN = 2000;
    private static final long DELAY_BEFORE_ENEMY_TURN = 700;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fight);

        initialize();

        //turn = (int) (Math.random() * 2);
        turn = 0;

        fight();

    }

    private void initialize() {
        player = (Player) getIntent().getSerializableExtra("player");
        enemy = (Enemy) getIntent().getSerializableExtra("enemy");

        tvPlayerName = findViewById(R.id.tv_player_name);
        tvPlayerName.setText(player.getName());
        tvPlayerHp = findViewById(R.id.tv_player_hp);
        tvPlayerHp.setText("HP: " + player.getHp());
        tvEnemyName = findViewById(R.id.tv_enemy_name);
        tvEnemyName.setText(enemy.getName());
        tvEnemyHp = findViewById(R.id.tv_enemy_hp);
        tvEnemyHp.setText("HP: " + enemy.getHp());


        tvDescription = findViewById(R.id.tv_description);

        ivPlayer = findViewById(R.id.iv_player);
        ivPlayer.setImageResource(player.getImageId());
        ivEnemy = findViewById(R.id.iv_enemy);
        ivEnemy.setImageResource(enemy.getImageId());

        btnsAttack = new Button[4];
        btnsAttack[0] = findViewById(R.id.btn_atk1);
        btnsAttack[1] = findViewById(R.id.btn_atk2);
        btnsAttack[2] = findViewById(R.id.btn_atk3);
        btnsAttack[3] = findViewById(R.id.btn_atk4);
    }

    private void fight() {

        updateHealthTexts();

        if (player.getHp() > 0 && enemy.getHp() > 0) {
            if (turn%2 == 0) {
                playerTurn();
            } else {
                enemyTurn();
            }
        }
    }

    private void playerTurn() {
        constrainTopThenBottom(ivPlayer,ivEnemy);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showAttackButtons();
            }
        }, DELAY_ATTACK_BUTTONS);




        for (int i=0; i<btnsAttack.length; i++) {
            final int k = i;
            btnsAttack[i].setText(player.getAttack(i).getName());
            btnsAttack[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String text = player.getName() + " attacks with " + player.getAttack(k).getName() + ".";
                    tvDescription.setText(text);

                    if (player.getAttack(k).useOn(enemy)) {
                        hideAttackButtons();
                        tvDescription.setText("HIT!");
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                updateHealthTexts();
                            }
                        },DELAY_HIT_ANIMATION / 2);

                        ivPlayer.bringToFront();
                        constraintHit(ivPlayer,ivEnemy);
                    } else {
                        hideAttackButtons();
                        tvDescription.setText("MISS!");
                        constraintMiss(ivPlayer,ivEnemy);
                    }

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            turn++;
                            fight();
                        }
                    }, DELAY_BEFORE_NEXT_TURN);


                }
            });
        }
    }

    private void enemyTurn() {
        /*
            need to handle case if enemy has no uses left on any attack
         */

        String text = enemy.getName() + "'s turn";
        tvDescription.setText(text);
        hideAttackButtons();
        constrainTopThenBottom(ivEnemy,ivPlayer);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Attack attack = enemy.getAttack((int) (Math.random() * 4));
                if (attack.getNumUsesLeft() > 0) {

                    String text = enemy.getName() + " attacks with " + attack.getName() + ".";
                    tvDescription.setText(text);

                    if (attack.useOn(player)) {
                        tvDescription.setText("HIT!");
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                updateHealthTexts();
                            }
                        },DELAY_HIT_ANIMATION / 2);
                        ivEnemy.bringToFront();
                        constraintHit(ivEnemy,ivPlayer);
                    } else {
                        tvDescription.setText("MISS!");
                        constraintMiss(ivEnemy,ivPlayer);
                    }

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            turn++;
                            fight();
                        }
                    }, DELAY_BEFORE_NEXT_TURN);


                } else {
                    enemyTurn();
                }
            }
        }, DELAY_BEFORE_ENEMY_TURN);


    }

    private void showAttackButtons(){
        for (Button btn:btnsAttack) {
            btn.setVisibility(View.VISIBLE);
        }
    }

    private void hideAttackButtons(){
        for (Button btn:btnsAttack) {
            btn.setVisibility(View.INVISIBLE);
        }
    }

    private void updateHealthTexts(){
        tvPlayerHp.setText("HP: " + player.getHp());
        tvEnemyHp.setText("HP: " + enemy.getHp());
    }

    private void constrainTopThenBottom(View top, View bottom){
        Log.d("Constrain","top");
        ConstraintLayout layout = findViewById(R.id.constraint_fight);
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(layout);

        constraintSet.connect(top.getId(), ConstraintSet.TOP,      R.id.gl_top, ConstraintSet.TOP);
        constraintSet.connect(top.getId(), ConstraintSet.BOTTOM,   R.id.gl_vert_mid, ConstraintSet.TOP);
        constraintSet.connect(bottom.getId(), ConstraintSet.TOP,      R.id.gl_vert_mid, ConstraintSet.TOP);
        constraintSet.connect(bottom.getId(), ConstraintSet.BOTTOM,   R.id.gl_base, ConstraintSet.TOP);

        TransitionManager.beginDelayedTransition(layout);
        constraintSet.applyTo(layout);
        top.invalidate();
        bottom.invalidate();
    }

    private void constraintHit(View attacker, View defender) {


        ConstraintLayout layout = findViewById(R.id.constraint_fight);
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(layout);

        //save original constraints
        ConstraintSet originalConstraints = new ConstraintSet();
        originalConstraints.clone(layout);

        constraintSet.clear(attacker.getId(), ConstraintSet.TOP);
        constraintSet.clear(attacker.getId(), ConstraintSet.BOTTOM);
        constraintSet.clear(attacker.getId(), ConstraintSet.START);
        constraintSet.clear(attacker.getId(), ConstraintSet.END);

        constraintSet.connect(attacker.getId(), ConstraintSet.TOP,     defender.getId(), ConstraintSet.TOP);
        constraintSet.connect(attacker.getId(), ConstraintSet.BOTTOM,  defender.getId(), ConstraintSet.BOTTOM);
        constraintSet.connect(attacker.getId(), ConstraintSet.START,  defender.getId(), ConstraintSet.START);
        constraintSet.connect(attacker.getId(), ConstraintSet.END,  defender.getId(), ConstraintSet.END);

        TransitionManager.beginDelayedTransition(layout);
        constraintSet.applyTo(layout);
        attacker.bringToFront();
        attacker.invalidate();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                TransitionManager.beginDelayedTransition(layout);
                originalConstraints.applyTo(layout);
                attacker.invalidate();
            }
        }, DELAY_HIT_ANIMATION);





    }

    private void constraintMiss(View attacker, View defender) {

        ConstraintLayout layout = findViewById(R.id.constraint_fight);
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(layout);

        //save original constraints
        ConstraintSet originalConstraints = new ConstraintSet();
        originalConstraints.clone(layout);

        constraintSet.clear(attacker.getId(), ConstraintSet.START);
        constraintSet.clear(attacker.getId(), ConstraintSet.END);

        constraintSet.connect(attacker.getId(), ConstraintSet.START,  defender.getId(), ConstraintSet.START);
        constraintSet.connect(attacker.getId(), ConstraintSet.END,  defender.getId(), ConstraintSet.END);

        TransitionManager.beginDelayedTransition(layout);
        constraintSet.applyTo(layout);
        attacker.bringToFront();
        attacker.invalidate();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                TransitionManager.beginDelayedTransition(layout);
                originalConstraints.applyTo(layout);
                attacker.invalidate();
            }
        }, DELAY_HIT_ANIMATION);


    }
}
