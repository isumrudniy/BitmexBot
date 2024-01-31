package ru.tuzhilkin_dm;

import ru.tuzhilkin_dm.bitmexbot.model.Order;
import ru.tuzhilkin_dm.bitmexbot.model.OrderType;
import ru.tuzhilkin_dm.bitmexbot.model.Symbol;
import ru.tuzhilkin_dm.bitmexbot.service.BitmexClient;
import ru.tuzhilkin_dm.bitmexbot.service.BitmexClientFactory;

public class App {

    public static void main(String[] args) {
        Order order = Order.builder()
                .orderQty(100)
                .orderType(OrderType.LMT)
                .isBuy(true)
                .symbol(Symbol.XBTUSD)
                .price(10000.)
                .build();

        String apiKey = "";
        String apiSecretKey = "";
        BitmexClient bitmexClient = BitmexClientFactory.newTestnetBitmexClient(apiKey, apiSecretKey);
//        bitmexClient.sendOrder(order);
        bitmexClient.getOrders();
    }
}
