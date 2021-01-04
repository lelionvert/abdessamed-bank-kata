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
        Transaction transaction = new DummyTransaction();

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
    public void sumEmptyHistory() {
        History history = new History();

        Money actual = history.sum();

        Assertions.assertEquals(Money.of(0), actual);
    }

    @Test
    public void toStringHistory() {
        History history = new History();

        history.record(new DepositTransaction(Money.of(10)));
        history.record(new DepositTransaction(Money.of(10.50)));
        history.record(new DepositTransaction(Money.of(20.23)));
        history.record(new WithdrawTransaction(Money.of(5.19)));
        history.record(new WithdrawTransaction(Money.of(10.08)));
        history.record(new DepositTransaction(Money.of(10.943)));

        Assertions.assertEquals(
                """
                        History size : 6
                        Deposit transaction : +10,00€
                        Deposit transaction : +10,50€
                        Deposit transaction : +20,23€
                        Withdraw transaction : -5,19€
                        Withdraw transaction : -10,08€
                        Deposit transaction : +10,94€""",
                history.toString());
    }
}
