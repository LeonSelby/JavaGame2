package javagame2.entities.combatunits.enemies;

import lombok.Data;

@Data
public class Undead extends Enemy {
    public Undead(String name, int health, int atkRatingBase, int defRatingBase) {
        super(name);
        this.setHealthMax(health);
        this.setHealthCurrent(health);
        this.setAtkRatingBase(atkRatingBase);
        this.setDefRatingBase(defRatingBase);
        this.setDodgeChance(0);
        this.setBlockChance(0);
    }
}
