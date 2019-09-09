package com.mikeangel.diceroller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    public int numberOfDice = 1;
    public ArrayList<Integer> dice = new ArrayList<Integer>(numberOfDice);
    public int maxNumberOfDice = 9;
    public int colorScheme = 0;
    public int[] DiceIds = {
            R.id.die_1,
            R.id.die_2,
            R.id.die_3,
            R.id.die_4,
            R.id.die_5,
            R.id.die_6,
            R.id.die_7,
            R.id.die_8,
            R.id.die_9,
    };
    public int[] diceImages = {
            R.drawable.ic_die_1,
            R.drawable.ic_die_2,
            R.drawable.ic_die_3,
            R.drawable.ic_die_4,
            R.drawable.ic_die_5,
            R.drawable.ic_die_6,
            R.drawable.ic_invis,
    };

    public int[] diceImagesAlt = {
            R.drawable.ic_die_1,
            R.drawable.ic_die_2_alt,
            R.drawable.ic_die_3_alt,
            R.drawable.ic_die_4,
            R.drawable.ic_die_5,
            R.drawable.ic_die_6_alt,
            R.drawable.ic_invis,
    };

    // TODO: Hold button that highlights and makes die not roll (if not on hold)
    // TODO: Settings page with: colors

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for(int i = 0; i < maxNumberOfDice; i++){
            dice.add(6);
        }
    }

    /** Called when the user taps the Color button */
    public void changeColor(View view) {
        colorScheme += 1;
        colorScheme %= 3;
        ConstraintLayout cl = findViewById(R.id.main);
        if (colorScheme == 0){
            diceImages[0] = R.drawable.ic_die_1;
            diceImages[1] = R.drawable.ic_die_2;
            diceImages[2] = R.drawable.ic_die_3;
            diceImages[3] = R.drawable.ic_die_4;
            diceImages[4] = R.drawable.ic_die_5;
            diceImages[5] = R.drawable.ic_die_6;
            diceImagesAlt[0] = R.drawable.ic_die_1;
            diceImagesAlt[1] = R.drawable.ic_die_2_alt;
            diceImagesAlt[2] = R.drawable.ic_die_3_alt;
            diceImagesAlt[3] = R.drawable.ic_die_4;
            diceImagesAlt[4] = R.drawable.ic_die_5;
            diceImagesAlt[5] = R.drawable.ic_die_6_alt;
            cl.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        }
        if (colorScheme == 1){
            diceImages[0] = R.drawable.ic_blue_die_1;
            diceImages[1] = R.drawable.ic_blue_die_2;
            diceImages[2] = R.drawable.ic_blue_die_3;
            diceImages[3] = R.drawable.ic_blue_die_4;
            diceImages[4] = R.drawable.ic_blue_die_5;
            diceImages[5] = R.drawable.ic_blue_die_6;
            diceImagesAlt[0] = R.drawable.ic_blue_die_1;
            diceImagesAlt[1] = R.drawable.ic_blue_die_2_alt;
            diceImagesAlt[2] = R.drawable.ic_blue_die_3_alt;
            diceImagesAlt[3] = R.drawable.ic_blue_die_4;
            diceImagesAlt[4] = R.drawable.ic_blue_die_5;
            diceImagesAlt[5] = R.drawable.ic_blue_die_6_alt;
            cl.setBackgroundColor(getResources().getColor(R.color.colorPink));
        }
        if (colorScheme == 2){
            diceImages[0] = R.drawable.ic_red_die_1;
            diceImages[1] = R.drawable.ic_red_die_2;
            diceImages[2] = R.drawable.ic_red_die_3;
            diceImages[3] = R.drawable.ic_red_die_4;
            diceImages[4] = R.drawable.ic_red_die_5;
            diceImages[5] = R.drawable.ic_red_die_6;
            diceImagesAlt[0] = R.drawable.ic_red_die_1;
            diceImagesAlt[1] = R.drawable.ic_red_die_2_alt;
            diceImagesAlt[2] = R.drawable.ic_red_die_3_alt;
            diceImagesAlt[3] = R.drawable.ic_red_die_4;
            diceImagesAlt[4] = R.drawable.ic_red_die_5;
            diceImagesAlt[5] = R.drawable.ic_red_die_6_alt;
            cl.setBackgroundColor(getResources().getColor(R.color.colorTableGreen));
        }
        refreshDice(numberOfDice, dice);
    }

    /** Called when the user taps the addDice button */
    public void addDice(View view){
        if(numberOfDice < maxNumberOfDice) {
            numberOfDice += 1;
            refreshDice(numberOfDice, dice);
        }
    }

    /** Called when the user taps the subtractDice button */
    public void subtractDice(View view){
        if(numberOfDice > 1) {
            numberOfDice -= 1;
            refreshDice(numberOfDice, dice);
        }
    }


    /** Called when the user taps the Roll button */
    public void rollDice(View view) {
        Random rand = new Random();
        for (int i =0; i < numberOfDice; i++){
            int n = rand.nextInt(6);
            n += 1;
            dice.set(i, n);
        }
        refreshDice(numberOfDice, dice);
        changeTotal(numberOfDice, dice);
    }

    public void changeTotal(int numberOfDice, ArrayList<Integer> dice){
        Integer diceTotal = 0;
        for (int i = 0; i < numberOfDice; i++){
            diceTotal += dice.get(i);
        }
        final TextView textViewToChange = (TextView) findViewById(R.id.total);
        if(numberOfDice == 1){
            textViewToChange.setText("");
        }
        else{
            String total = getResources().getString(R.string.total) + " " + diceTotal.toString();
            textViewToChange.setText(total);
        }
    }

    public void refreshDice(int numberOfDice, ArrayList<Integer> dice){
        Animation shake;
        shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);

        for (int i = 0; i < numberOfDice; i++){
            ImageView dieToEdit = findViewById(DiceIds[i]);
            int diceValue = dice.get(i);
            boolean useAlt = false;
            if(diceValue == 2 || diceValue == 3 || diceValue == 6){
                Random rand = new Random();
                useAlt = rand.nextBoolean();
            }
            if(useAlt){
                dieToEdit.setImageResource(diceImagesAlt[diceValue-1]);
            }
            else{
                dieToEdit.setImageResource(diceImages[diceValue-1]);
            }
            dieToEdit.startAnimation(shake);
//            Drawable highlight = getResources().getDrawable( R.drawable.highlight);
//            dieToEdit.setBackground(highlight);
        }
        if(numberOfDice < maxNumberOfDice){
            for(int i = numberOfDice; i < maxNumberOfDice; i++){
                ImageView dieToEdit = findViewById(DiceIds[i]);
                dieToEdit.setImageResource(diceImages[6]);
            }
        }
    }
}
