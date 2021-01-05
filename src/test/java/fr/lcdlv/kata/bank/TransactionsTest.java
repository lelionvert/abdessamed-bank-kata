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
        BaseTransaction baseTransaction = new DepositTransaction(Money.of(0));

        transactions.record(baseTransaction);

        Assertions.assertEquals(1, transactions.size());
    }

    @Test
    public void equalsWithAnEqualTransactionsShouldReturnTrue() {
        Transactions transactions = new Transactions();
        BaseTransaction baseTransaction = new ReturnTrueOnEqualsStubBaseTransaction();

        transactions.record(baseTransaction);
        transactions.record(baseTransaction);

        Assertions.assertEquals(expectedTransactions(), transactions);
    }

    private Transactions expectedTransactions() {
        Transactions transactions = new Transactions();
        BaseTransaction baseTransaction = new ReturnTrueOnEqualsStubBaseTransaction();

        transactions.record(baseTransaction);
        transactions.record(baseTransaction);

        return transactions;
    }

    @Test
    public void sumOnEmptyTransactionsShouldReturnZero() {
        Transactions transactions = new Transactions();

        Money zero = transactions.sum();

        Assertions.assertEquals(Money.of(0), zero);
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
