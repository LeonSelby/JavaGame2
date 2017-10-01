package entities.combatunits.enemies;

import entities.combatunits.CombatUnit;
import lombok.Data;

@Data
abstract public class Enemy extends CombatUnit {

    private int numberOfAttacks = 1;

    public Enemy(String name) {
        super(name);
    }
}
