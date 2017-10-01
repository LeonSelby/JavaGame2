package javagame2.entities.combatunits.enemies;

import lombok.Data;

@Data
public class Orc extends Enemy{
    public Orc(String name, int health, int atkRatingBase, int defRatingBase) {
        super(name);
        this.setHealthMax(health);
        this.setHealthCurrent(health);
        this.setAtkRatingBase(atkRatingBase);
        this.setDefRatingBase(defRatingBase);
        this.setBlockChance(15);
        this.setAbleToCrit(false);
    }
}
