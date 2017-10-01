package javagame2;

import javagame2.items.weapons.Sword;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        Sword sword = new Sword("Sword", 20, 15);
        game.getPlayer1().getInventory().addItemToInventory(sword);
        game.getPlayer1().wield(sword);

        while(game.getDeadBosses() != 2 || !game.getPlayer1().isDead()){
            game.takeTurn();
        }
    }
}
