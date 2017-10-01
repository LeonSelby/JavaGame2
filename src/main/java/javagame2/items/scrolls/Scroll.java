package javagame2.items.scrolls;

import javagame2.items.Item;
import lombok.Data;

@Data
abstract public class Scroll extends Item {
    private int percentageBuff;

    public Scroll(String name, int coinValue, int percentageBuff) {
        super(name, coinValue);
        this.percentageBuff = percentageBuff;
    }

    @Override
    public String toString() {
        return "Scroll{" +
                "name=" + this.getName() +
                ", percentageBuff=" + percentageBuff +
                '}';
    }
}
