package javagame2.items.scrolls;

import lombok.Data;

@Data
public class Map extends Scroll {

    public Map() {
        super("Map", 300, 0);
    }

    public Map(boolean trueMap) {
        super("True Map", 500, 0);
    }

    @Override
    public String toString() {
        if (this.getName().equalsIgnoreCase("True Map")) {
            return this.getName() + " shows the real map and sells for " + this.getCoinValue() + " coins.";
        } else {
            return this.getName() + " shows the basic map and sells for " + this.getCoinValue() + " coins.";
        }
    }
}
