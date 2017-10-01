package javagame2.entities.vendors;

import javagame2.entities.Entity;
import lombok.Data;

@Data
public class Vendor extends Entity {

    private Inventory inventory;
    private boolean isTravelling = false;

    //Constructor
    public Vendor(String name, boolean isTravelling) {
        super(name);
        this.isTravelling = isTravelling;
        this.inventory = new Inventory();
        if (isTravelling){this.setUpTravellingInventory();}
        else{this.setUpStoreInventory();}
    }

    private void setUpTravellingInventory(){

    }

    private void setUpStoreInventory(){

    }

    public void visit(){
        if(isTravelling){GameUtility.printToConsole(GameUtility.welcomeToStoreString());}
        else{GameUtility.printToConsole(GameUtility.welcomeToTravellingStoreString());}

        //Method presents options for type of item
        //Method describes inventory of that item
        //Asks to select one, last option is reset
    }
}
