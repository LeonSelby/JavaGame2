package entities.combatunits.enemies;

import lombok.Data;

@Data
public class Goblin extends Enemy {
    public Goblin(String name, int health, int atkRatingBase, int defRatingBase) {
        super(name);
        this.setHealthMax(health);
        this.setHealthCurrent(health);
        this.setAtkRatingBase(atkRatingBase);
        this.setDefRatingBase(defRatingBase);
        this.setNumberOfAttacks(2);
        this.setCritChance(10);
    }
}
