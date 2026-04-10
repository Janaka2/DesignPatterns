package org.example.architecture.clean;

public class SubmitOrderUseCase {
    private final OrderGateway gateway;

    public SubmitOrderUseCase(OrderGateway gateway) {
        this.gateway = gateway;
    }

    public void execute(String orderId) {
        gateway.save(orderId, "SUBMITTED");
    }
}
