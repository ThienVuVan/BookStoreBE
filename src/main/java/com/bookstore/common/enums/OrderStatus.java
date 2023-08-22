package com.bookstore.common.enums;

public class OrderStatus {
    public static final String ORDER_PLACEMENT = "Order Placement";
    public static final String ORDER_PROCESSING = "Order Processing";
    public static final String ORDER_FULFILLMENT = "Order Fulfillment";
    public static final String DELIVERY_PREPARATION = "Delivery Preparation";
    public static final String SHIPPING = "Shipping";
    public static final String DELIVERY = "Delivery";
    public static final String SUCCESSFUL_DELIVERY = "Successful Delivery";
    public static final String RETURN = "Return";
    public static final String CANCELED_ORDER = "Canceled Order";

    /**
     *
     * Order Placement (Đặt hàng): The initial step where a customer submits an order for products or services.
     *
     * Order Processing (Xử lý đơn hàng): The stage in which the seller processes the order, which includes verifying the payment, checking product availability, and preparing the items for shipment.
     *
     * Order Fulfillment (Hoàn thành đơn hàng): The process of assembling and packaging the ordered items, getting them ready for shipping.
     *
     * Shipping/Delivery Preparation (Chuẩn bị giao hàng): The phase where the items are packaged, labeled, and made ready for shipment.
     *
     * Shipping/Shipment (Giao hàng): The transportation of the packaged items from the seller's location to the customer's address.
     *
     * Delivery (Giao hàng): The final step where the shipped items are received by the customer.
     *
     * Successful Delivery/Receipt (Giao hàng thành công): Confirmation that the items have been received by the customer in satisfactory condition.
     *
     * Tracking (Theo dõi): The ability for customers to monitor the status and location of their shipment through tracking numbers or codes.
     *
     * Out of Stock (Hết hàng): When the requested item is not available in the inventory for immediate fulfillment.
     *
     * Backorder (Hàng đặt trước): When an item is not currently in stock but can be ordered and will be delivered once it becomes available.
     *
     * Return (Trả hàng): The process of a customer sending back items they've received, often due to defects or dissatisfaction.
     *
     * Refund (Hoàn tiền): The reimbursement of the customer's payment for returned or canceled items.
     *
     * Canceled Order (Đơn hàng bị hủy): An order that was initially placed but subsequently canceled, often before shipment.
     *
     * Confirmation Email (Email xác nhận): An automated email sent to the customer to confirm their order placement.
     *
     * Dispatched/Shipped Email (Email thông báo giao hàng): An automated email sent to the customer when their order has been shipped.
     */
}
