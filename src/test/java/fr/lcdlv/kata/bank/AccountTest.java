package fr.lcdlv.kata.bank;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AccountTest {

    @Test
    public void getBalanceShouldReturnCurrentAccountBalance() {
        Account account = new Account();

        Money balance = account.getBalance();

        Assertions.assertEquals(Money.of(0), balance);
    }
}
