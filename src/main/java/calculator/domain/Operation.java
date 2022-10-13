package calculator.domain;

import java.util.Arrays;
import java.util.function.BinaryOperator;

public enum Operation {
    PLUS("+", (x, y) -> x + y),
    MINUS("-", (x, y) -> x - y),
    TIMES("*", (x, y) -> x * y),
    DIVIDE("/", (x, y) -> x / y);

    private final String symbol;
    private final BinaryOperator<Integer> op;

    Operation(String symbol, BinaryOperator<Integer> op) {
        this.symbol = symbol;
        this.op = op;
    }

    public static Operation findBySymbol(final String symbol) {
        return Arrays.stream(values())
                .filter(v -> v.getSymbol().equals(symbol))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("사칙연산 기호가 아닙니다."));
    }

    public int apply(int x, int y) {
        if (isDivideWithZero(y)) {
            throw new IllegalArgumentException("0으로 나눌수 없습니다.");
        }
        return op.apply(x, y);
    }

    private boolean isDivideWithZero(final int y) {
        return this == DIVIDE && y == 0;
    }

    private String getSymbol() {
        return symbol;
    }
}
