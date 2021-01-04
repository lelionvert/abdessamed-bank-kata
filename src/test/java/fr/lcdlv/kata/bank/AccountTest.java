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

    @Test
    public void getBalanceShouldReturnCurrentAccountBalance2() {
        Account account = new Account(Money.of(1));

        Money balance = account.getBalance();

        Assertions.assertEquals(Money.of(1), balance);
    }

    @Test
    public void depositMoneyOnAccount() {
        Account account = new Account();

        account.deposit(Money.of(1));

        Money balance = account.getBalance();
        Assertions.assertEquals(Money.of(1), balance);
    }
}
