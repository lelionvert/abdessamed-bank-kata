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
}
