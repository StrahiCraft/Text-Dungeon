package utility;

public class Stats {
    private static final float MAX_ARMOR = 100;

    private float maxHealth;
    private float currentHealth;
    private float armor;

    private float damage;

    public Stats() {
        maxHealth = 0;
        currentHealth = 0;
        armor = 0;
        damage = 0;
    }

    public Stats(float maxHealth, float currentHealth, float armor, float damage) {
        this.maxHealth = maxHealth;
        this.currentHealth = currentHealth;
        this.armor = armor;
        this.damage = damage;
    }

    public static float getMaxArmor() {
        return MAX_ARMOR;
    }

    public float getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(float maxHealth) {
        this.maxHealth = maxHealth;
    }

    public float getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(float currentHealth) {
        this.currentHealth = currentHealth;
        if(currentHealth > maxHealth) {
            currentHealth = maxHealth;
        }
    }

    public float getArmor() {
        return armor;
    }

    public void setArmor(float armor) {
        this.armor = armor;
        if(armor > MAX_ARMOR) {
            armor = MAX_ARMOR;
        }
    }

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    @Override
    public String toString() {
        return "Stats: " +
                currentHealth + "/" + maxHealth + "HP " +
                armor + " ARMOR " +
                damage + " DAMAGE";
    }
}
