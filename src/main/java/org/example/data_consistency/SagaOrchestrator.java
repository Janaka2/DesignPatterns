package org.example.data_consistency;

import java.util.ArrayList;
import java.util.List;

public class SagaOrchestrator {
    public void execute(List<SagaStep> steps) {
        List<SagaStep> completed = new ArrayList<>();

        for (SagaStep step : steps) {
            try {
                step.action().run();
                completed.add(step);
            } catch (RuntimeException ex) {
                rollback(completed);
                throw new IllegalStateException("Saga orchestration failed", ex);
            }
        }
    }

    private void rollback(List<SagaStep> completed) {
        for (int i = completed.size() - 1; i >= 0; i--) {
            completed.get(i).compensation().run();
        }
    }
}
