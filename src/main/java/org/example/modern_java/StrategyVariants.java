package org.example.modern_java;

import java.util.Map;
import java.util.function.IntBinaryOperator;

public final class StrategyVariants {
    private StrategyVariants() {
    }

    public interface Strategy {
        int apply(int left, int right);
    }

    public static final class ClassicCalculator {
        private final Strategy strategy;

        public ClassicCalculator(final Strategy strategy) {
            this.strategy = strategy;
        }

        public int calculate(final int left, final int right) {
            return strategy.apply(left, right);
        }
    }

    public static final class FunctionalCalculator {
        private final IntBinaryOperator operation;

        public FunctionalCalculator(final IntBinaryOperator operation) {
            this.operation = operation;
        }

        public int calculate(final int left, final int right) {
            return operation.applyAsInt(left, right);
        }
    }

    public sealed interface Operation permits Add, Multiply {
    }

    public record Add(int left, int right) implements Operation {
    }

    public record Multiply(int left, int right) implements Operation {
    }

    public static final class ModernCalculator {
        private final Map<Class<? extends Operation>, IntBinaryOperator> registry = Map.of(
                Add.class, Integer::sum,
                Multiply.class, (left, right) -> left * right
        );

        public int calculate(final Operation operation) {
            return switch (operation) {
                case Add add -> registry.get(Add.class).applyAsInt(add.left(), add.right());
                case Multiply multiply -> registry.get(Multiply.class)
                        .applyAsInt(multiply.left(), multiply.right());
            };
        }
    }
}
