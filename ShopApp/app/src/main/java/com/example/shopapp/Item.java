package com.example.shopapp;

import java.io.Serializable;

public class Item implements Serializable{
    private int prise;
    private int wlsPrise;
    private int amount;
    private String name;
    private String code;

    public Item(double prise, int wlsPrise, int amount, String code, String name) {
        this.prise =(int) (prise / 100 * wlsPrise + wlsPrise);
        this.wlsPrise = wlsPrise;
        this.amount = amount;
        this.name = name;
        this.code = code;
    }

    public int getPrise() {
        return prise;
    }

    public int getWlsPrise() {
        return wlsPrise;
    }

    public int getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setPrise(int prise) {
        this.prise = prise;
    }

    public void setWlsPrise(int wlsPrise) {
        this.wlsPrise = wlsPrise;
    }

    @Override
    public String toString() {
        return "Продукт {" +
                "Ціна = " + prise +
                ", Ціна закупки = " + wlsPrise +
                ", Кількість = " + amount +
                ", Код = '" + code + '\'' +
                ", Назва = '" + name + '\'' +
                '}';
    }
}
