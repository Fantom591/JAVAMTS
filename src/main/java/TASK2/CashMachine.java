package TASK2;

import java.math.BigDecimal;
import java.math.RoundingMode;


public class CashMachine implements WithdrawalOperations, DepositOperations {
    public BigDecimal introduction(BigDecimal Balans, BigDecimal introd) {
        if (Balans == null || introd == null || introd.compareTo(new BigDecimal("0")) <= 0) {
            return Balans;
        } else
            return Balans.add(introd).setScale(2, RoundingMode.HALF_UP);

    }

    public BigDecimal Deduction(BigDecimal Balans, BigDecimal cost, BankType BankType) {
        if (Balans == null) {
            return BigDecimal.ZERO;
        }
        if (cost == null || BankType == null) {
            System.out.println("Null в заданных данных");
            return Balans;
        }
        cost = cost.add(applyCommission(cost, BankType));
        if (Balans.compareTo(cost) < 0) {
            System.out.println("Недостаточно средств");
            return Balans;
        } else {
            return Balans.subtract(cost).setScale(2, RoundingMode.HALF_UP);
        }
    }
}
