package javagame2.entities.combatunits.enemies;

import javagame2.entities.combatunits.CombatUnit;
import lombok.Data;

@Data
abstract public class Enemy extends CombatUnit {

    private int numberOfAttacks = 1;

    public Enemy(String name) {
        super(name);
    }
}
