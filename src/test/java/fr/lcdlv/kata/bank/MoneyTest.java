package fr.lcdlv.kata.bank;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MoneyTest {

    @Test
    public void isEqualToSameAmountOfMoney() {
        Money one = Money.of(1);

        Money anotherOne = Money.of(1);

        assertEquals(one, anotherOne);
    }

    @Test
    public void isEqualToAnotherAmountOfMoney() {
        Money one = Money.of(1);

        Money two = Money.of(2);

        assertNotEquals(one, two);
    }

    @Test
    public void addMoney() {
        Money one = Money.of(1);

        Money two = one.add(one);

        assertEquals(Money.of(2), two);
    }

    @Test
    public void compareToEquivalentMoney() {
        Money one = Money.of(1);

        int c = one.compareTo(one);

        assertEquals(0, c);
    }

    @Test
    public void isBiggerThanShouldReturnTrue() {
        Money one = Money.of(1);
        Money two = Money.of(2);

        boolean isBiggerOrEqual = two.isBiggerThanOrEquals(one);

        assertTrue(isBiggerOrEqual);
    }
}
