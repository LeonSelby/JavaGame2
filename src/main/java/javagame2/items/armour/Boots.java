package javagame2.items.armour;

import lombok.Data;

@Data
public class Boots extends Armour {

    private int trapProtection;
    private boolean negatesTraps = checkIfTrapsAreNegated();

    public Boots(String name, int coinValue, int defRating, int trapProtection) {
        super(name, coinValue, defRating);
        this.trapProtection = trapProtection;
    }

    //Methods
    private boolean checkIfTrapsAreNegated(){return this.trapProtection == 100;}

    @Override
    public String toString() {
        return this.getName() +
                " value=" + this.getCoinValue() +
                ", def rating= " + this.getDefRating() +
                ", negatesTraps=" + negatesTraps +
        '}';
    }
}
