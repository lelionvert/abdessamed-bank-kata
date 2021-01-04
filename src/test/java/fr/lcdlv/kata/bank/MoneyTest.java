package fr.lcdlv.kata.bank;

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
    public void subtractMoney() {
        Money one = Money.of(1);

        Money zero = one.subtract(one);

        assertEquals(Money.of(0), zero);
    }

    @Test
    public void compareToEquivalentMoney() {
        Money one = Money.of(1);

        int c = one.compareTo(one);

        assertEquals(0, c);
    }

    @Test
    public void isBiggerThanOrEqualToShouldReturnTrue() {
        Money one = Money.of(1);
        Money two = Money.of(2);

        boolean isBiggerOrEqual = two.isBiggerThanOrEqualTo(one);

        assertTrue(isBiggerOrEqual);
    }

    @Test
    public void isLessThanShouldReturnTrue() {
        Money one = Money.of(1);
        Money two = Money.of(2);

        boolean isLessThanOrEqualTo = one.isLessThan(two);

        assertTrue(isLessThanOrEqualTo);
    }
}
