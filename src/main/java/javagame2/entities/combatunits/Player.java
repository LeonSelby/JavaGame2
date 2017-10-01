package javagame2.entities.combatunits;

import javagame2.GameUtility;
import javagame2.Inventory;
import javagame2.items.Item;
import javagame2.items.armour.Armour;
import javagame2.items.weapons.Weapon;
import lombok.Data;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;


@Data
public class Player extends CombatUnit {
    private int experiencePoints = 1;
    private Inventory inventory = new Inventory();
    private List<Armour> equippedArmour = new ArrayList<Armour>(4);
    private List<Weapon> equippedWeapon = new LinkedList<Weapon>();
    private Point location;
    private Point prevLocation;

    //Constructor
    public Player(String name) {
        super(name);
        this.getInventory().populatePlayersInventory();
    }
    //Methods
    //Combat
    public void takeHpPotion() {
        if (this.getInventory().getListOfPotions().isEmpty()) {
            GameUtility.printToConsole(GameUtility.noPotionsString());
        }else if(this.getHealthMax() == this.getHealthCurrent()) {GameUtility.printToConsole(GameUtility.fullHealthString());
        }else {
            HealthPotion potion = this.getInventory().getListOfPotions().get(0);
            this.gainHP(potion.getRecoveryAmount());
            this.getInventory().removePotionFromInventory(potion);
        }
    }

    //Shop
    public void buy(Item item) {
        if (this.getInventory().checkIfCanAfford(item)){
            this.getInventory().addItemToInventory(item);
            this.getInventory().removeCoins(item.getCoinValue());
        }else{GameUtility.printToConsole(GameUtility.notEnoughCoinsPurchaseString()); }
    }

    public void sell(Item item){
        if(this.getInventory().checkIfItemIsPresent(item)){
            this.getInventory().removeItemFromInventory(item);
            this.getInventory().addCoins(item.getCoinValue());
        }else{GameUtility.printToConsole(GameUtility.cantSellItemNotInInvString()); }
    }

    //javagame2.Inventory
    public void equip(Armour armour){
        if(!this.getInventory().getListOfArmour().contains(armour)){
            GameUtility.printToConsole(GameUtility.cantEquipArmourNotInInvString());
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
            GameUtility.printToConsole(GameUtility.cantUnEquipArmourNotWearingString());
        }else{
            this.getInventory().getListOfArmour().add(armour);
            this.getEquippedArmour().remove(armour);
        }
    }

    public void wield(Weapon weapon){
        if(!this.getInventory().getListOfWeapons().contains(weapon)){
            GameUtility.printToConsole(GameUtility.cantEquipWeaponNotInInvString());
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
            GameUtility.printToConsole(GameUtility.cantUnEquipWeaponNotWearingString());
        }else{
            this.getInventory().getListOfWeapons().add(weapon);
            this.getEquippedWeapon().remove(weapon);
        }
    }

    //Move
    public Point attemptMove() {
        Direction dir = TakeInput.requestMovementDirection(GameUtility.movementQuestionString());
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
            if(this.getLevel() < Math.ceil(this.getExperiencePoints()%100)){
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
        return "Player{" +
                "experiencePoints=" + experiencePoints +
                ", level=" + this.getLevel() +
                ", attack Rating=" + this.getAtkRating() +
                ", defense Rating=" + this.getDefRating() +
                '}';
    }
}
