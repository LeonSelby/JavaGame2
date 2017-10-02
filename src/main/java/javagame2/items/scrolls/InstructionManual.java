package javagame2.items.scrolls;

import lombok.Data;

@Data
public class InstructionManual extends Scroll {
    public InstructionManual() {
        super("Instruction Manual", 300, 25);
    }

    @Override
    public String toString() {
        return this.getName() + ": value= " + this.getCoinValue() + " buffs crit chance by " + this.getPercentageBuff();
    }
}
