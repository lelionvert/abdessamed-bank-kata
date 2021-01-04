package fr.lcdlv.kata.bank;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DepositSpec {

    @Test
    public void depositMoneyOnAccount() throws MinimumMoneyAllowedException {
        Money money = Money.of(1);
        Account account = new Account(Money.of(0), new History());

        account.deposit(money);

        Money balance = account.getBalance();
        Assertions.assertEquals(Money.of(1), balance);
    }

    @Test
    public void depositMoneyUnderMinimumAllowedOnAccount() {
        Money money = Money.of(0);
        Account account = new Account(Money.of(0), new History());

        Assertions.assertThrows(MinimumMoneyAllowedException.class, () -> account.deposit(money));
    }
}
