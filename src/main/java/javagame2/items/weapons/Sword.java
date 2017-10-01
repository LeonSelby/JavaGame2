package javagame2.items.weapons;

import lombok.Data;

@Data
public class Sword extends Weapon {
    public Sword(String name, int coinValue, int atkRating) {
        super(name, coinValue, atkRating);
    }

    @Override
    public String toString() {
        return this.getName() + " has " + this.getAtkRating() + " attack rating!";
    }
}
