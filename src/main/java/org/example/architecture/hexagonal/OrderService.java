package org.example.architecture.hexagonal;

public class OrderService {
    private final PaymentPort paymentPort;

    public OrderService(PaymentPort paymentPort) {
        this.paymentPort = paymentPort;
    }

    public String placeOrder(String accountId, int amount) {
        boolean charged = paymentPort.charge(accountId, amount);
        return charged ? "ORDER_CONFIRMED" : "ORDER_REJECTED";
    }
}
