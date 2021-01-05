package fr.lcdlv.kata.bank;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WithdrawTransactionTest {

    @Test
    public void applyOnMoney() {
        Transaction withdrawTransaction = new WithdrawTransaction(Money.of(10));

        Money money = withdrawTransaction.applyOn(Money.of(40));

        assertEquals(Money.of(30), money);
    }

    @Test
    public void manyApplyOnMoney() {
        Transaction withdrawTransaction = new WithdrawTransaction(Money.of(10));

        Money money = withdrawTransaction.applyOn(Money.of(40));
        money = withdrawTransaction.applyOn(money);
        money = withdrawTransaction.applyOn(money);
        money = withdrawTransaction.applyOn(money);

        assertEquals(Money.ZERO, money);
    }

    @Test
    public void DepositTransactionToString() {
        Transaction withdrawTransaction = new WithdrawTransaction(Money.of(10));

        String actual = withdrawTransaction.toString();

        assertEquals("Transaction : -10,00€", actual);
    }
}
