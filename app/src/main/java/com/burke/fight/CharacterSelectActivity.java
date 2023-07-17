package com.burke.fight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class CharacterSelectActivity extends AppCompatActivity {

    private Game game = Game.getInstance();
    private Context context = this;
    private Player player;
    private ImageView[] ivCharacterSelect;

    private TextView[] tvCharacters;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_select);

        ivCharacterSelect = new ImageView[4];
        ivCharacterSelect[0] = findViewById(R.id.iv_c1);
        ivCharacterSelect[1] = findViewById(R.id.iv_c2);
        ivCharacterSelect[2] = findViewById(R.id.iv_c3);
        ivCharacterSelect[3] = findViewById(R.id.iv_c4);

        tvCharacters = new TextView[2];
        tvCharacters[0] = findViewById(R.id.tv_c1);
        tvCharacters[1] = findViewById(R.id.tv_c2);

        characterSelect(ivCharacterSelect[0],CharacterFactory.mathTeacher(3,3));
        characterSelect(ivCharacterSelect[1],CharacterFactory.wickedWitch(3,3));
        characterSelect(ivCharacterSelect[2],CharacterFactory.belowAverageStudent(3,3));
        characterSelect(ivCharacterSelect[3],CharacterFactory.burgerFlipper(3,3));


    }

    private void characterSelect(ImageView iv, Character character) {
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i=0; i< ivCharacterSelect.length; i++) {
                    int imageId = character.getImageIds()[i];

                    for (TextView tv:tvCharacters) {
                        tv.setVisibility(View.INVISIBLE);
                    }

                    ivCharacterSelect[i].setImageResource(imageId);
                    ivCharacterSelect[i].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            player = new Player(character);
                            player.setImageId(imageId);
                            game.setPlayer(player);
                            Intent intent = new Intent(context, MapActivity.class);
                            startActivity(intent);
                        }
                    });

                }
            }
        });
    }
}