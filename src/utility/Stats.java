package utility;

import graphics.Color;
import utility.file.FileInterpreter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Stats implements utility.file.FileWriter, FileInterpreter {
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

    @Override
    public void writeToFile() {
        System.out.println(Color.getColor("bright red") +
                "Error, don't write stats to utility.file without a file writer name!" + Color.resetColor());
    }

    @Override
    public void writeToFile(FileWriter fileWriter) {
        try {
            fileWriter.append(maxHealth + "\n");
            fileWriter.append(currentHealth + "\n");
            fileWriter.append(armor + "\n");
            fileWriter.append(damage + "\n");

            fileWriter.close();
        } catch (IOException e) {
            System.out.println(Color.getColor("bright red") +
                    "Error while writing stats to file!" + Color.resetColor());
            e.printStackTrace();
        }
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
                currentHealth + "/" + maxHealth + Color.getColor("red") + "HP " + Color.resetColor() +
                armor + Color.getColor("yellow") + " ARMOR " + Color.resetColor() +
                damage + Color.getColor("magenta") + " DAMAGE" + Color.resetColor();
    }

    @Override
    public void interpretFileData(ArrayList<String> fileData) {
        maxHealth = Float.parseFloat(fileData.get(0));
        currentHealth = Float.parseFloat(fileData.get(1));
        armor = Float.parseFloat(fileData.get(2));
        damage = Float.parseFloat(fileData.get(3));
    }
}
