package entity.inventory.item.potions;

import combat.CombatManager;
import entity.inventory.item.Item;
import entity.inventory.item.Rarity;
import entity.player.Player;
import graphics.Color;
import graphics.TextRenderer;
import utility.Stats;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TemporaryPotion extends Potion {
    public TemporaryPotion() {
        super();
    }

    public TemporaryPotion(Stats statIncreases) {
        super(statIncreases);
    }

    public TemporaryPotion(TemporaryPotion otherItem) {
        super(otherItem);
    }

    public TemporaryPotion(String name, Rarity rarity, int price, Stats statIncreases) {
        super(name, rarity, price, statIncreases);
    }

    @Override
    public void onUse() {
        if(!CombatManager.isInCombat()){
            TextRenderer.printText("Temporary potions can only be used while in combat.");
            return;
        }
        Player.Instance.increaseStats(getStatIncreases());
        // TODO remove stat increase after one combat turn
    }

    @Override
    public void writeToFile() {
        try {
            File file = new File("assets/items/potions/temporary/" + getName() + ".txt");
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("name=" + getName() +
                    "\nprice=" + getPrice() + "\n");
            getStatIncreases().writeToFile(fileWriter);

            fileWriter.close();
        } catch (IOException e) {
            System.out.println(Color.getColor("bright red") + "Error while creating or writing to utility.file: "
                    + Color.resetColor() + "assets/items/equipItems/" + getName() + ".txt");
            e.printStackTrace();
        }
    }

    @Override
    public void writeToFile(FileWriter fileWriter) {
        writeToFile();
    }
}
