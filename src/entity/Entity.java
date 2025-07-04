package entity;

import utility.Stats;

public abstract class Entity {
    private String name;
    private Stats stats;

    public Entity() {
        name = "Entity";
        stats = new Stats();
    }

    public Entity(String name, Stats stats) {
        this.name = name;
        this.stats = stats;
    }

    public void takeDamage(float damage) {
        stats.setCurrentHealth(stats.getCurrentHealth() - damage * (stats.getArmor() / Stats.getMaxArmor()));

        if(stats.getCurrentHealth() <= 0) {
            handleDeath();
            return;
        }

        if(stats.getCurrentHealth() > stats.getMaxHealth()) {
            stats.setCurrentHealth(stats.getMaxHealth());
        }
    }

    public abstract void handleDeath();

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name + ", " +  stats;
    }
}
