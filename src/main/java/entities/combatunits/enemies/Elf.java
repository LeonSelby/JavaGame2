package entities.combatunits.enemies;

import lombok.Data;

@Data
public class Elf extends Enemy {
    public Elf(String name, int health, int atkRatingBase, int defRatingBase) {
        super(name);
        this.setHealthMax(health);
        this.setHealthCurrent(health);
        this.setAtkRatingBase(atkRatingBase);
        this.setDefRatingBase(defRatingBase);
        this.setDodgeChance(15);
        this.setBlockChance(0);
    }
}
