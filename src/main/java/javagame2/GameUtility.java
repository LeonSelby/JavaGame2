package javagame2;

import javagame2.entities.combatunits.CombatUnit;
import javagame2.entities.combatunits.Player;
import javagame2.entities.combatunits.enemies.Enemy;
import javagame2.items.CoinBag;
import javagame2.items.Item;
import javagame2.items.armour.Boots;
import javagame2.items.armour.Breastplate;
import javagame2.items.armour.Helmet;
import javagame2.items.armour.Leggings;
import javagame2.items.weapons.Sword;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
public class GameUtility {
    private List<Integer> listOfUnMoveableTiles = new ArrayList<>();
    private List<Integer> listOfInteractiveTiles = new ArrayList<>();

    public GameUtility() {
        this.listOfUnMoveableTiles.add(-1);
        this.listOfUnMoveableTiles.add(1);
        this.listOfUnMoveableTiles.add(2);
        this.listOfUnMoveableTiles.add(5);
        this.listOfUnMoveableTiles.add(6);
        this.listOfUnMoveableTiles.add(8);
        this.listOfUnMoveableTiles.add(10);

        this.listOfInteractiveTiles.add(1);
        this.listOfInteractiveTiles.add(2);
        this.listOfInteractiveTiles.add(5);
        this.listOfInteractiveTiles.add(6);
        this.listOfInteractiveTiles.add(8);
        this.listOfInteractiveTiles.add(10);
    }

    //Methods
    public static void printToConsole(String input){
        System.out.println(input);
    }

    public static Item determineItemLoot(){
        Item temp = null;
        Random random = new Random();
        int n = random.nextInt(10) + 1;
        int m = random.nextInt(6) + 1;
        if (n < 6) {
            switch (m) {
                case 1:
                    temp = new Helmet("Cloth Helmet", 15, 1, 10);
                    break;
                case 2:
                    temp = new Breastplate("Cloth Shirt", 15, 1);
                    break;
                case 3:
                    temp = new Leggings("Cloth Trousers", 15, 1);
                    break;
                case 4:
                    temp = new Boots("Cloth Boots", 15, 1, 10);
                    break;
                case 5:
                    temp = new Sword("Wooden Sword", 20, 3);
                    break;
                case 6:
                    temp = new CoinBag(10);
                    break;
            }
        } else if (n < 10 && n > 5) {
            switch (m) {
                case 1:
                    temp = new Helmet("Leather Helmet", 30, 3, 50);
                    break;
                case 2:
                    temp = new Breastplate("Leather Shirt", 30, 3);
                    break;
                case 3:
                    temp = new Leggings("Leather Trousers", 30, 3);
                    break;
                case 4:
                    temp = new Boots("Leather Boots", 30, 3, 50);
                    break;
                case 5:
                    temp = new Sword("Valyrian Sword", 75, 12);
                    break;
                case 6:
                    temp = new CoinBag(10);
                    break;
            }
        } else if (n < 11 && n > 9) {
            switch (m) {
                case 1:
                    temp = new Helmet("Plate Helmet", 60, 7, 100);
                    break;
                case 2:
                    temp = new Breastplate("Plate Shirt", 60, 7);
                    break;
                case 3:
                    temp = new Leggings("Plate Trousers", 60, 7);
                    break;
                case 4:
                    temp = new Boots("Plate Boots", 60, 7, 100);
                    break;
                case 5:
                    temp = new Sword("Master Sword", 200, 30);
                    break;
                case 6:
                    temp = new CoinBag(10);
                    break;
            }
        }
        return temp;
    }

    //Strings
    //Meta
    public static String welcomeToGameString(){return "Welcome to BarronMoore! Defeat the two bosses to win the game!";}

    public static String noticeBoardAheadString(){return "A noticeboard is to the East, take a look to get a lay of the land!";}

    public static String presentNoticeboardOptions(){return "Which part of the noticeboard would you like to see? \n" +
            " 1. Map \n 2. Map legend \n 3. Walk Away.";}

    public static String mapLegendString(){return " X: Barrier. C: Chest. S: Shop \n M: Danger! W: Danger+! +:Strange Obelisk \n" +
            " B: Boss N: Noticeboard R:Rare Chest U: You!";}
    //Character
    public static String askForNameString(){return "What is your name?";}

    public static String levelUpString(){return "You have gained a level! Ratings have increased by 2, and health by 20!";}

    public static String presentOptions(){
        return "What would you like to do?" + "\n 1. Move. \n 2. See Map.\n" +
                " 3. See Equipped Items. \n 4. See Inventory. \n 5. Take Potion. \n 6. See Statistics.";}

    public static String noMapString(){return "You don't own a Map!";}

    public static String bossWarningString(){return "A boss looms ahead, be careful!";}

    //Combat
    public static String fleeFailureString(){return "Flee failed!";}

    public static String fleeSuccessString(){return "Flee successful!";}

    public static String dodgeSuccessString(){return "Dodge successful! Damage avoided!";}

    public static String critSuccessString(){return "Crit successful! Attack Rating doubled!";}

    public static String blockSuccessString(){return "Block successful! Defense Rating doubled!";}

    public static String declareDMG(int dmg, CombatUnit tar){return tar.getName() + " has taken " + dmg + "damage!";}

    public static String fullHealthString(){return "You have full health!";}

    public static String declareLootString(Item loot){return loot.getName() + " has dropped!";}

    public static String declareEXPString(int exp, Player player){return player.getName() + " has gained " + exp + " experience!";}

    public static String optionsString(){
        return "Please select your next action:\n1. Attack\n2. Take Potion\n3. Flee";
    }

    public static String hpDeclarations(Player player, Enemy enemy){
        return player.getName() + " has " + player.getHealthCurrent() + "HP left, and " + enemy.getName()
                + " has "+ enemy.getHealthCurrent() + "HP left.";
    }

    public static String declareHPLoss(CombatUnit combatUnit, int hpToLose) {
        return combatUnit.getName() + " has lost " + hpToLose + " HP!";
    }

    public static String winnerString(CombatUnit input) {
        return input.getName() + " has won this battle!";
    }

    public static String battleIntroString(Enemy enemy) {
        return enemy.getName() + " has appeared!";
    }

    //Inventory
    public static String cantEquipArmourNotInInvString(){return "You can't equip armour not in your inventory!";}

    public static String cantUnEquipArmourNotWearingString(){return "You can't unequip armour you're not wearing!";}

    public static String cantEquipWeaponNotInInvString(){return "You can't equip weapons not in your inventory!";}

    public static String cantUnEquipWeaponNotWearingString(){return "You can't unequip weapons you're not holding!";}

    public static String itemNotPresentString(){ return "That item isn't present in this inventory."; }

    public static String cantSellItemNotInInvString(){return "You can't sell an item not in your inventory!";}

    public static String noPotionsString(){return "You have no potions!";}

    public static String notEnoughCoinsPurchaseString() {
        return "You can't spend that many coins!";
    }

    public static String coinReporter(int amount) {
        return "You have " + amount + " coin(s) in your bag!";
    }

    public static String inventoryIsFullString(){return "Your inventory is too full for anymore items of this kind!";}

    public static String inventoryIsFullString(String list){return "Your " + list + " storage is too full for anymore items of this kind!";}

    public static String addedToInventoryString(Item loot){return loot.getName() + " has been added to inventory!";}

    public static String presentInvOptions(){
        return "What would you like to do?\n 1. See Items \n 2. Check if full. \n 3. Search.";}

    public static String askForNameOfItem(){return "Please type a name to search for.";}

    public static String itemIsPresentString(Item item){return item.getName() + " is in your inventory!";}

    public static String itemIsPresentString(String item){return item + " is in your inventory!";}

   public static String inventoryIsNotFullString(String inventory){return "Your " + inventory + " is not full.";}
    //Movement
    public static String movementStringQuestion(){return "Which direction would you like to move?";}

    public static String determineTargetTile(int targetTile){
        String tile = "";
        if(targetTile == 1) {
            tile = "Chest";
        }else if(targetTile == -1){
            tile = "Barrier";
        }else if(targetTile == 2){
            tile = "Shop";
        }else if(targetTile == 5){
            tile = "Boss";
        }else if(targetTile == 6){
            tile = "Obelisk";
        }else if (targetTile == 8){
            tile = "Noticeboard";
        }else if(targetTile == 10){
            tile = "Large Chest";
        }
        return tile;
    }

    public static String cantMoveString(){return "You can't move there!";}

    public static String interactString(String tile){return "But you can interact with " + tile + ", would you like to?";}

    public static String blocksYourWayString(String tile){return "A " + tile + " blocks your way!";}


    //Store
    public static String welcomeToStoreString(){return "Welcome to my Store!";}

    public static String welcomeToTravellingStoreString(){return "Hello! I travel around looking for adventurers to sell to.";}

    public static String presentStoreOptions(){return null;}
}
