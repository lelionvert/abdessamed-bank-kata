package fr.lcdlv.kata.bank;

import java.util.ArrayList;
import java.util.List;

public class History {

    List<Transaction> transactions = new ArrayList<>();

    public int size() {
        return transactions.size();
    }

    public void record(Transaction transaction) {
        transactions.add(transaction);
    }
}
