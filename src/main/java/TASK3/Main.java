package TASK3;

import java.util.Scanner;

public class Main {
    private static boolean printError(){
        System.out.println("Введены не корректные данные");
        return false;
    }
    static boolean createCharacters(Scanner input,Hero[] fighters) {
        for (int num = 1; num <= 2; num++) {
            System.out.println("Создание персонажа " + num);
            System.out.println("Выберите тип персонажа \n" +
                    "1 - Воин \n" +
                    "2 - Маг");
            if(!input.hasNextInt()){
                return printError();
            }
            int type = input.nextInt();
            if(!(1<=type && type<=2)){
                return printError();
            }
            input.nextLine();
            System.out.print("Выбери имя: ");
            String name = input.nextLine();
            System.out.print("Напиши максимальное здоровье: ");
            if(!input.hasNextInt()){
                return printError();
            }
            int maxHealth = input.nextInt();
            if(maxHealth<=0){
                return printError();
            }
            System.out.print("Напиши базовую атаку: ");
            if(!input.hasNextInt()){
                return printError();
            }
            int baseAttack = input.nextInt();
            if(baseAttack<=0){
                return printError();
            }
            if (type == 1) {
                System.out.print("Напиши показатель брони: ");
                if(!input.hasNextInt()){
                    return printError();
                }
                int armor = input.nextInt();
                if(armor<0){
                    return printError();
                }
                fighters[num - 1] = new Warrior(name, maxHealth, baseAttack, armor);
            } else {
                System.out.print("Напиши запас маны: ");
                if(!input.hasNextInt()){
                    return printError();
                }
                int maxMana = input.nextInt();
                if(maxMana<=0){
                    return printError();
                }
                fighters[num - 1] = new Mage(name, maxHealth, baseAttack, maxMana);
            }

        }
        return true;
    }

    public static void main(String[] args) {
        Hero[] fighters = new Hero[2];
        Scanner input = new Scanner(System.in);
        if(!createCharacters(input,fighters)){
            return;
        }
        Arena arena = new Arena(fighters[0], fighters[1]);
        arena.startTournament();
    }
}
