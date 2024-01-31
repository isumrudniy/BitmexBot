package ru.tuzhilkin_dm.bitmexbot.model;

import lombok.Builder;
import lombok.Data;
import ru.tuzhilkin_dm.bitmexbot.model.reference.OrderStatus;
import ru.tuzhilkin_dm.bitmexbot.model.reference.OrderType;
import ru.tuzhilkin_dm.bitmexbot.model.reference.Symbol;

@Data
@Builder
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
