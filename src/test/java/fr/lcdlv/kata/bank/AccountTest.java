package fr.lcdlv.kata.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AccountTest {

    @Test
    public void balanceShouldReturnCurrentAccountBalance() {
        Account account = AccountFactory.wrap(Money.of(1));

        Money balance = account.balance();

        assertEquals(Money.of(1), balance);
    }

    @Test
    public void depositMoneyShouldAddMoneyToCurrentBalance() throws OperationException {
        Account account = AccountFactory.empty();

        account.deposit(Money.of(1));

        Money balance = account.balance();
        assertEquals(Money.of(1), balance);
    }

    @Test
    public void depositMoneyUnderMinimumAllowedShouldThrowMinimumMoneyAllowedException() {
        Account account = AccountFactory.empty();

        assertThrows(MinimumMoneyAllowedException.class, () -> account.deposit(Money.ZERO));
    }
}
