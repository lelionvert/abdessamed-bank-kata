package fr.lcdlv.kata.bank;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AccountTest {

    @Test
    public void getBalanceShouldReturnCurrentAccountBalance() {
        Account account = new Account(Money.of(1));

        Money balance = account.getBalance();

        Assertions.assertEquals(Money.of(1), balance);
    }

    @Test
    public void depositMoney() throws MinimumMoneyException {
        Account account = new Account(Money.of(0));

        account.deposit(Money.of(1));

        Money balance = account.getBalance();
        Assertions.assertEquals(Money.of(1), balance);
    }

    @Test
    public void depositMoneyUnderMinimumAllowed() {
        Money money = Money.of(0);
        Account account = new Account(Money.of(0));

        Assertions.assertThrows(MinimumMoneyException.class, () -> account.deposit(money));
    }
}
