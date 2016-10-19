package com.dungeonstory.backend;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.dungeonstory.backend.Dice.DiceType;

public class DiceTest {

    @Test
    public void testDice() {
        Dice dice = new Dice(1, DiceType.D20);
        assertEquals("1d20", dice.toString());

        dice = new Dice(2, DiceType.D12, 4);
        assertEquals("2d12+4", dice.toString());

        dice = new Dice(3, DiceType.D4, -1);
        assertEquals("3d4-1", dice.toString());

        dice = new Dice(3, DiceType.D8, 0);
        assertEquals("3d8", dice.toString());
    }

    @Test
    public void testDiceString() {
        Dice dice = new Dice("1d20");
        assertEquals("1d20", dice.toString());

        dice = new Dice("2d12+4");
        assertEquals("2d12+4", dice.toString());

        dice = new Dice("3d4-1");
        assertEquals("3d4-1", dice.toString());

        dice = new Dice("3d8+0");
        assertEquals("3d8", dice.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidDice1() {
        new Dice("1243");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidDice2() {
        new Dice("1d40");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidDice3() {
        new Dice("1d8a");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidDice4() {
        new Dice("1d8-a");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidDice5() {
        new Dice("1d8-1+2");
    }

    @Test
    public void testRoll() {
        Dice dice = new Dice(1, DiceType.D20);
        for (int i = 0; i < 100; i++) {
            int result = dice.roll();
            System.out.println(result);
            assertTrue(result > 0 && result <= 20);
        }

        dice = new Dice("2d12+4");
        for (int i = 0; i < 100; i++) {
            int result = dice.roll();
            System.out.println(result);
            assertTrue(result >= 6 && result <= 28);
        }

        dice = new Dice("3d4-1");
        for (int i = 0; i < 100; i++) {
            int result = dice.roll();
            System.out.println(result);
            assertTrue(result >= 2 && result <= 11);
        }
    }

}
