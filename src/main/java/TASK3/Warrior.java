package TASK3;

import static TASK3.ActionType.*;

public class Warrior extends Hero implements Restable, Castable {


    private final int armor;

    Warrior(String name, int maxHealth, int baseAttack, int armor) {
        super(name, maxHealth, baseAttack);
        this.armor = Math.max(armor,super.MIN_STAT_VALUE);
    }
    @Override
    public String getRes(){
        return "Armor: "+armor;
    }
    @Override
    public void takeDamage(int damage) {
        super.takeDamage(Math.max(damage -armor,1));
    }
    @Override
    public void attack(Hero target) {
        target.takeDamage(getBaseAttack());
        System.out.println(this.getName() + " нанёс урон: " + getBaseAttack() + " по " + target.getName());
    }
    @Override
    public ActionType makeTurn(Hero target) {
        if (canCast()) {
            castSpecialSkill(target);
            return SPECIAL_SKILL;
        } else if (needsRest()) {
            rest();
            return REST;
        } else {
            attack(target);
            return BASE_ATTACK;
        }
    }

    public boolean canCast() {
        if (getHealth() * 2 < getMaxHealth()) {
            return true;
        } else {
            return false;
        }
    }

    public void castSpecialSkill(Hero target) {
        if (canCast()) {
            System.out.println(getName() + ": Удар щитом");
            target.takeDamage((armor * 2) + getBaseAttack());
            System.out.println(this.getName() + " нанёс урон: " + (getBaseAttack() + armor * 2) + " по " + target.getName());
        } else {
            System.out.println(getName() + " Воин попытался ударить щитом, но потерял равновесие!");
        }
    }

    public boolean needsRest() {
        if (100 * getHealth() < 15 * getMaxHealth()) {
            return true;
        } else {
            return false;
        }
    }

    public void rest() {
        System.out.println(getName() + ": Второе дыхание");
        heal(armor * 2);
        System.out.println(getName() + " восстановил " + armor * 2 + " здоровья");
    }
}
