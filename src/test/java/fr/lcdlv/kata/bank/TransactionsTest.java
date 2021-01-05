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
    public void recordShouldAddTransactionToHistorySize() {
        Transactions transactions = new Transactions();
        Transaction transaction = new Transaction(Money.of(0));

        transactions.record(transaction);

        Assertions.assertEquals(1, transactions.size());
    }

    @Test
    public void equalsWithAnEqualHistoryShouldReturnTrue() {
        Transactions transactions = new Transactions();
        Transaction transaction = new ReturnTrueOnEqualsStubTransaction();

        transactions.record(transaction);
        transactions.record(transaction);

        Assertions.assertEquals(expectedHistory(), transactions);
    }

    private Transactions expectedHistory() {
        Transactions transactions = new Transactions();
        Transaction transaction = new ReturnTrueOnEqualsStubTransaction();

        transactions.record(transaction);
        transactions.record(transaction);

        return transactions;
    }

    @Test
    public void toStringHistory() {
        Transactions transactions = new Transactions();

        transactions.record(new Transaction(Money.of(10)));
        transactions.record(new Transaction(Money.of(10.50)));
        transactions.record(new Transaction(Money.of(20.23)));
        transactions.record(new Transaction(Money.of(5.19).opposite()));
        transactions.record(new Transaction(Money.of(10.08).opposite()));
        transactions.record(new Transaction(Money.of(10.943)));

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
