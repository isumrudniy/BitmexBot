package ru.tuzhilkin_dm.bitmexbot.model;

public class Order {
    private String orderId;
    private Symbol symbol;
    private boolean isBuy;
    private double orderQty;
    private Double price;
    private Double stopPx;
    private boolean isWorking;
    private OrderType orderType;
    private OrderStatus orderStatus;
}
