package org.example;

import java.math.BigDecimal;
import java.util.*;
import java.lang.Math;

public class Main {

    static Account Prime = new Account(12345, 999, new BigDecimal("10000.00"), BankType.AUM);
    static CashMachine CashMachine = new CashMachine();

    static boolean authorization(Scanner input) {


        System.out.print("Введите номер карты: ");
        if (!input.hasNextInt()) {
            System.out.println("Введено не число");
            input.nextLine();
            return false;
        }

        int numcard = input.nextInt();
        System.out.print("Введите пинкод: ");
        if (!input.hasNextInt()) {
            System.out.println("Введено не число");
            input.nextLine();
            return false;
        }
        int pincod = input.nextInt();
        if (numcard == Prime.getNumCard() && pincod == Prime.getPincod()) {
            return true;
        } else {
            return false;
        }
    }


    static void addmoney(Scanner input) {

        System.out.print("Введите сумму пополнения: ");
        if (!input.hasNextBigDecimal()) {
            System.out.println("Введено не число");
            input.nextLine();
            return;
        }
        BigDecimal introd = input.nextBigDecimal();
        if (introd.compareTo(new BigDecimal("0")) < 0) {
            System.out.println("Сумма должна быть положительной");
            return;
        }
        Prime.Balans = CashMachine.introduction(Prime.Balans, introd);
        System.out.println(Prime.Balans);


    }

    static void subtractmoney(Scanner input) {

        System.out.print("Введите сумму снятия: ");
        if (!input.hasNextBigDecimal()) {
            System.out.println("Введено не число");
            input.nextLine();
            return;
        }
        BigDecimal cost = input.nextBigDecimal();
        if (cost.compareTo(new BigDecimal("0")) <= 0) {
            System.out.println("Сумма должна быть положительной");
            return;
        }
        Prime.Balans = CashMachine.Deduction(Prime.Balans, cost, Prime.BankType);
        System.out.println(Prime.Balans);

    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Банковская система запущена");

        if (authorization(input)) {
            System.out.println("Успешный вход");

            addmoney(input);

            subtractmoney(input);

            input.close();
        } else {
            System.out.println("Ошибка данных");
            input.close();
            return;

        }
    }
}
