package entity;

import utility.Stats;

import java.util.ArrayList;

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
        stats.setCurrentHealth(stats.getCurrentHealth() - damage + damage > 0? damage *
                ((stats.getArmor() < Stats.getMaxArmor()? stats.getArmor() : Stats.getMaxArmor())
                        / Stats.getMaxArmor()) : 0);

        if(stats.getCurrentHealth() <= 0) {
            handleDeath();
            return;
        }

        if(stats.getCurrentHealth() > stats.getMaxHealth()) {
            stats.setCurrentHealth(stats.getMaxHealth());
        }
    }

    public void useSpeed(float amount){
        getStats().setCurrentSpeed(getStats().getCurrentSpeed() - amount);
    }

    public void increaseStats(Stats increase) {
        getStats().setMaxHealth(getStats().getMaxHealth() + increase.getMaxHealth());
        getStats().setCurrentHealth(getStats().getCurrentHealth() + increase.getCurrentHealth());
        getStats().setArmor(getStats().getArmor() + increase.getArmor());
        getStats().setDamage(getStats().getDamage() + increase.getDamage());
        getStats().setMaxSpeed(getStats().getMaxSpeed() + increase.getMaxSpeed());
        getStats().setCurrentSpeed(getStats().getCurrentSpeed() + increase.getCurrentSpeed());
    }
    public void decreaseStats(Stats decrease){
        getStats().setMaxHealth(getStats().getMaxHealth()         -decrease.getMaxHealth());
        getStats().setCurrentHealth(getStats().getCurrentHealth() -decrease.getCurrentHealth());
        getStats().setArmor(getStats().getArmor()                 -decrease.getArmor());
        getStats().setDamage(getStats().getDamage()               -decrease.getDamage());
        getStats().setMaxSpeed(getStats().getMaxSpeed()           -decrease.getMaxSpeed());
        getStats().setCurrentSpeed(getStats().getCurrentSpeed()   -decrease.getCurrentSpeed());
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
        return name + " " +  stats;
    }
}
