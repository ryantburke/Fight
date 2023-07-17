package com.burke.fight;

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

    public static Character mathTeacher(int x, int y){
        String name = "Math Teacher";
        int[] imageIds = {R.drawable.im_math_teacher1, R.drawable.im_math_teacher2, R.drawable.im_math_teacher3, R.drawable.im_math_teacher4};
        int hp_max = 500;
        Attack a1 = new Attack("Number Crunch",                 20, 1.0,    100,true);
        Attack a2 = new Attack("Ruler Slap",                    50, 0.66,   5,  true);
        Attack a3 = new Attack("Administrative Backup",         100,0.5,    3,  true);
        Attack a4 = new Attack("Adminster AP Calculus BC Exam", 120,0.75,   2,  false);
        Attack[] attacks = {a1,a2,a3,a4};
        String winMessage = " exclaims, \"Precisely! It is so.\"";
        String loseMessage = " grades away confused and scared.";
        return new Character(name,imageIds,hp_max,attacks,winMessage,loseMessage, x, y);
    }

    public static Character burgerFlipper(int x, int y){
        String name = "Burger Flipper";
        int[] imageIds = {R.drawable.im_burger_flipper1, R.drawable.im_burger_flipper2, R.drawable.im_burger_flipper3, R.drawable.im_burger_flipper4};
        int hp_max = 500;
        Attack a1 = new Attack("Patty Slap",                                            15, 1.0,    100,true);
        Attack a2 = new Attack("Grease Slinger",                                        70, 0.66,   5,  true);
        Attack a3 = new Attack("Deliver Wrong Order",                                   40, 0.7,    8,  true);
        Attack a4 = new Attack("Ultimate Double-Double with Fries Caloric Meltdown",     120,0.8,    2,  false);
        Attack[] attacks = {a1,a2,a3,a4};
        String winMessage = " takes a day off to work on his poetry.";
        String loseMessage = " notices a grease stain on his shirt.";
        return new Character(name,imageIds,hp_max,attacks,winMessage,loseMessage, x, y);
    }

    public static Character belowAverageStudent(int x, int y){
        String name = "Below Average Student";
        int[] imageIds = {R.drawable.im_student1, R.drawable.im_student2, R.drawable.im_student3, R.drawable.im_student4};
        int hp_max = 1;
        Attack a1 = new Attack("Scratch Face",  10,     1.0,    100,true);
        Attack a2 = new Attack("Viral TikTok",  10000,  0.05,   5,  true);
        Attack a3 = new Attack("Cry",           1,      1.0,    10, true);
        Attack a4 = new Attack("Drop out",      100000, 1.0,    1,  false);
        Attack[] attacks = {a1,a2,a3,a4};
        String winMessage = " smiels :)";
        String loseMessage = " frowns :(";
        return new Character(name,imageIds,hp_max,attacks,winMessage,loseMessage, x, y);
    }

    public static Character organDonor(int x, int y){
        String name = "Organ Donor";
        int[] imageIds = {R.drawable.im_organ_donor1, R.drawable.im_organ_donor2, R.drawable.im_organ_donor3, R.drawable.im_organ_donor4};
        int hp_max = 200;
        Attack a1 = new Attack("Generate Sympathy",                             50,   0.9, 100,true);
        Attack a2 = new Attack("Give back to community",                        -50,  0.8, 5,  true);
        Attack a3 = new Attack("Show off liscence",                             0,    1.0, 2,  true);
        Attack a4 = new Attack("Donate organ - they now owe you their life",    10000,1.0, 1,  false);
        Attack[] attacks = {a1,a2,a3,a4};
        String winMessage = " smirks, thinking about how happy someone else is.";
        String loseMessage = " drinks away their sorrows, then dies of alcohol poisoning.";
        return new Character(name,imageIds,hp_max,attacks,winMessage,loseMessage,  x, y);
    }

    public static Character wickedWitch(int x, int y){
        String name = "Wicked Witch";
        int[] imageIds = {R.drawable.im_wicked_witch1, R.drawable.im_wicked_witch2, R.drawable.im_wicked_witch3, R.drawable.im_wicked_witch4};
        int hp_max = 400;
        Attack a1 = new Attack("Magical Wand Casting Spell",                                                                    20,   1.0, 100,true);
        Attack a2 = new Attack("The Cackle",                                                                                    10,   1.0, 10, true);
        Attack a3 = new Attack("Broomstick to the booty",                                                                       100,  0.5, 4,  true);
        Attack a4 = new Attack("pack of 40 great wolves, a swarm of black bees, a flock of 40 crows, and an army of Winkies",   10000,0.6, 1,  false);
        Attack[] attacks = {a1,a2,a3,a4};
        String winMessage = " flys away cackling to herself.";
        String loseMessage = " drowns in a well of personal dispair.";
        return new Character(name,imageIds,hp_max,attacks,winMessage,loseMessage, x, y);
    }


    public static Character characterList(int characterNumber){
        int x = 3;
        int y = 3;
        return characterList(characterNumber, x, y);
        }


    public static Character characterList(int characterNumber, int x, int y){
        switch (characterNumber) {
            case 0:
                return mathTeacher(x, y);
            case 1:
                return burgerFlipper(x, y);
            case 2:
                return belowAverageStudent(x, y);
            case 3:
                return organDonor(x, y);
            case 4:
                return wickedWitch(x, y);
            default:
                return mathTeacher(x,y);
        }
    }
}
