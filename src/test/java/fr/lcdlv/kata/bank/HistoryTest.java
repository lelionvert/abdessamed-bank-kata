package fr.lcdlv.kata.bank;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HistoryTest {

    @Test
    public void creationShouldReturnAnEmptySize() {
        History history = new History();

        int size = history.size();

        Assertions.assertEquals(0, size);
    }

    @Test
    public void recordShouldAddTransactionToHistorySize() {
        History history = new History();
        Transaction transaction = new Transaction(Money.of(0));

        history.record(transaction);

        Assertions.assertEquals(1, history.size());
    }

    @Test
    public void equalsWithAnEqualHistoryShouldReturnTrue() {
        History history = new History();
        Transaction transaction = new ReturnTrueOnEqualsStubTransaction();

        history.record(transaction);
        history.record(transaction);

        Assertions.assertEquals(expectedHistory(), history);
    }

    private History expectedHistory() {
        History history = new History();
        Transaction transaction = new ReturnTrueOnEqualsStubTransaction();

        history.record(transaction);
        history.record(transaction);

        return history;
    }

    @Test
    public void toStringHistory() {
        History history = new History();

        history.record(new Transaction(Money.of(10)));
        history.record(new Transaction(Money.of(10.50)));
        history.record(new Transaction(Money.of(20.23)));
        history.record(new Transaction(Money.of(5.19).opposite()));
        history.record(new Transaction(Money.of(10.08).opposite()));
        history.record(new Transaction(Money.of(10.943)));

        Assertions.assertEquals(
                """
                        History size : 6
                        Transaction : 10,00€
                        Transaction : 10,50€
                        Transaction : 20,23€
                        Transaction : -5,19€
                        Transaction : -10,08€
                        Transaction : 10,94€""",
                history.toString());
    }
}
