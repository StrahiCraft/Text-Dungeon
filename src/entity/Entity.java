package entity;

import entity.actions.CombatAction;
import utility.Stats;

import java.util.ArrayList;

public abstract class Entity {
    private String name;
    private Stats stats;

    private ArrayList<CombatAction> combatActions;

    public Entity() {
        name = "Entity";
        stats = new Stats();
        combatActions = new ArrayList<CombatAction>();
    }

    public Entity(String name, Stats stats) {
        this.name = name;
        this.stats = stats;
        combatActions = new ArrayList<CombatAction>();
    }

    public Entity(String name, Stats stats, ArrayList<CombatAction> combatActions) {
        this.name = name;
        this.stats = stats;
        this.combatActions = combatActions;
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

    public void useSpeed(float amount){
        getStats().setCurrentSpeed(getStats().getCurrentSpeed() - amount);
    }

    public void addCombatAction(CombatAction newAction){
        combatActions.add(newAction);
    }

    public void removeCombatAction(CombatAction action){
        removeCombatAction(action.getName());
    }

    public void removeCombatAction(String actionName){
        for(int i = 0; i < combatActions.size(); i++){
            if(combatActions.get(i).getName().equals(actionName)){
                combatActions.remove(i);
                return;
            }
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

    public ArrayList<CombatAction> getCombatActions() {
        return combatActions;
    }

    public void setCombatActions(ArrayList<CombatAction> combatActions) {
        this.combatActions = combatActions;
    }

    @Override
    public String toString() {
        return name + " " +  stats;
    }
}
