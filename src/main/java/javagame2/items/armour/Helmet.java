package javagame2.items.armour;

import lombok.Data;

@Data
public class Helmet extends Armour {
    private int trapProtection;
    private boolean negatesTraps = checkIfTrapsAreNegated();

    public Helmet(String name, int coinValue, int defRating, int trapProtection) {
        super(name, coinValue, defRating);
        this.trapProtection = trapProtection;
    }

    //Methods
    private boolean checkIfTrapsAreNegated() {
        return this.trapProtection == 100;
    }

    public String toString() {
        return this.getName() +
                " value=" + this.getCoinValue() +
                ", def rating= " + this.getDefRating() +
                ", negatesTraps=" + negatesTraps +
                '}';
    }
}
