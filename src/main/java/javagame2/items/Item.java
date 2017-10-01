package javagame2.items;

import lombok.Data;

@Data
abstract public class Item {

    private String name;
    private int coinValue;

    //Constructor
    public Item(String name, int coinValue) {
        this.name = name;
        this.coinValue = coinValue;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", coinValue=" + coinValue +
                '}';
    }
}
