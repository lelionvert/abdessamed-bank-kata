package fr.lcdlv.kata.bank;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AccountTest {

    @Test
    public void getBalanceShouldReturnCurrentAccountBalance() {
        Account account = AccountFactory.wrap(Money.of(1));

        Money balance = account.getBalance();

        Assertions.assertEquals(Money.of(1), balance);
    }

    @Test
    public void depositMoneyShouldAddMoneyToCurrentBalance() throws MinimumMoneyAllowedException {
        Account account = AccountFactory.empty();

        account.deposit(Money.of(1));

        Money balance = account.getBalance();
        Assertions.assertEquals(Money.of(1), balance);
    }

    @Test
    public void depositMoneyUnderMinimumAllowedShouldThrowMinimumMoneyAllowedException() {
        Account account = AccountFactory.empty();

        Assertions.assertThrows(MinimumMoneyAllowedException.class, () -> account.deposit(Money.ZERO));
    }
}
