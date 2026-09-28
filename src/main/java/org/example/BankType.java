package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;

public enum BankType {
    NEO("НеоКредит", "0.01"),
    AUM("Арум Финтех", "0.02"),
    VTA("Вектор Альянс Банк", "0.00");
    public final String BankName;
    public final BigDecimal com;

    BankType(String BankName, String com) {
        this.com = new BigDecimal(com).setScale(2, RoundingMode.HALF_UP);
        this.BankName = BankName;
    }

    String getName() {
        return this.BankName;
    }

    BigDecimal getCom() {
        return this.com;
    }
}
