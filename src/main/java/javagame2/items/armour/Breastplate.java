package javagame2.items.armour;

import lombok.Data;

@Data
public class Breastplate extends Armour {
    public Breastplate(String name, int coinValue, int defRating) {
        super(name, coinValue, defRating);
    }
}
