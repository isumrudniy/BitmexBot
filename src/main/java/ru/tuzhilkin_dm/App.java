package ru.tuzhilkin_dm;

import ru.tuzhilkin_dm.bitmexbot.model.Order;
import ru.tuzhilkin_dm.bitmexbot.model.reference.OrderType;
import ru.tuzhilkin_dm.bitmexbot.model.reference.Symbol;
import ru.tuzhilkin_dm.bitmexbot.client.BitmexClient;
import ru.tuzhilkin_dm.bitmexbot.client.BitmexClientFactory;
import ru.tuzhilkin_dm.bitmexbot.util.AppСonstants;

import java.util.List;

public class App {

    public static void main(String[] args) {
        Order order = Order.builder()
                .orderQty(100)
                .orderType(OrderType.LMT)
                .isBuy(true)
                .symbol(Symbol.XBTUSD)
                .price(10000.)
                .build();
        Order order2 = Order.builder()
                .orderQty(100)
                .orderType(OrderType.STP_MKT)
                .stopPx(11000.)
                .isBuy(true)
                .symbol(Symbol.XBTUSD)
                .build();

        BitmexClient bitmexClient = BitmexClientFactory.newTestnetBitmexClient(AppСonstants.API_KEY, AppСonstants.API_SECRET_KEY);
//        bitmexClient.sendOrder(order);
//        bitmexClient.sendOrder(order2);

//        bitmexClient.getOrders();

        List<String> orderID = List.of("4a345c9c-3951-4932-b8b2-2b1b78a55e17");
        bitmexClient.cancelOrder(orderID);

        bitmexClient.getOrders();

    }
}
