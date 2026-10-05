package TASK2;

import java.math.BigDecimal;
import java.math.RoundingMode;


public interface WithdrawalOperations {

    BigDecimal Deduction(BigDecimal Balans, BigDecimal cost, BankType BankType);

    default BigDecimal applyCommission(BigDecimal cost, BankType BankType) {
        if (cost == null || BankType == null) {
            return BigDecimal.ZERO;
        } else {

            cost = cost.multiply(BankType.com).setScale(2, RoundingMode.HALF_UP);
            return cost;
        }
    }
}
