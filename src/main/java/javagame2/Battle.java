package javagame2;

import javagame2.entities.combatunits.CombatUnit;
import javagame2.entities.combatunits.Player;
import javagame2.entities.combatunits.enemies.*;
import javagame2.items.CoinBag;
import javagame2.items.Item;
import javagame2.items.potions.Potion;
import lombok.Data;

import static javagame2.GameUtility.*;
import static javagame2.TakeInput.*;


import java.util.Random;

@Data
public class Battle {
    private Player player;
    private Enemy enemy;
    private Boss boss;

    //Constructor
    public Battle(Player player1) {
        this.player = player1;
        this.enemy = determineEnemy(false);
        this.player.setInCombat(true);
    }

    public Battle(Player player1, boolean hard) {
        this.player = player1;
        this.enemy = determineEnemy(hard);
        this.player.setInCombat(true);
    }

    //Methods

    public void randomBattle(){
        printToConsole(battleIntroString(enemy));
        while(!player.isDead() && !enemy.isDead() && player.isInCombat()) {
           printToConsole(hpDeclarations(player, enemy));
            turn(player);
            printToConsole(hpDeclarations(player, enemy));
            if (!enemy.isDead()) {
                turn(enemy);} }
        try {
           printToConsole(winnerString(determineWinner()));
        }catch (NullPointerException npe){
         printToConsole(fleeSuccessString());
        }
        awardEXP();
        awardLoot();
    }

    public void bossBattle(Boss boss){
        printToConsole((battleIntroString(boss)));
        while(!player.isDead() && !boss.isDead() && player.isInCombat()){
            printToConsole(" ");
            printToConsole(hpDeclarations(player, boss));
            turn(player);
            printToConsole(hpDeclarations(player, boss));
            if(!boss.isDead()) {
                turn(boss);} }
        try {
            printToConsole(winnerString(determineWinner()));
        }catch (NullPointerException npe){
            printToConsole(fleeSuccessString());
        }
        awardEXP();
        awardLoot();
    }

    private void turn(CombatUnit combatUnit) {
        if(combatUnit instanceof Player){
            int ans = offerOptions();
            switch (ans) {
                case 1:
                    player.attack(this.getEnemy());
                    break;
                case 2:
                    player.takeHpPotion();
                    break;
                case 3:
                    player.flee();
                    break;
            }
        }else{
            for( int i = 0; i < enemy.getNumberOfAttacks(); i++){
            enemy.attack(player);}
        }
    }

    private Enemy determineEnemy(boolean hard) {
        Enemy enemy = null;
        Random random = new Random();
        int a = random.nextInt(5) + 1;
        if(hard){
            a = a + 2;
        }
        int level = player.getLevel();
        switch (level){
            case 1:
                enemy = new Undead("Zombie", random.nextInt(20)+16,
                        3, 1);
                break;
            case 2:
                if(a<3){
                    enemy = new Undead("Zombie", random.nextInt(20)+16,
                            3, 1);
                }else{
                    enemy = new Goblin("Gobbo", random.nextInt(25)+17,
                            2, 2);
                }
                break;
            case 3:
                if(a<2){
                    enemy = new Undead("Zombie", random.nextInt(20)+16,
                            3, 1);
                }else if(a<4){
                    enemy = new Goblin("Big Gobbo", random.nextInt(45)+35,
                            4, 3);
                }else{
                    enemy = new Elf("Blood Elf", random.nextInt(60)+55,
                            7, 5);
                }
                break;
            case 4:
                if(a<2){
                    enemy = new Goblin("Big Gobbo", random.nextInt(45)+35,
                            4, 3);
                }else if(a<3){
                    enemy = new Elf("Blood Elf", random.nextInt(60)+55,
                            7, 5);
                }else{
                    enemy = new Orc("HellScream", random.nextInt(100)+99,
                            10, 7);
                }
                break;
            case 5: case 6:
                if(a<2){
                    enemy = new Goblin("Big Gobbo", random.nextInt(45)+35,
                            4, 3);
                }else if(a<3){
                    enemy = new Elf("Blood Elf", random.nextInt(60)+55,
                            7, 5);
                }else if(a<5){
                    enemy = new Orc("HellScream", random.nextInt(100)+99,
                            10, 7);
                }else {
                    enemy = new Orc("Thrall", random.nextInt(150)+100,
                            11, 9);
                }
                break;
            case 7: case 8: case 9: case 10:
                if(a<3){
                    enemy = new Elf("Blood Elf", random.nextInt(90)+75,
                            8, 6);
                }else if(a<5){
                    enemy = new Orc("Corrupted HellScream", random.nextInt(150)+129,
                            13, 9);
                }else {
                    enemy = new Orc("Thrall Jesus", random.nextInt(200)+180,
                            15, 12);
                }
                break;
        }
        if (hard){
            if (enemy != null) {
                enemy.setAtkRating(enemy.getAtkRating() + 1);
                enemy.setDefRating(enemy.getDefRating() + 1);
                enemy.setHealthMax(enemy.getHealthMax() + 25);
                enemy.setHealthCurrent(enemy.getHealthCurrent() + 25);
            }
        }
        return enemy;
    }

    private void awardLoot() {
        if (enemy.isDead()) {
            Random random = new Random();
            int o = random.nextInt(3) + 1;
            if (o > 1) {
                Item loot = determineItemLoot();
               printToConsole(declareLootString(loot));
                player.getInventory().addItemToInventory(loot);
            } else {
                int coinsReward = random.nextInt(30) + 20;
                Potion potion = new Potion(50);
                CoinBag coinBag = new CoinBag(coinsReward);
                player.getInventory().addItemToInventory(potion);
                player.getInventory().addItemToInventory(coinBag);
            }
        }
    }

    private int determineEXP(){
        int exp = 0;
        if(enemy.isDead()) {
            int enemyHealthMax = enemy.getHealthMax();
            if (enemyHealthMax < 20) {
                exp = 10;
            } else if (enemyHealthMax < 30) {
                exp = 15;
            } else if (enemyHealthMax < 60) {
                exp = 20;
            } else if (enemyHealthMax < 80) {
                exp = 25;
            } else if (enemyHealthMax < 100) {
                exp = 30;
            } else {
                exp = 50;
            }
        }
        return exp;
    }

    private void awardEXP(){
        int exp = determineEXP();
        printToConsole(declareEXPString(exp, player));
        player.setExperiencePoints(player.getExperiencePoints()+ exp);
        player.checkIfShouldLevelUp();
    }

    private int offerOptions(){
        return requestInputInRange(optionsString(), 1, 3);
    }

    private CombatUnit determineWinner(){
        if(enemy.isDead()){
            return player;
        }else if(player.isDead()){
            return enemy;
        }
        return null;
    }
}
