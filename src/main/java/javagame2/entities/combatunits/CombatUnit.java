package javagame2.entities.combatunits;

import javagame2.GameUtility;
import javagame2.entities.Entity;
import lombok.Data;

import static javagame2.GameUtility.*;

import java.util.Random;

@Data
abstract public class CombatUnit extends Entity {

    private int level = 1;
    private int AtkRatingBase = 1;
    private int DefRatingBase = 1;
    private int defRating;
    private int atkRating;
    private boolean inCombat = false;
    private boolean ableToCrit = true;
    private int critChance = 5;
    private int dodgeChance = 5;
    private int blockChance = 5;

    //Constructor
    public CombatUnit(String name) {
        super(name);
    }

    public void loseHP(int hpToLose){
        if(hpToLose < 0){hpToLose = 0;}//Prevents gaining health on loseHP Method.
        if (hpToLose >= this.getHealthCurrent()){
            this.setHealthCurrent(0);
            this.setDead(true);
        }else if(hpToLose < this.getHealthCurrent()){
            this.setHealthCurrent(this.getHealthCurrent()-hpToLose);
        }
    }

    public void gainHP(int hpToGain){
        int missingHp = this.getHealthMax() - this.getHealthCurrent();
        if(hpToGain < 0){hpToGain = 0;}//Prevents losing health on gainHP Method.
        if (hpToGain >= missingHp){
            this.setHealthCurrent(this.getHealthMax());
        }else if(hpToGain <= missingHp){
            this.setHealthCurrent(this.getHealthCurrent()+ hpToGain);
        }
    }

    public void flee(){
        Random random = new Random();
        int n = random.nextInt(20) + 1;
        if(n != 20) {this.setInCombat(false);}
        else{GameUtility.printToConsole(GameUtility.fleeFailureString());}
    }

    public void levelUp() {
        this.setLevel(this.getLevel() + 1);
        this.setAtkRatingBase(getAtkRatingBase() + 2);
        this.setDefRatingBase(getDefRatingBase() + 2);
        this.setHealthMax(this.getHealthMax() + 10);
        this.setHealthCurrent(this.getHealthCurrent() + 10);
printToConsole(levelUpString());
    }

    public void attack(CombatUnit target){
        int attackersAtkRating = this.getAtkRating();
        int defendersDefRating = target.getDefRating();
        if(this.determineIfCrit(this.getCritChance())){
            printToConsole(critSuccessString());
            attackersAtkRating += attackersAtkRating;
        }else if(target.determineIfDodge(this.getDodgeChance())){
            printToConsole(dodgeSuccessString());
            attackersAtkRating = 0;
        }else if(this.determineIfBlock(this.getBlockChance())){
            printToConsole(blockSuccessString());
            defendersDefRating = defendersDefRating + (defendersDefRating/2);
        }
        int damage = attackersAtkRating - defendersDefRating;
        target.loseHP(damage);
        printToConsole(declareDMG(damage, target));
    }

    //Determine Methods

    private boolean determineIfCrit(int critChance){
        if(this.ableToCrit) {
            Random random = new Random();
            int n = random.nextInt(20) + 1;
            return n >= (20 - (100 / critChance));
        }else{return false;}
    }

    private boolean determineIfBlock(int blockChance){
        Random random = new Random();
        int n = random.nextInt(20) + 1;
        return n >= (19 - (100 / blockChance));
    }

    private boolean determineIfDodge(int dodgeChance){
        Random random = new Random();
        int n = random.nextInt(20) + 1;
        return n >= (20 - (100 / dodgeChance));
    }

    //Override LomBok
    public int getAtkRating() {
        return this.getAtkRatingBase()+this.atkRating;
    }

    public int getDefRating() {
        return this.getDefRatingBase()+this.defRating;
    }

    //toString

    @Override
    public String toString() {
        return "CombatUnit{" +
                "level=" + level +
                ", defRating=" + defRating +
                ", atkRating=" + atkRating +
                ", ableToCrit=" + ableToCrit +
                ", critChance=" + critChance +
                ", dodgeChance=" + dodgeChance +
                ", blockChance=" + blockChance +
                '}';
    }
}
