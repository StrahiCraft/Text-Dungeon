package entity.actions;

import entity.Entity;

public class AttackAction extends CombatAction{
    private float damageMultiplier;

    public AttackAction(){
        damageMultiplier = 1f;
    }

    public AttackAction(float damageMultiplier) {
        this.damageMultiplier = damageMultiplier;
    }

    public AttackAction(String name, float speedCost, float damageMultiplier) {
        super(name, speedCost);
        this.damageMultiplier = damageMultiplier;
    }

    @Override
    public void useAction(Entity actionTarget, Entity actionUser) {
        actionTarget.takeDamage(actionUser.getStats().getDamage());
        actionUser.useSpeed(getSpeedCost());
    }

    public float getDamageMultiplier() {
        return damageMultiplier;
    }

    public void setDamageMultiplier(float damageMultiplier) {
        this.damageMultiplier = damageMultiplier;
    }
}
