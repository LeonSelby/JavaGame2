package javagame2;

import javagame2.entities.combatunits.CombatUnit;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
 public class GameUtility {
    private List<Integer> listOfUnMoveableTiles = new ArrayList<>();
    private List<Integer> listOfInteractiveTiles = new ArrayList<>();

    public GameUtility() {
        this.listOfUnMoveableTiles.add(-1);
        this.listOfUnMoveableTiles.add(1);
        this.listOfUnMoveableTiles.add(2);
        this.listOfUnMoveableTiles.add(6);
        this.listOfUnMoveableTiles.add(10);
        this.listOfUnMoveableTiles.add(5);

        this.listOfInteractiveTiles.add(1);
        this.listOfInteractiveTiles.add(2);
        this.listOfInteractiveTiles.add(5);
        this.listOfInteractiveTiles.add(6);
        this.listOfInteractiveTiles.add(10);
    }

    //Methods

    public static void printToConsole(String input){
        System.out.println(input);
    }

    //Strings

    //Combat
    public static String fleeFailureString(){return "Flee failed!";}

    public static String dodgeSuccessString(){return "Dodge successful! Damage avoided!";}

    public static String critSuccessString(){return "Crit successful! Attack Rating doubled!";}

    public static String blockSuccessString(){return "Block successful! Defense Rating doubled!";}

    public static String declareDMG(int dmg, CombatUnit tar){return tar.getName() + " has taken " + dmg + "damage!";}


    //Inventory

    public static String cantEquipArmourNotInInvString(){return "You can't equip armour not in your inventory!";}

    public static String cantUnEquipArmourNotWearingString(){return "You can't unequip armour you're not wearing!";}

    public static String cantEquipWeaponNotInInvString(){return "You can't equip weapons not in your inventory!";}

    public static String cantUnEquipWeaponNotWearingString(){return "You can't unequip weapons you're not holding!";}

   public static String itemNotPresentString(){ return "That item isn't present in this inventory."; }
}
