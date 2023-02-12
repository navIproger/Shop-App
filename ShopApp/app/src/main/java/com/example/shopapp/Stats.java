package com.example.shopapp;

import java.io.Serializable;

public class Stats implements Serializable {
    private int sellItem;
    private int profit;
    private int cleanProfit;

    public Stats(int sellItem, int profit, int cleanProfit) {
        this.sellItem = sellItem;
        this.profit = profit;
        this.cleanProfit = cleanProfit;
    }

    public int getSellItem() {
        return sellItem;
    }

    public int getProfit() {
        return profit;
    }

    public int getCleanProfit() {
        return cleanProfit;
    }

}
