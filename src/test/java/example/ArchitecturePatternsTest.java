package example;

import org.example.architecture.acl.AntiCorruptionCustomerAdapter;
import org.example.architecture.acl.Customer;
import org.example.architecture.acl.LegacyCustomerSystem;
import org.example.architecture.clean.InMemoryOrderGateway;
import org.example.architecture.clean.SubmitOrderUseCase;
import org.example.architecture.hexagonal.InMemoryPaymentAdapter;
import org.example.architecture.hexagonal.OrderService;
import org.example.architecture.strangler.LegacyBillingService;
import org.example.architecture.strangler.NewBillingService;
import org.example.architecture.strangler.StranglerBillingRouter;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ArchitecturePatternsTest {

    @Test
    public void hexagonalAndCleanArchitecture() {
        InMemoryPaymentAdapter paymentAdapter = new InMemoryPaymentAdapter();
        OrderService orderService = new OrderService(paymentAdapter);

        String status = orderService.placeOrder("acct-1", 120);

        assertEquals("ORDER_CONFIRMED", status);
        assertTrue(paymentAdapter.hasPayment("acct-1", 120));

        InMemoryOrderGateway orderGateway = new InMemoryOrderGateway();
        SubmitOrderUseCase useCase = new SubmitOrderUseCase(orderGateway);
        useCase.execute("order-77");

        assertEquals("SUBMITTED", orderGateway.getStatus("order-77"));
    }

    @Test
    public void stranglerAndAntiCorruptionLayer() {
        StranglerBillingRouter router = new StranglerBillingRouter(
                new LegacyBillingService(),
                new NewBillingService(),
                Set.of("cust-new")
        );

        assertEquals("LEGACY_BILL:cust-old", router.bill("cust-old"));
        assertEquals("NEW_BILL:cust-new", router.bill("cust-new"));

        AntiCorruptionCustomerAdapter acl = new AntiCorruptionCustomerAdapter(new LegacyCustomerSystem());
        Customer customer = acl.loadCustomer("42");

        assertEquals("LEG-42", customer.id());
        assertEquals("Legacy 42", customer.name());
    }
}
