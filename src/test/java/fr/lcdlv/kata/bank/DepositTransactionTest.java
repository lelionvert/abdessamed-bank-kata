package fr.lcdlv.kata.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DepositTransactionTest {

    @Test
    public void applyOnMoney() {
        Transaction depositTransaction = new DepositTransaction(Money.of(10));

        Money money = depositTransaction.applyOn(Money.ZERO);

        assertEquals(Money.of(10), money);
    }

    @Test
    public void manyApplyOnMoney() {
        Transaction depositTransaction = new DepositTransaction(Money.of(10));

        Money money = depositTransaction.applyOn(Money.ZERO);
        money = depositTransaction.applyOn(money);
        money = depositTransaction.applyOn(money);
        money = depositTransaction.applyOn(money);

        assertEquals(Money.of(40), money);
    }

    @Test
    public void DepositTransactionToString() {
        Transaction depositTransaction = new DepositTransaction(Money.of(10));

        String actual = depositTransaction.toString();

        assertEquals("Transaction : 10,00€", actual);
    }
}
