package javagame2.entities.combatunits;

import javagame2.Direction;
import javagame2.Inventory;
import javagame2.items.Item;
import javagame2.items.armour.Armour;
import javagame2.items.potions.Potion;
import javagame2.items.weapons.Weapon;
import lombok.Data;

import static javagame2.Direction.*;
import static javagame2.GameUtility.*;
import static javagame2.TakeInput.*;


import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;



@Data
public class Player extends CombatUnit {
    private int experiencePoints = 1;
    private Inventory inventory = new Inventory();
    private List<Armour> equippedArmour = new ArrayList<Armour>(4);
    private List<Weapon> equippedWeapon = new LinkedList<Weapon>();
    private Point location;
    private Point prevLocation;

    //Constructor
    public Player() {
        super("Placeholder");
        printToConsole(askForNameString());
        String name = takeNextLine();
        this.setName(name);
        this.getInventory().populatePlayersInventory();
        this.setHealthMax(40);
        this.setHealthCurrent(40);
        this.location = new Point(10, 1);
        this.prevLocation = new Point(10, 1);
    }
    //Methods
    //Combat
    public void takeHpPotion() {
        if (this.getInventory().getListOfPotions().isEmpty()) {
            printToConsole(noPotionsString());
        }else if(this.getHealthMax() == this.getHealthCurrent()) {printToConsole(fullHealthString());
        }else {
            Potion potion = this.getInventory().getListOfPotions().get(0);
            this.gainHP(potion.getRecoveryAmount());
            this.getInventory().removeItemFromInventory(potion);
        }
    }

    //Shop
    public void buy(Item item) {
        if (this.getInventory().checkIfCanAfford(item)){
            this.getInventory().addItemToInventory(item);
            this.getInventory().removeCoins(item.getCoinValue());
        }else{printToConsole(notEnoughCoinsPurchaseString()); }
    }

    public void sell(Item item){
        if(this.getInventory().checkIfItemIsPresent(item)){
            this.getInventory().removeItemFromInventory(item);
            this.getInventory().addCoins(item.getCoinValue());
        }else{printToConsole(cantSellItemNotInInvString()); }
    }

    //Inventory
    public void equip(Armour armour){
        if(!this.getInventory().getListOfArmour().contains(armour)){
            printToConsole(cantEquipArmourNotInInvString());
        }else {
            if (this.getEquippedArmour().isEmpty()) {
                this.getEquippedArmour().add(armour);
            } else {
                Armour temp = null;
                for (Armour a : this.getEquippedArmour()) {
                    if (a.getClass() == armour.getClass()){temp = a;}}
                this.unequip(temp);
                this.getEquippedArmour().add(armour);
                this.getInventory().getListOfArmour().remove(armour);
            }
        }
    }

    public void unequip(Armour armour){
        if(!this.getEquippedArmour().contains(armour)){
            printToConsole(cantUnEquipArmourNotWearingString());
        }else{
            this.getInventory().getListOfArmour().add(armour);
            this.getEquippedArmour().remove(armour);
        }
    }

    public void wield(Weapon weapon){
        if(!this.getInventory().getListOfWeapons().contains(weapon)){
            printToConsole(cantEquipWeaponNotInInvString());
        }else{
            Weapon temp;
            if(this.getEquippedWeapon().isEmpty()){
                this.getEquippedWeapon().add(weapon);
            }else {
                temp = this.getEquippedWeapon().get(0);
                this.unWield(temp);
                this.getEquippedWeapon().add(weapon);
                this.getInventory().getListOfWeapons().remove(weapon);
            }
        }
    }

    public void unWield(Weapon weapon){
        if(!this.getEquippedWeapon().contains(weapon)){
           printToConsole(cantUnEquipWeaponNotWearingString());
        }else{
            this.getInventory().getListOfWeapons().add(weapon);
            this.getEquippedWeapon().remove(weapon);
        }
    }

    //Move
    public Point attemptMove() {
        Direction dir = requestMovementDirection(movementStringQuestion());
        Point attemptedDest = null;
        if (dir == Dir_NORTH){
            attemptedDest = new Point(this.location.x - 1, this.location.y);
        }else if(dir == Dir_EAST){
            attemptedDest = new Point(this.location.x, this.location.y + 1);
        }else if(dir == Dir_SOUTH){
            attemptedDest = new Point(this.location.x + 1, this.location.y);
        }else if(dir == Dir_WEST){
            attemptedDest = new Point(this.location.x, this.location.y - 1);
        }
        return attemptedDest;
    }

    //Level
    public void checkIfShouldLevelUp(){
        if(this.getLevel() < 10){
            double currentEXP = Math.floor(this.getExperiencePoints()/100) + 1;
            if(this.getLevel() < currentEXP){
//                System.out.println((currentEXPmod + " is bigger than " + this.getLevel()));
            this.levelUp();
            this.checkIfShouldLevelUp();
        }}
    }

    //Override CombatUnit
    @Override
    public int getAtkRating() {
        int rating = getAtkRatingBase();
        for (Weapon w : this.equippedWeapon) {rating += w.getAtkRating();}
        return rating;
    }

    @Override
    public int getDefRating() {
        int rating = getDefRatingBase();
        for (Armour a : this.equippedArmour) {rating += a.getDefRating();}
        return rating;
    }

    @Override
    public String toString() {
        return this.getName() +
                ": experiencePoints=" + experiencePoints +
                ", level=" + this.getLevel() +
                ", attack Rating=" + this.getAtkRating() +
                ", defense Rating=" + this.getDefRating() +
                '}';
    }
}
