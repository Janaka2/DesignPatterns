package example;

import org.example.data_consistency.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DataConsistencyPatternsTest {

    @Test
    public void transactionalOutboxAndSagaVariants() {
        TransactionalOutbox outbox = new TransactionalOutbox();
        List<String> entityState = new ArrayList<>();
        OutboxEvent event = new OutboxEvent("order-1", "OrderPlaced", "{amount:100}");

        outbox.saveWithEvent("order-1", () -> entityState.add("saved"), event);
        assertEquals(1, outbox.pendingEvents().size());
        assertEquals("saved", entityState.get(0));

        List<String> logs = new ArrayList<>();
        SagaOrchestrator orchestrator = new SagaOrchestrator();
        orchestrator.execute(List.of(
                new SagaStep(() -> logs.add("reserve"), () -> logs.add("release")),
                new SagaStep(() -> logs.add("charge"), () -> logs.add("refund"))
        ));
        assertEquals(List.of("reserve", "charge"), logs);

        ChoreographySaga choreographySaga = new ChoreographySaga();
        List<String> events = new ArrayList<>();
        choreographySaga.subscribe(e -> events.add("inventory:" + e));
        choreographySaga.subscribe(e -> events.add("payment:" + e));
        choreographySaga.publish("OrderPlaced");

        assertEquals(List.of("inventory:OrderPlaced", "payment:OrderPlaced"), events);
    }

    @Test
    public void unitOfWorkRepositorySpecification() {
        UnitOfWork unitOfWork = new UnitOfWork();
        List<String> committed = new ArrayList<>();
        unitOfWork.register(() -> committed.add("one"));
        unitOfWork.register(() -> committed.add("two"));
        assertEquals(2, unitOfWork.pendingOperations());

        unitOfWork.commit();
        assertEquals(List.of("one", "two"), committed);

        InMemoryRepository<String, OrderRecord> repository = new InMemoryRepository<>();
        repository.save("1", new OrderRecord("1", "PAID", 100));
        repository.save("2", new OrderRecord("2", "PENDING", 40));

        Specification<OrderRecord> paidSpec = order -> "PAID".equals(order.status());
        Specification<OrderRecord> highValueSpec = order -> order.amount() > 50;

        List<OrderRecord> matches = repository.findBySpecification(paidSpec.and(highValueSpec));
        assertEquals(1, matches.size());
        assertEquals("1", matches.get(0).id());
    }
}
