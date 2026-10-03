package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;


public class Account {
    public int NumCard;
    public int Pincod;
    public BigDecimal Balans = new BigDecimal("0");
    public BankType BankType;

    public Account(int numcard, int pincod, BigDecimal balans, BankType banktype) {
        if (numcard >= 10000 && numcard <= 99999) {
            NumCard = numcard;
        }
        if (pincod >= 100 && pincod <= 999) {
            Pincod = pincod;
        }

        if (banktype == null)
            BankType = BankType.NEO;
        else
            BankType = banktype;

        if (balans != null && balans.compareTo(BigDecimal.ZERO) >= 0) {
            Balans = balans.setScale(2, RoundingMode.HALF_UP);
        }
    }

    public String toString() {
        return (String) (BankType.BankName + " Карта: " + NumCard + ", Баланс: " + Balans + " руб.");
    }

    public int getNumCard() {
        return NumCard;
    }

    public int getPincod() {
        return Pincod;
    }

    public BigDecimal getBalance() {
        return Balans;
    }

    public BankType getBankType() {
        return BankType;
    }

}
