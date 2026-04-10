package org.example.ai;

import java.util.List;
import java.util.regex.Pattern;

public class PolicyEnforcedLlmClient implements LlmClient {

    public interface InputPolicy {
        void validate(String input);
    }

    public interface OutputPolicy {
        String filter(String output);
    }

    public static class NonEmptyInputPolicy implements InputPolicy {
        @Override
        public void validate(String input) {
            if (input == null || input.isBlank()) {
                throw new IllegalArgumentException("Prompt must not be blank");
            }
        }
    }

    public static class MaxLengthInputPolicy implements InputPolicy {
        private final int maxLength;

        public MaxLengthInputPolicy(int maxLength) {
            this.maxLength = maxLength;
        }

        @Override
        public void validate(String input) {
            if (input.length() > maxLength) {
                throw new IllegalArgumentException("Prompt exceeds max length " + maxLength);
            }
        }
    }

    public static class PiiMaskingInputPolicy implements InputPolicy {
        private static final Pattern EMAIL = Pattern.compile("[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+");

        @Override
        public void validate(String input) {
            // no-op for validation; mask is applied via sanitize
        }

        public String sanitize(String input) {
            return EMAIL.matcher(input).replaceAll("[EMAIL_MASKED]");
        }
    }

    public static class BasicOutputFilterPolicy implements OutputPolicy {
        @Override
        public String filter(String output) {
            return output.replaceAll("(?i)password\\s*[:=]\\s*\\S+", "password:[REDACTED]");
        }
    }

    private final LlmClient delegate;
    private final List<InputPolicy> inputPolicies;
    private final List<OutputPolicy> outputPolicies;
    private final PiiMaskingInputPolicy piiMaskingInputPolicy;

    public PolicyEnforcedLlmClient(
            LlmClient delegate,
            List<InputPolicy> inputPolicies,
            List<OutputPolicy> outputPolicies,
            PiiMaskingInputPolicy piiMaskingInputPolicy
    ) {
        this.delegate = delegate;
        this.inputPolicies = inputPolicies;
        this.outputPolicies = outputPolicies;
        this.piiMaskingInputPolicy = piiMaskingInputPolicy;
    }

    @Override
    public String complete(String prompt) {
        for (InputPolicy policy : inputPolicies) {
            policy.validate(prompt);
        }

        String sanitizedPrompt = piiMaskingInputPolicy.sanitize(prompt);
        String output = delegate.complete(sanitizedPrompt);
        String filteredOutput = output;
        for (OutputPolicy policy : outputPolicies) {
            filteredOutput = policy.filter(filteredOutput);
        }
        return filteredOutput;
    }
}
