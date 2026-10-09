package com.example.test.db.entity.enums;

public enum TransactionType {

    INCOME(
            "Money received from salary, business, investment or other sources",
            "Maaş, iş, yatırım veya diğer gelir kaynaklarından elde edilen para"
    ),

    EXPENSE(
            "Money spent on food, transportation, bills, shopping or other expenses",
            "Yemek, ulaşım, faturalar, alışveriş veya diğer harcamalar için harcanan para"
    );

    private final String descEn;
    private final String descTr;

    TransactionType(String descEn, String descTr) {
        this.descEn = descEn;
        this.descTr = descTr;
    }

    public String getDescEn() {
        return descEn;
    }

    public String getDescTr() {
        return descTr;
    }
}