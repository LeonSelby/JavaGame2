package javagame2.items.weapons;

import javagame2.items.Item;
import lombok.Data;

@Data
abstract public class Weapon extends Item {
    private int AtkRating;

    public Weapon(String name, int coinValue, int atkRating) {
        super(name, coinValue);
        this.AtkRating = atkRating;
    }

    @Override
    public String toString() {
        return "Weapon{" +
                "name=" + this.getName() +
                ", atkRating=" + AtkRating +
                '}';
    }
}
