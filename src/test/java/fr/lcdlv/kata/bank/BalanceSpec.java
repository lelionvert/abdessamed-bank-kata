package fr.lcdlv.kata.bank;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BalanceSpec {

    @Test
    public void getBalanceInReadableForm() {
        Account account = new Account(Money.of(157.83), new History());

        Money balance = account.getBalance();

        Assertions.assertEquals("157,83€", balance.toString());
    }
}
