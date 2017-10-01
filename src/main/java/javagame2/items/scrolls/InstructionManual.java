package javagame2.items.scrolls;

import lombok.Data;

@Data
public class InstructionManual extends Scroll {
    public InstructionManual() {
        super("Instruction Manual", 300, 25);
    }
}
