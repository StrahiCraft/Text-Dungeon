package entity.inventory.item.potions;

import entity.inventory.item.Item;
import entity.inventory.item.Rarity;
import entity.player.Player;
import graphics.Color;
import graphics.TextRenderer;
import utility.Stats;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Potion extends Item {
    private Stats statIncreases;

    public Potion(){
        super();
        statIncreases = new Stats();
        handleRarity();
    }

    public Potion(Stats statIncreases) {
        super();
        this.statIncreases = statIncreases;
        handleRarity();
    }

    public Potion(Potion otherItem) {
        super(otherItem);
        statIncreases = new Stats(otherItem.getStatIncreases());
        handleRarity();
    }

    public Potion(String name, Rarity rarity, int price, Stats statIncreases) {
        super(name, rarity, price);
        this.statIncreases = statIncreases;
        handleRarity();
    }

    @Override
    public void onUse() {
        printOnUseText();
        Player.Instance.increaseStats(statIncreases);
        Player.Instance.getInventory().removeItem(this);
    }

    @Override
    public void handleRarity() {
        switch (getRarity()){
            case COMMON -> statIncreases.multiplyStats(1f);
            case UNCOMMON -> {
                statIncreases.multiplyStats(1.1f);
                setPrice((int)(getPrice() * 1.5f));
            }
            case RARE -> {
                statIncreases.multiplyStats(1.25f);
                setPrice((int)(getPrice() * 2f));
            }
            case EPIC -> {
                statIncreases.multiplyStats(1.5f);
                setPrice((int)(getPrice() * 3f));
            }
            case LEGENDARY -> {
                statIncreases.multiplyStats(2f);
                setPrice((int)(getPrice() * 5f));
            }
            case MITHIC -> {
                statIncreases.multiplyStats(3f);
                setPrice((int)(getPrice() * 10f));
            }
        }
    }

    @Override
    public String info() {
        return "Increases stats permanently: " + statIncreases;
    }

    @Override
    public void interpretFileData(ArrayList<String> fileData) {
        setName(fileData.get(0));
        setPrice(Integer.parseInt(fileData.get(1)));

        statIncreases.interpretFileData(new ArrayList<String>(fileData.subList(2, fileData.size())));
    }

    @Override
    public void writeToFile() {
        try {
            File file = new File("assets/items/potions/permanent/" + getName() + ".txt");
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("name=" + getName() +
                    "\nprice=" + getPrice() + "\n");
            statIncreases.writeToFile(fileWriter);

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

    public Stats getStatIncreases() {
        return statIncreases;
    }

    public void setStatIncreases(Stats statIncreases) {
        this.statIncreases = statIncreases;
    }

    private void printOnUseText() {
        String statUpdates = getStatUpdate(Color.getColor("bright red") + "Max health"
                + Color.resetColor(), statIncreases.getMaxHealth()) +
                getStatUpdate(Color.getColor("red") + "Health"
                        + Color.resetColor(), statIncreases.getCurrentHealth()) +
                getStatUpdate(Color.getColor("yellow") + "Armor"
                        + Color.resetColor(), statIncreases.getArmor()) +
                getStatUpdate(Color.getColor("magenta") + "Damage"
                        + Color.resetColor(), statIncreases.getDamage()) +
                getStatUpdate("Max speed", statIncreases.getMaxSpeed()) +
                getStatUpdate("Speed", statIncreases.getCurrentSpeed());

        System.out.println(statUpdates);
    }

    private String getStatUpdate(String statName, float amount){
        StringBuilder statUpdate = new StringBuilder();

        if(amount != 0){
            statUpdate.append(statName);

            if(amount > 0){
                statUpdate.append(" increased by ");
            }
            else {
                statUpdate.append(" decreased by ");
            }

            statUpdate.append(Math.abs(amount));
        }

        return statUpdate.toString();
    }
}
