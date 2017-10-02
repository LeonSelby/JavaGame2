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
import java.util.Random;

import static javagame2.GameUtility.*;
import static javagame2.TakeInput.*;


@Data
public class Game {
    private Boss tyrant = new Boss("Tyrant", 1000, 30, 30);
    private Boss werewolf = new Boss("Werewolf", 900, 20, 10);
    private GameUtility gameUtils = new GameUtility();
    private Player player1 = new Player();
    private Board board = new Board(12, player1);
    private Board unalteredBoard = new Board(12, player1);
    private Vendor shop = new Vendor(false);
    private Vendor merchant = new Vendor(true);
    private int deadBosses = 0;
    private int combatChance = 33;

    public Game() {
        werewolf.setNumberOfAttacks(3);
        werewolf.setDodgeChance(0);
        tyrant.setCritChance(25);
        printToConsole(welcomeToGameString());
        printToConsole(noticeBoardAheadString());
    }

    public void takeTurn(){
        int ans = TakeInput.requestInputInRange(presentOptions(), 1, 10);
        if(ans == 1){
            movePlayer();
        }else if(ans == 2){
            if(player1.getInventory().checkIfItemIsPresent("map")){board.printBoard();}
            else if( player1.getInventory().checkIfItemIsPresent("True Map")){board.printTrueBoard();}
            else{printToConsole(noMapString());}
        }else if(ans == 3){
            for (Weapon w: player1.getEquippedWeapon()) {printToConsole(w.toString()); }
            for (Armour a: player1.getEquippedArmour()) {printToConsole(a.toString()); }
        }else if(ans == 4){
            player1.getInventory().open();
        }else if(ans == 5){
            player1.takeHpPotion();
        }else if(ans == 6){
            printToConsole(player1.toString());
        }
    }

    private void movePlayer(){
        Point startingLoc = player1.getLocation();
        Point targetLoc = player1.attemptMove();
        int targetTile = board.getBoardArray()[targetLoc.x][targetLoc.y];
        String tile = determineTargetTile(targetTile);
        if (gameUtils.getListOfUnMoveableTiles().contains(targetTile)){
            printToConsole(cantMoveString());
            if(gameUtils.getListOfInteractiveTiles().contains(targetTile)){
                boolean ans = TakeInput.requestYesOrNo(interactString(tile));
                if(ans){
                    if(targetTile == 1){
                        Item item = determineItemLoot();
                        player1.getInventory().addItemToInventory(item);
                        depleteTile(targetLoc);
                    }else if(targetTile == 2){
                        shop.visit();
                    }else if(targetTile == 5){
                        Battle battle = new Battle(player1, false);
                        if(!tyrant.isDead()){battle.bossBattle(tyrant);} else if (werewolf.isDead()){ battle.bossBattle(werewolf);}
                        depleteTile(targetLoc);
                    }else if(targetTile == 6) {
                        printToConsole("Buffs not yet added!");
                    }else if(targetTile == 8) {
                        openNoticeboard();
                    }else if(targetTile == 9){
                        printToConsole("That " + tile + " has already been spent!");
                    }else if(targetTile == 10){
                        Sword superSword = new Sword("Super Sword", 1000, 60);
                        player1.getInventory().addItemToInventory(superSword);
                        depleteTile(targetLoc);
                    }
                }
            }else {
                printToConsole(blocksYourWayString(tile));}
        } else{
            player1.setLocation(targetLoc);
            player1.setPrevLocation(startingLoc);
            checkIfCombatSpawns(targetTile);
            checkForMessage(targetTile, targetLoc);
        }
        board.updateBoard();
    }

    private void checkForMessage(int targetTile, Point targetLoc) {
        if(targetTile==7){
            printToConsole(bossWarningString());
            depleteTile(targetLoc);
        }
    }

    private void checkIfCombatSpawns(int targetTile) {
        Random random = new Random();
        int n = random.nextInt(100) + 1;
        if(n > (100 - combatChance)) {
            if (targetTile == 3) {
                Battle battle = new Battle(player1, false);
                battle.randomBattle();
            } else if (targetTile == 4) {
                Battle battle = new Battle(player1, true);
                battle.randomBattle();
            }
        }
    }

    private void openNoticeboard(){
        int ans = requestInputInRange(presentNoticeboardOptions(), 3);
        switch (ans){
            case 1:
                board.printBoard();
                break;
            case 2:
                printToConsole(mapLegendString());
                break;
            case 3:
                break;
        }
    }


    private void depleteTile(Point targetLoc){
        board.getBoardArray()[targetLoc.x][targetLoc.y] = 9;
        board.getPureBoard()[targetLoc.x][targetLoc.y] = 9;
    }
}
