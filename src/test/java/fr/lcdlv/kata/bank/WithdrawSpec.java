package fr.lcdlv.kata.bank;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WithdrawSpec {

    @Test
    public void withdrawWithoutOverdraft() throws OverdraftException {
        Account account = new Account(Money.of(50));

        account.withdraw(Money.of(10));

        Money balance = account.getBalance();
        assertEquals(balance, Money.of(40));
    }

    @Test
    public void withdrawWithOverdraft() {
        Account account = new Account(Money.of(0));

        assertThrows(OverdraftException.class, () -> account.withdraw(Money.of(10)));
    }
}
