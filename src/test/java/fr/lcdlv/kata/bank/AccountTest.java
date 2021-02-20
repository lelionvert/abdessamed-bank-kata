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

        var depositOperation = new DepositOperation(Money.of(1));
        account.apply(depositOperation);

        Money balance = account.balance();
        assertEquals(Money.of(1), balance);
    }

    @Test
    public void depositMoneyUnderMinimumAllowedShouldThrowMinimumMoneyAllowedException() {
        Account account = AccountFactory.empty();

        assertThrows(MinimumMoneyAllowedException.class, () -> {
            var depositOperation = new DepositOperation(Money.ZERO);
            account.apply(depositOperation);
        });
    }
}
