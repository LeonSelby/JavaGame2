package javagame2.items.armour;

import lombok.Data;

@Data
public class Breastplate extends Armour {
    public Breastplate(String name, int coinValue, int defRating) {
        super(name, coinValue, defRating);
    }

    public String toString() {
        return this.getName() +
                " value=" + this.getCoinValue() +
                ", def rating= " + this.getDefRating() +
                '}';

    }
}
