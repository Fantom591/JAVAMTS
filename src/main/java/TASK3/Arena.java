package TASK3;

public class Arena {
    private final Hero fighter1;
    private final Hero fighter2;
    private int roundCounter;

    Arena(Hero fighter1, Hero fighter2) {
        if (fighter1 == null || fighter2 == null) {
            System.out.println("Герои для боя выбраны не правильно\n" +
                    "сражаются базовые персонажи");
            this.fighter1 = new Warrior("Артур", 120, 15, 5);
            this.fighter2 = new Mage("Мерлин", 80, 20, 30);
        } else {
            this.fighter1 = fighter1;
            this.fighter2 = fighter2;
        }
    }

    void startTournament() {
        while (fighter1.isAlive() && fighter2.isAlive()) {
            roundCounter++;
            System.out.println("Раунд: " + roundCounter);
            ActionType action;
            int attacker = (int) (Math.random() * 2) + 1;
            if (attacker == 1) {
                action = fighter1.makeTurn(fighter2);
                System.out.println("Игрок " + fighter1.getName() + " совершил действие: " + action.getDescription());
                if (!fighter2.isAlive()) {
                    break;
                }
                action = fighter2.makeTurn(fighter1);
                System.out.println("Игрок " + fighter2.getName() + " совершил действие: " + action.getDescription());
                if (!fighter1.isAlive()) {
                    break;
                }
            } else {
                action = fighter2.makeTurn(fighter1);
                System.out.println("Игрок " + fighter2.getName() + " совершил действие: " + action.getDescription());
                if (!fighter1.isAlive()) {
                    break;
                }
                action = fighter1.makeTurn(fighter2);
                System.out.println("Игрок " + fighter1.getName() + " совершил действие: " + action.getDescription());
                if (!fighter2.isAlive()) {
                    break;
                }
            }
            System.out.println(fighter1.getName() + " Health: " + fighter1.getHealth() + " Attack: " + fighter1.getBaseAttack() + " " + fighter1.getRes());
            System.out.println(fighter2.getName() + " Health: " + fighter2.getHealth() + " Attack: " + fighter2.getBaseAttack() + " " + fighter2.getRes());
        }
        if (fighter1.isAlive()) {
            System.out.println(fighter1.getName() + " победил!");
        } else {
            System.out.println(fighter2.getName() + " победил!");
        }
    }

}
