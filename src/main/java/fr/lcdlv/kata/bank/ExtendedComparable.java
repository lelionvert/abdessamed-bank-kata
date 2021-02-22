package fr.lcdlv.kata.bank;

import java.util.function.Predicate;

public interface ExtendedComparable<T> extends Comparable<T> {

    default boolean compareTo(T o, LogicOperation operation) {
        var result = compareTo(o);
        return operation.test(result);
    }

    enum LogicOperation {
        EQUAL_TO(result -> result == 0),
        NOT_EQUAL_TO(result -> result != 0),
        BIGGER_THAN(result -> result > 0),
        BIGGER_THAN_OR_EQUAL_TO(result -> BIGGER_THAN.test(result) || EQUAL_TO.test(result)),
        LESS_THAN(result -> result < 0),
        LESS_THAN_OR_EQUAL_TO(result -> LESS_THAN.test(result) || LESS_THAN.test(result));

        private final Predicate<Integer> predicate;

        LogicOperation(final Predicate<Integer> predicate) {
            this.predicate = predicate;
        }

        public boolean test(int result) {
            return predicate.test(result);
        }
    }
}
