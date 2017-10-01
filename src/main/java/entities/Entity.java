package entities;

import lombok.Data;

@Data
abstract public class Entity{
    private String name;
    private int healthMax = 1;
    private int healthCurrent = 1;
    private boolean isDead = false;

    public Entity(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "name='" + name + '\'' +
                ", healthMax=" + healthMax +
                ", healthCurrent=" + healthCurrent +
                ", isDead=" + isDead +
                '}';
    }
}
