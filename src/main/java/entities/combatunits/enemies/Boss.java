package entities.combatunits.enemies;

import lombok.Data;

@Data
public class Boss extends Enemy {
    public Boss(String name, int health, int atkRatingBase, int defRatingBase) {
        super(name);
        this.setHealthCurrent(health);
        this.setHealthMax(health);
        this.setAtkRatingBase(atkRatingBase);
        this.setDefRatingBase(defRatingBase);
    }
}
