package ru.tuzhilkin_dm.bitmexbot.model;

import lombok.Data;
import java.util.Date;

@Data
public class OrderResponse {
    private String orderID;
    private String clOrdID;
    private String clOrdLinkID;
    private int account;
    private String symbol;
    private String side;
    private int orderQty;
    private double price;
    private Double displayQty;
    private Double stopPx;
    private String pegOffsetValue;
    private String pegPriceType;
    private String currency;
    private String settlCurrency;
    private String ordType;
    private String timeInForce;
    private String execInst;
    private String contingencyType;
    private String ordStatus;
    private String triggered;
    private boolean workingIndicator;
    private String ordRejReason;
    private int leavesQty;
    private int cumQty;
    private Double avgPx;
    private String text;
    private Date transactTime;
    private Date timestamp;
}
