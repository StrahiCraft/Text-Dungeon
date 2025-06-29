package entity;

import utility.Stats;

public class Entity {
    private Stats stats;
    private String name;

    public Entity() {
    }

    public Entity(Stats stats, String name) {
        this.stats = stats;
        this.name = name;
    }

    public void takeDamage(float damage) {
        stats.setCurrentHealth(stats.getCurrentHealth() - damage * (stats.getArmor() / Stats.getMaxArmor()));

        if(stats.getCurrentHealth() <= 0) {
            // death
            return;
        }

        if(stats.getCurrentHealth() > stats.getMaxHealth()) {
            stats.setCurrentHealth(stats.getMaxHealth());
        }
    }

    @Override
    public String toString() {
        return name + ", " +  stats;
    }
}
