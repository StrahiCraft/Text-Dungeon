package entity.inventory.item.special;

import dungeon.Dungeon;
import entity.inventory.item.Item;
import entity.inventory.item.Rarity;
import entity.inventory.item.equipment.EquipmentSlot;
import entity.player.Player;
import graphics.Color;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class BundleOfGold extends Item {
    private float goldMultiplier;

    public BundleOfGold(){
        super();
        goldMultiplier = 0;
    }

    public BundleOfGold(float goldMultiplier) {
        super();
        this.goldMultiplier = goldMultiplier;
    }

    public BundleOfGold(Item otherItem, float goldMultiplier) {
        super(otherItem);
        this.goldMultiplier = goldMultiplier;
    }

    public BundleOfGold(String name, Rarity rarity, int price, float goldMultiplier) {
        super(name, rarity, price);
        this.goldMultiplier = goldMultiplier;
    }

    @Override
    public void onUse() {
        Player.Instance.addGold((int)(Math.random() * 10 * Dungeon.getCurrentDungeonThreat() * goldMultiplier));
        Player.Instance.getInventory().removeItem(this);
    }

    @Override
    public void handleRarity() {
        switch (getRarity()){
            case COMMON ->    goldMultiplier *= 1;
            case UNCOMMON ->  goldMultiplier *= 1.25f;
            case RARE ->      goldMultiplier *= 1.5f;
            case EPIC ->      goldMultiplier *= 1.75f;
            case LEGENDARY -> goldMultiplier *= 2f;
            case MITHIC ->    goldMultiplier *= 3f;
        }
    }

    @Override
    public void writeToFile() {
        try {
            File file = new File("assets/items/special/" + getName() + ".txt");
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("name=" + getName() +
                    "\nprice=" + getPrice() +
                    "\ngoldMultiplier=" + goldMultiplier);

            fileWriter.close();
        } catch (IOException e) {
            System.out.println(Color.getColor("bright red") + "Error while creating or writing to utility.file: "
                    + Color.resetColor() + "assets/items/specials/" + getName() + ".txt");
            e.printStackTrace();
        }
    }

    @Override
    public void writeToFile(FileWriter fileWriter) {
        writeToFile();
    }

    @Override
    public void interpretFileData(ArrayList<String> fileData) {
        setName(fileData.get(0));
        setPrice(Integer.parseInt(fileData.get(1)));
        goldMultiplier = Float.parseFloat(fileData.get(2));
    }

    public float getGoldMultiplier() {
        return goldMultiplier;
    }

    public void setGoldMultiplier(float goldMultiplier) {
        this.goldMultiplier = goldMultiplier;
    }
}
