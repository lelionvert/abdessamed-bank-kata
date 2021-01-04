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
}
