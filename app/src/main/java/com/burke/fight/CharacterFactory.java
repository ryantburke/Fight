package com.burke.fight;

import android.graphics.Color;

public class CharacterFactory {

    public static String[] getCharacterListString(){
        String list[] = {
                            "Math Teacher",
                            "Burger Flipper",
                            "YOU",
                            "Organ Donor",
                            "Wicked Witch"
                        };
        return list;
    }

    public static Character mathTeacher(String type, int x, int y){
        String name = "Math Teacher";
        int color = Color.WHITE;
        int hp_max = 500;
        Attack a1 = new Attack("Number Crunch",                 20, 1.0,    100,true);
        Attack a2 = new Attack("Ruler Slap",                    50, 0.66,   5,  true);
        Attack a3 = new Attack("Administrative Backup",         100,0.5,    3,  true);
        Attack a4 = new Attack("Adminster AP Calculus BC Exam", 120,0.75,   2,  false);
        Attack[] attacks = {a1,a2,a3,a4};
        String winMessage = " exclaims, \"Precisely! It is so.\"";
        String loseMessage = " grades away confused and scared.";
        return new Character(name,hp_max,attacks,winMessage,loseMessage, x, y);
    }

    public static Character burgerFlipper(String type, int x, int y){
        String name = "Burger Flipper";
        int color = Color.RED;
        int hp_max = 500;
        Attack a1 = new Attack("Patty Slap",                                            15, 1.0,    100,true);
        Attack a2 = new Attack("Grease Slinger",                                        70, 0.66,   5,  true);
        Attack a3 = new Attack("Deliver Wrong Order",                                   40, 0.7,    8,  true);
        Attack a4 = new Attack("Ultimate Double-Double with Fries Caloric Meltdown",     120,0.8,    2,  false);
        Attack[] attacks = {a1,a2,a3,a4};
        String winMessage = " takes a day off to work on his poetry.";
        String loseMessage = " notices a grease stain on his shirt.";
        return new Character(name,hp_max,attacks,winMessage,loseMessage, x, y);
    }

    public static Character belowAverageStudent(String type, int x, int y){
        String name = "Below Average Student";
        int color = Color.YELLOW;
        int hp_max = 1;
        Attack a1 = new Attack("Scratch Face",  10,     1.0,    100,true);
        Attack a2 = new Attack("Viral TikTok",  10000,  0.05,   5,  true);
        Attack a3 = new Attack("Cry",           1,      1.0,    10, true);
        Attack a4 = new Attack("Drop out",      100000, 1.0,    1,  false);
        Attack[] attacks = {a1,a2,a3,a4};
        String winMessage = " smiels :)";
        String loseMessage = " frowns :(";
        return new Character(name,hp_max,attacks,winMessage,loseMessage, x, y);
    }

    public static Character organDonor(String type, int x, int y){
        String name = "Organ Donor";
        int color = Color.YELLOW;
        int hp_max = 200;
        Attack a1 = new Attack("Generate Sympathy",                             50,   0.9, 100,true);
        Attack a2 = new Attack("Give back to community",                        -50,  0.8, 5,  true);
        Attack a3 = new Attack("Show off liscence",                             0,    1.0, 2,  true);
        Attack a4 = new Attack("Donate organ - they now owe you their life",    10000,1.0, 1,  false);
        Attack[] attacks = {a1,a2,a3,a4};
        String winMessage = " smirks, thinking about how happy someone else is.";
        String loseMessage = " drinks away their sorrows, then dies of alcohol poisoning.";
        return new Character(name,hp_max,attacks,winMessage,loseMessage,  x, y);
    }

    public static Character wickedWitch(String type, int x, int y){
        String name = "Wicked Witch";
        int color = Color.YELLOW;
        int hp_max = 400;
        Attack a1 = new Attack("Magical Wand Casting Spell",                                                                    20,   1.0, 100,true);
        Attack a2 = new Attack("The Cackle",                                                                                    10,   1.0, 10, true);
        Attack a3 = new Attack("Broomstick to the booty",                                                                       100,  0.5, 4,  true);
        Attack a4 = new Attack("pack of 40 great wolves, a swarm of black bees, a flock of 40 crows, and an army of Winkies",   10000,0.6, 1,  false);
        Attack[] attacks = {a1,a2,a3,a4};
        String winMessage = " flys away cackling to herself.";
        String loseMessage = " drowns in a well of personal dispair.";
        return new Character(name,hp_max,attacks,winMessage,loseMessage, x, y);
    }


    public static Character characterList(String type, int characterNumber){
        int x = 3;
        int y = 3;
        return characterList(type, characterNumber, x, y);
        }


    public static Character characterList(String type, int characterNumber, int x, int y){
        switch (characterNumber) {
            case 0:
                return mathTeacher(type, x, y);
            case 1:
                return burgerFlipper(type, x, y);
            case 2:
                return belowAverageStudent(type, x, y);
            case 3:
                return organDonor(type, x, y);
            case 4:
                return wickedWitch(type, x, y);
            default:
                return mathTeacher(type, x,y);
        }
    }
}
