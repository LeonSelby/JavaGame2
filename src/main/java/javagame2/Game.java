package javagame2;

import javagame2.entities.combatunits.Player;
import javagame2.entities.combatunits.enemies.Boss;
import javagame2.entities.vendors.Vendor;
import javagame2.items.Item;
import javagame2.items.armour.Armour;
import javagame2.items.weapons.Sword;
import javagame2.items.weapons.Weapon;
import lombok.Data;

import java.awt.*;

import static javagame2.GameUtility.*;

@Data
public class Game {
    private Boss tyrant = new Boss("Tyrant", 1000, 30, 30);
    private Boss werewolf = new Boss("Werewolf", 900, 20, 10);
    private GameUtility gameUtils = new GameUtility();
    private Player player1 = new Player("Leon");
    private Board board = new Board(12, player1);
    private Board unalteredBoard = new Board(12, player1);
    private Vendor shop = new Vendor(false);
    private Vendor merchant = new Vendor(true);
    private int deadBosses = 0;

    public Game() {
        werewolf.setNumberOfAttacks(3);
        werewolf.setDodgeChance(0);
        tyrant.setCritChance(25);
    }

    public void takeTurn(){
        int ans = TakeInput.requestInputInRange(presentOptions(), 1, 10);
        if(ans == 1){
            movePlayer();
        }else if(ans == 2){
            if(player1.getInventory().checkIfItemIsPresent("map")){board.printBoard();}
            else{printToConsole(noMapString());}
        }else if(ans == 3){
            for (Weapon w: player1.getEquippedWeapon()) {
                System.out.println(w.toString());
            }
            for (Armour a: player1.getEquippedArmour()) {
                System.out.println(a.toString());
            }
        }else if(ans == 4){
            player1.getInventory().open();
        }else if(ans == 5){
            player1.takeHpPotion();
        }else if(ans == 6){
            player1.toString();
        }
    }

    private void movePlayer(){
        Point startingLoc = player1.getLocation();
        Point targetLoc = player1.attemptMove();
        int targetTile = board.getBoardArray()[targetLoc.x][targetLoc.y];
        String tile = determineTargetTile(targetTile);
        if (gameUtils.getListOfUnMoveableTiles().contains(targetTile)){
            System.out.println("Can't move here.");
            if(gameUtils.getListOfInteractiveTiles().contains(targetTile)){
                boolean ans = TakeInput.requestYesOrNo
                        ("But you can interact with " + tile + ", would you like to?");
                if(ans){
                    if(targetTile == 1){
                        Item item = determineItemLoot();
                        player1.getInventory().addItemToInventory(item);
                        System.out.println(item.getName() + " has been added to bag!");
                        board.getBoardArray()[targetLoc.x][targetLoc.y] = 9; // UNDO COMMENT TO GIVE LOOT FOREVER
                        board.getPureBoard()[targetLoc.x][targetLoc.y] = 9; // UNDO COMMENT TO GIVE LOOT FOREVER
                    }else if(targetTile == 2){
                        shop.visit();
                    }else if(targetTile == 5){
                        Battle battle = new Battle(player1, false);
                        if(!tyrant.isDead()){battle.bossBattle(tyrant);} else if (werewolf.isDead()){ battle.bossBattle(werewolf);}
                        board.getBoardArray()[targetLoc.x][targetLoc.y] = 9; // UNDO COMMENT TO GIVE Boss FOREVER
                        board.getPureBoard()[targetLoc.x][targetLoc.y] = 9; // UNDO COMMENT TO GIVE Boss FOREVER
                    }else if(targetTile == 6) {
                        System.out.println("Buffs not yet added!");
                    }else if(targetTile == 9){
                        System.out.println("That " + tile + " has already been spent!");
                    }else if(targetTile == 10){
                        Sword superSword = new Sword("Super Sword", 1000, 60);
                        player1.getInventory().addItemToInventory(superSword);
                        System.out.println(superSword.getName()+ " has been added to bag!");
                        board.getBoardArray()[targetLoc.x][targetLoc.y] = 9; // UNDO COMMENT TO GIVE LOOT FOREVER
                        board.getPureBoard()[targetLoc.x][targetLoc.y] = 9; // UNDO COMMENT TO GIVE LOOT FOREVER
                    }
                }
            }else {
                System.out.print("A " + tile + " blocks your way!");
            }
        } else{
            player1.setLocation(targetLoc);
            player1.setPrevLocation(startingLoc);
        }
        board.updateBoard();
    }


}
