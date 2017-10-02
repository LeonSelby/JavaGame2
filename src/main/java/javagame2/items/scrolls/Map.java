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
}
