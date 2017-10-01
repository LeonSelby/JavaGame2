package javagame2.items.armour;

import javagame2.items.Item;
import lombok.Data;

@Data
abstract public class Armour extends Item {
    private int defRating;

    public Armour(String name, int coinValue, int defRating) {
        super(name, coinValue);
        this.defRating = defRating;
    }

    @Override
    public String toString() {
        return "Armour{" +
                "name=" + this.getName() +
                ", defRating=" + defRating +
                '}';
    }
}
