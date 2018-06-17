package com.dungeonstory.backend.rules;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import com.dungeonstory.backend.Configuration;

/**
 * Dice class.
 * Valid dices are d4, d6, d8, d10, d12, d20 and d100.
 * Positive or negative modifier could be added to the dice (ex: 2d4+2)
 *
 */
public class Dice {

    public enum DiceType {
        D4(4), D6(6), D8(8), D10(10), D12(12), D20(20), D100(100);

        int dice;

        private DiceType(int dice) {
            this.dice = dice;
        }

        public int getDice() {
            return dice;
        }
    }

    private final int nbDice;

    private final DiceType type;

    private int modifier = 0;

    private Random random = new Random();

    public Dice(int nbDice, DiceType type) {
        this.nbDice = nbDice;
        this.type = type;
    }

    public Dice(int nbDice, DiceType type, Integer modifier) {
        this(nbDice, type);
        this.modifier = modifier;
    }

    public Dice(String dice) {
        //        @Pattern(regexp = "(\\d+)(d\\d+)([\\+\\-]\\d+)?")

        String pattern1 = "(\\d+)(d4|d6|d8|d10|d12|d20|d100)$";
        String pattern2 = "(\\d+)(d4|d6|d8|d10|d12|d20|d100)([\\+\\-]\\d+)$";

        // Create a Pattern object
        Pattern r1 = Pattern.compile(pattern1);
        Pattern r2 = Pattern.compile(pattern2);

        // Now create matcher object.
        Matcher m1 = r1.matcher(dice);
        Matcher m2 = r2.matcher(dice);
        if (m1.find()) {
            if (Configuration.getInstance().isDebug()) {
                System.out.println("Found value: " + m1.group(0));
                System.out.println("Found value: " + m1.group(1));
                System.out.println("Found value: " + m1.group(2));
            }
            this.nbDice = Integer.parseInt(m1.group(1));
            this.type = DiceType.valueOf(m1.group(2).toUpperCase());
        } else if (m2.find()) {
            if (Configuration.getInstance().isDebug()) {
                System.out.println("Found value: " + m2.group(0));
                System.out.println("Found value: " + m2.group(1));
                System.out.println("Found value: " + m2.group(2));
                System.out.println("Found value: " + m2.group(3));
            }
            this.nbDice = Integer.parseInt(m2.group(1));
            this.type = DiceType.valueOf(m2.group(2).toUpperCase());
            this.modifier = Integer.valueOf(m2.group(3));
        } else {
            System.out.println("NO MATCH, Dé invalide " + dice);
            throw new IllegalArgumentException("Dé invalide " + dice);
        }
    }

    public int roll() {
        IntStream intStream = random.ints(nbDice, 1, type.getDice() + 1);
        int result = intStream.sum();
        if (modifier != 0) {
            result += modifier;
        }
        return result;
    }

    public int getNbDice() {
        return nbDice;
    }

    public DiceType getType() {
        return type;
    }

    public int getModifier() {
        return modifier;
    }
    
    public void addModifier(int modifier) {
        this.modifier += modifier;
    }

    @Override
    public String toString() {
        String result = nbDice + type.toString().toLowerCase();
        if (modifier > 0) {
            result += "+" + String.valueOf(modifier);
        } else if (modifier < 0) {
            result += String.valueOf(modifier);
        }
        return result;
    }

}
