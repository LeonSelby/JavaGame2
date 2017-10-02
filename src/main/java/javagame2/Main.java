package javagame2;

import javagame2.items.scrolls.Map;
import javagame2.items.weapons.Sword;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        Sword sword = new Sword("Sword", 20, 15);
        Map map = new Map(true);
        game.getPlayer1().getInventory().addItemToInventory(sword);
        game.getPlayer1().getInventory().addItemToInventory(map);
        game.getPlayer1().wield(sword);

        while(game.getDeadBosses() != 2 || !game.getPlayer1().isDead()){
            game.takeTurn();
        }
    }
}


//Add Noticeboard with option to view the map and legend DONE
//Add inventory interaction menus
//Add items to each shop
//Add shop menu
//Set boss stats
//Possibly change chests to not give gold.
//Add Rarity Enum, polymorph loot method to use these.
//Add finding gold or poor items on floor.

