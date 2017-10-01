package javagame2.items;

import lombok.Data;

@Data
public class CoinBag extends Item {
    private int amountOfCoins;

    public CoinBag( int coinValue) {
        super("Bag of Coins", coinValue);
        this.amountOfCoins = coinValue;
    }

    @Override
    public String getName(){
        return "Bag of Coins(" + this.amountOfCoins+ ")";
    }
}
