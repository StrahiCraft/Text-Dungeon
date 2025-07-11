package entity.actions;

import entity.Entity;
import graphics.Color;

public abstract class CombatAction {
    private String name;
    private float speedCost;

    public CombatAction() {
        name = "Combat Action";
        speedCost = 0;
    }

    public CombatAction(String name, float speedCost) {
        this.name = name;
        this.speedCost = speedCost;
    }

    public abstract void useAction(Entity actionTarget, Entity actionUser);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getSpeedCost() {
        return speedCost;
    }

    public void setSpeedCost(float speedCost) {
        this.speedCost = speedCost;
    }

    /**
     * Add combat action type to each derived class.
     * @return instance of the class as a string.
     */
    @Override
    public String toString() {
        return name + ", costs" + speedCost + Color.getColor("bright blue") + " speed.";
    }
}
