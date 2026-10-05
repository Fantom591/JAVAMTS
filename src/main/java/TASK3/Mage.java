package TASK3;



import static TASK3.ActionType.*;

public class Mage extends Hero implements Restable, Castable {

    private final int maxMana;
    private int mana;

    Mage(String name, int maxHealth, int baseAttack, int maxMana) {
        super(name, maxHealth, baseAttack);
        this.maxMana = Math.max(maxMana,super.MIN_STAT_VALUE);
        this.mana = this.maxMana;
    }
    @Override
    public String getRes(){
        return "Mana: "+mana;
    }
    @Override
    public void attack(Hero target) {
        if (mana >= 10) {
            System.out.println(getName() + " Магический выстрел");
            target.takeDamage(getBaseAttack() * 2);
            mana -= 10;
            System.out.println(getName() + " нанёс урон: " + getBaseAttack() * 2 + " по " + target.getName());
        } else {
            System.out.println(getName() + " Удар посохом ");
            target.takeDamage(getBaseAttack() / 2);
            System.out.println(getName() + " нанёс урон: " + getBaseAttack() / 2 + " по " + target.getName());
            if (mana + 5 > maxMana) {
                System.out.println(getName() + " Востановил " + (maxMana - mana) + " маны");
                mana = maxMana;
            } else {
                mana += 5;
                System.out.println(getName() + " Востановил " + 5 + " маны");
            }
        }
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
        if (mana >= 25) {
            return true;
        } else {
            return false;
        }
    }

    public void castSpecialSkill(Hero target) {
        if (canCast()) {
            System.out.println(getName()+" Огненная глыба");
            target.takeDamage(getBaseAttack() * 3);
            mana -= 25;
            System.out.println(getName() + " нанёс урона: " + getBaseAttack() * 3 + " по " + target.getName());
        } else {
            System.out.println(getName() + " Заклинание прервалось");
        }

    }

    public boolean needsRest() {
        if (getHealth() * 10 < getMaxHealth() * 3 || mana == 0) {
            return true;
        } else {
            return false;
        }
    }

    public void rest() {
        heal(getBaseAttack());
        System.out.println(getName() + " восстановил " + (maxMana - mana) + " маны и " + getBaseAttack() + " здоровья");
        mana = maxMana;
    }
}
