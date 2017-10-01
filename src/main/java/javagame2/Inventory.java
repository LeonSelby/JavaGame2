package javagame2;

import javagame2.items.Item;
import javagame2.items.armour.Armour;
import javagame2.items.potions.Potion;
import javagame2.items.scrolls.Scroll;
import javagame2.items.weapons.Weapon;
import lombok.Data;

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

    public boolean checkIfInventoryIsFull(Item item){
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
        }catch (NullPointerException npe){ tmp = false; }
        return tmp;
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
            GameUtility.printToConsole(GameUtility.itemNotPresentString());
        }
    }

    private void removeWeaponFromInventory(Weapon weapon) {
        try {
            this.listOfWeapons.remove(weapon);
        } catch (NullPointerException e) {
            GameUtility.printToConsole(GameUtility.itemNotPresentString());
        }
    }

    private void removePotionFromInventory(Potion potion) {
        try {
            this.listOfPotions.remove(potion);
        } catch (NullPointerException e) {
            GameUtility.printToConsole(GameUtility.itemNotPresentString());
        }
    }

    private void removeScrollFromInventory(Scroll scroll) {
        try {
            this.listOfScrolls.remove(scroll);
        } catch (NullPointerException e) {
            GameUtility.printToConsole(GameUtility.itemNotPresentString());
        }
    }
}
