package org.example.ai;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public final class EvaluationHarness {

    private EvaluationHarness() {
    }

    public record GoldenExample(String input, String expectedOutput) {
    }

    public record GoldenSetResult(double accuracy, List<String> failures) {
    }

    public record StatisticalResult(double averageScore, double minScore, double maxScore) {
    }

    public static GoldenSetResult evaluateGoldenSet(List<GoldenExample> examples, Function<String, String> model) {
        int correct = 0;
        List<String> failures = new ArrayList<>();

        for (GoldenExample example : examples) {
            String actual = model.apply(example.input());
            if (example.expectedOutput().equals(actual)) {
                correct++;
            } else {
                failures.add("input=" + example.input() + ", expected=" + example.expectedOutput() + ", actual=" + actual);
            }
        }

        double accuracy = examples.isEmpty() ? 0.0 : (double) correct / examples.size();
        return new GoldenSetResult(accuracy, failures);
    }

    public static StatisticalResult evaluateStatistically(List<String> inputs, Function<String, Double> scorer) {
        if (inputs.isEmpty()) {
            throw new IllegalArgumentException("inputs must not be empty");
        }

        double sum = 0.0;
        double min = Double.POSITIVE_INFINITY;
        double max = Double.NEGATIVE_INFINITY;

        for (String input : inputs) {
            double score = scorer.apply(input);
            sum += score;
            min = Math.min(min, score);
            max = Math.max(max, score);
        }

        return new StatisticalResult(sum / inputs.size(), min, max);
    }
}
