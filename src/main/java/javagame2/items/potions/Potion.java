package javagame2.items.potions;

import javagame2.items.Item;
import lombok.Data;

@Data
public class Potion extends Item {

    private int recoveryAmount;

    public Potion(int recoveryAmount) {
        super("Potion", 15);
        this.recoveryAmount = recoveryAmount;
    }

    public String toString() {
        return this.getName() +
                " value=" + this.getCoinValue() +
                ", recovery amount= " + this.getRecoveryAmount() +
                '}';
    }
}
