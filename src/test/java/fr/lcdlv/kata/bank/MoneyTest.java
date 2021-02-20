package fr.lcdlv.kata.bank;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

        assertEquals(Money.ZERO, zero);
    }

    @Test
    public void multiplyByAConstant() {
        Money one = Money.of(1);

        Money ten = one.multiplyBy(10);

        assertEquals(Money.of(10), ten);
    }

    @Test
    public void compareToEquivalentMoney() {
        Money one = Money.of(1);
        Money anotherOne = Money.of(1);

        int c = one.compareTo(anotherOne);

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
    public void isBiggerThanShouldReturnTrue() {
        Money one = Money.of(1);
        Money two = Money.of(2);

        boolean isBiggerOrEqual = two.isBiggerThan(one);

        assertTrue(isBiggerOrEqual);
    }

    @Test
    public void isBiggerThanShouldReturnFalse() {
        Money one = Money.of(1);
        Money two = Money.of(1);

        boolean isBiggerOrEqual = two.isBiggerThan(one);

        assertFalse(isBiggerOrEqual);
    }

    @Test
    public void isLessThanShouldReturnTrue() {
        Money one = Money.of(1);
        Money two = Money.of(2);

        boolean isLessThanOrEqualTo = one.isLessThan(two);

        assertTrue(isLessThanOrEqualTo);
    }

    @Test
    public void isLessThanOrEqualToShouldReturnTrue() {
        Money one = Money.of(1);
        Money two = Money.of(2);

        boolean isLessThanOrEqualTo = one.isLessThanOrEqualTo(two);

        assertTrue(isLessThanOrEqualTo);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "10,00€;10.00",
            "10,50€;10.50",
            "20,23€;20.23",
            "5,19€;5.19",
            "10,08€;10.08",
            "10,94€;10.94",
    }, delimiter = ';')
    public void toStringFormat(String expected, double moneyAsDouble) {
        Money money = Money.of(moneyAsDouble);

        String actual = money.toString();

        assertEquals(expected, actual);
    }
}
