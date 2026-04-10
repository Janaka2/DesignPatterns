package org.example.architecture.clean;

public interface OrderGateway {
    void save(String orderId, String status);
    String getStatus(String orderId);
}
