package TASK3;

public abstract class Hero {


    static protected final int MIN_STAT_VALUE = 1;
    final private String name;
    final private int maxHealth;
    final private int baseAttack;
    private int health;

    Hero(String name, int maxHealth, int baseAttack) {
        if (name == null || name.isBlank()) {
            name = "NoName";
            System.out.println("Имя введено не корректно");
        }
        this.name = name;
        this.maxHealth = Math.max(maxHealth, MIN_STAT_VALUE);
        this.health = Math.max(maxHealth, MIN_STAT_VALUE);
        this.baseAttack = Math.max(baseAttack, MIN_STAT_VALUE);
    }

    public String getName() {
        return name;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getBaseAttack() {
        return baseAttack;
    }

    public int getHealth() {
        return health;
    }


    public abstract void attack(Hero target);

    public abstract ActionType makeTurn(Hero target);

    public String getRes() {
        return "";
    }

    public void takeDamage(int damage) {
        if (health <= damage) {
            System.out.println(name + " пал в бою!");
            health = 0;
        } else {
            health -= Math.max(damage, 0);
        }

    }

    public void heal(int amount) {
        health = Math.min(maxHealth, health + Math.max(amount, 0));

    }

    public boolean isAlive() {
        if (health > 0) return true;
        else return false;
    }

}
