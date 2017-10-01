package javagame2.items.armour;

import lombok.Data;

@Data
public class Leggings extends Armour {
    public Leggings(String name, int coinValue, int defRating) {
        super(name, coinValue, defRating);
    }
}
