package order.model;

public enum OrderStatus {
    PENDING("Pending"),
    PROCESSING("Processing"),
    APPROVED("Approved"),
    REJECTED("Rejected"),
    CANCELLED("Cancelled");

    OrderStatus(String name) {
    }
}
