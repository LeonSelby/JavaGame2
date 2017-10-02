package javagame2;

import javagame2.items.CoinBag;
import javagame2.items.Item;
import javagame2.items.armour.Armour;
import javagame2.items.potions.Potion;
import javagame2.items.scrolls.Scroll;
import javagame2.items.weapons.Weapon;
import lombok.Data;

import static javagame2.GameUtility.*;
import static javagame2.TakeInput.*;

import java.util.ArrayList;
import java.util.List;

@Data
public class Inventory {
    private int capacity = 20;
    private int coinsInBag;

    private List<Armour> listOfArmour = new ArrayList<>(this.getCapacity());
    private List<Weapon> listOfWeapons = new ArrayList<>(this.getCapacity());
    private List<Potion> listOfPotions = new ArrayList<>(this.getCapacity());
    private List<Scroll> listOfScrolls = new ArrayList<>(this.getCapacity());


    //Methods
    public void populatePlayersInventory() {
        Potion hpPot = new Potion(15);
        this.listOfPotions.add(hpPot);
        this.addCoins(50);
    }

    public boolean checkIfItemIsPresent(String name){
       boolean ans = false;
        for (Armour a : listOfArmour){
            if (a.getName().equalsIgnoreCase(name)){ ans = true;} }
        for (Weapon w : listOfWeapons){
            if (w.getName().equalsIgnoreCase(name)){ ans = true;} }
        for (Potion p : listOfPotions){
            if (p.getName().equalsIgnoreCase(name)){ ans = true;} }
        for (Scroll s : listOfScrolls){
            if (s.getName().equalsIgnoreCase(name)){ ans = true;} }
        return ans;
    }

    public boolean checkIfItemIsPresent(Item item) {
        boolean tmp = false;
        if (item instanceof Armour) {
            for (Armour a : this.getListOfArmour()) {
                if (a.equals(item)) {
                    tmp = true;
                }
            }
        } else if (item instanceof Weapon) {
            for (Weapon a : this.getListOfWeapons()) {
                if (a.equals(item)) {
                    tmp = true;
                }
            }
        } else if (item instanceof Potion) {
            for (Potion a : this.getListOfPotions()) {
                if (a.equals(item)) {
                    tmp = true;
                }
            }
        } else if (item instanceof Scroll) {
            for (Scroll a : this.getListOfScrolls()) {
                if (a.equals(item)) {
                    tmp = true;
                }
            }
        }
        return tmp;
    }

    private boolean checkIfInventoryIsFull(Item item){
        boolean tmp = true;
        Item temp;
        try {
            if (item instanceof Armour) {
                temp = listOfArmour.get(19);
            } else if (item instanceof Weapon) {
                temp =listOfWeapons.get(19);
            } else if (item instanceof Potion) {
                temp = listOfPotions.get(19);
            } else if (item instanceof Scroll) {
                temp =listOfScrolls.get(19);
            }
        }catch (IndexOutOfBoundsException e){ tmp = false; }
        return tmp;
    }

    private boolean checkIfInventoryIsFull(List<Item> list){
        boolean tmp = true;
        Item temp;
        try {
                temp = list.get(19);
        }catch (IndexOutOfBoundsException e){ tmp = false; }
        return tmp;
    }
    //Coins
    public void addCoins(int amount) {
        this.coinsInBag = this.getCoinsInBag() + amount;
    }

    public void removeCoins(int amount) {
        if(amount >= this.getCoinsInBag()){
            this.coinsInBag = 0;
        }else{
            this.coinsInBag = this.getCoinsInBag() - amount;
        }
    }

    private boolean checkIfCanAfford(int needed){
        return (this.getCoinsInBag()>=needed);
    }

    public boolean checkIfCanAfford(Item item){
        int needed = item.getCoinValue();
        return checkIfCanAfford(needed);
    }

    public void coinReport(){
        printToConsole(coinReporter(this.getCoinsInBag()));
    }

    //Remove from Inventory
    public void removeItemFromInventory(Item item){
        if(item instanceof Armour){
            removeArmourFromInventory((Armour)item);
        }else if(item instanceof Weapon){
            removeWeaponFromInventory((Weapon)item);
        }else if(item instanceof Potion){
            removePotionFromInventory((Potion)item);
        }else if(item instanceof Scroll){
            removeScrollFromInventory((Scroll)item);
        }
    }

    private void removeArmourFromInventory(Armour armour) {
        try {
            this.listOfArmour.remove(armour);
        } catch (NullPointerException e) {
            printToConsole(itemNotPresentString());
        }
    }

    private void removeWeaponFromInventory(Weapon weapon) {
        try {
            this.listOfWeapons.remove(weapon);
        } catch (NullPointerException e) {
            printToConsole(itemNotPresentString());
        }
    }

    private void removePotionFromInventory(Potion potion) {
        try {
            this.listOfPotions.remove(potion);
        } catch (NullPointerException e) {
            printToConsole(itemNotPresentString());
        }
    }

    private void removeScrollFromInventory(Scroll scroll) {
        try {
            this.listOfScrolls.remove(scroll);
        } catch (NullPointerException e) {
            printToConsole(itemNotPresentString());
        }
    }

    //Add to Inventory
    private void addArmourToInventory(Armour item) {this.listOfArmour.add(item);}

    private void addWeaponToInventory(Weapon item) {this.listOfWeapons.add(item);}

    private void addPotionToInventory(Potion potion) {this.listOfPotions.add(potion); }

    private void addScrollToInventory(Scroll scroll) { this.listOfScrolls.add(scroll); }

    public void addItemToInventory(Item item) {
        if(item instanceof CoinBag){
            addCoins(((CoinBag) item).getAmountOfCoins());
            printToConsole(addedToInventoryString(item));
        }else if (!checkIfInventoryIsFull(item)) {
            if (item instanceof Armour) {
                addArmourToInventory((Armour) item);
            } else if (item instanceof Weapon) {
                addWeaponToInventory((Weapon) item);
            } else if (item instanceof Potion) {
                addPotionToInventory((Potion) item);
            } else if (item instanceof Scroll) {
                addScrollToInventory((Scroll) item);
            }
            printToConsole(addedToInventoryString(item));
        }else{
            printToConsole(inventoryIsFullString());
        }
    }

    //Interact

    public void open(){
        int ans1 = requestInputInRange(presentInvOptions(), 3);
        switch (ans1){
            case 1:
                //see items
                break;
            case 2:
                checkIfInventoryIsFull()
        }
    }
}
