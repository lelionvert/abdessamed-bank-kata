package fr.lcdlv.kata.bank;

public interface ExtendedComparable<T> extends Comparable<T> {

    default boolean compareTo(T o, LogicOperation operation) {
        var result = compareTo(o);
        return operation.test(result);
    }

    enum LogicOperation {
        EQUAL_TO {
            @Override
            public boolean test(int result) {
                return result == 0;
            }
        },
        NOT_EQUAL_TO {
            @Override
            public boolean test(int result) {
                return result != 0;
            }
        },
        BIGGER_THAN {
            @Override
            public boolean test(int result) {
                return result > 0;
            }
        },
        BIGGER_THAN_OR_EQUAL_TO {
            @Override
            public boolean test(int result) {
                return BIGGER_THAN.test(result) || EQUAL_TO.test(result);
            }
        },
        LESS_THAN {
            @Override
            public boolean test(int result) {
                return result < 0;
            }
        },
        LESS_THAN_OR_EQUAL_TO {
            @Override
            public boolean test(int result) {
                return LESS_THAN.test(result) || LESS_THAN.test(result);
            }
        };

        public abstract boolean test(int result);
    }
}
