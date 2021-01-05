package fr.lcdlv.kata.bank;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TransactionsTest {

    @Test
    public void creationShouldReturnAnEmptySize() {
        Transactions transactions = new Transactions();

        int size = transactions.size();

        Assertions.assertEquals(0, size);
    }

    @Test
    public void recordShouldAddTransactionToTransactionsSize() {
        Transactions transactions = new Transactions();
        Transaction transaction = new DepositTransaction(Money.ZERO);

        transactions.record(transaction);

        Assertions.assertEquals(1, transactions.size());
    }

    @Test
    public void equalsWithAnEqualTransactionsShouldReturnTrue() {
        Transactions transactions = new Transactions();
        Transaction transaction = new ReturnTrueOnEqualsStubTransaction();

        transactions.record(transaction);
        transactions.record(transaction);

        Assertions.assertEquals(expectedTransactions(), transactions);
    }

    private Transactions expectedTransactions() {
        Transactions transactions = new Transactions();
        Transaction transaction = new ReturnTrueOnEqualsStubTransaction();

        transactions.record(transaction);
        transactions.record(transaction);

        return transactions;
    }

    @Test
    public void sumOnEmptyTransactionsShouldReturnZero() {
        Transactions transactions = new Transactions();

        Money zero = transactions.sum();

        Assertions.assertEquals(Money.ZERO, zero);
    }

    @Test
    public void sumOnFilledTransactionsShouldReturnTheSumOfAllTransactions() {
        Transactions transactions = TransactionsFactory.wrap(Money.of(10), Money.of(20), Money.of(30));

        Money sixty = transactions.sum();

        Assertions.assertEquals(Money.of(60), sixty);
    }

    @Test
    public void transactionsToString() {
        Transactions transactions = new Transactions();

        transactions.record(new DepositTransaction(Money.of(10)));
        transactions.record(new DepositTransaction(Money.of(10.50)));
        transactions.record(new DepositTransaction(Money.of(20.23)));
        transactions.record(new WithdrawTransaction(Money.of(5.19)));
        transactions.record(new WithdrawTransaction(Money.of(10.08)));
        transactions.record(new DepositTransaction(Money.of(10.943)));

        Assertions.assertEquals(
                """
                        History size : 6
                        Transaction : 10,00€
                        Transaction : 10,50€
                        Transaction : 20,23€
                        Transaction : -5,19€
                        Transaction : -10,08€
                        Transaction : 10,94€""",
                transactions.toString());
    }
}
