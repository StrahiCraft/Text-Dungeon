package entity.inventory.item.equipment;

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

public class EquipItem extends Item {
    private Stats statIncreases;
    private EquipmentSlot equipmentSlot;

    public EquipItem(){
        super();
        statIncreases = new Stats();
        equipmentSlot = EquipmentSlot.HEAD;
    }

    public EquipItem(EquipItem otherItem){
        super(otherItem);
        statIncreases = otherItem.getStatIncreases();
        equipmentSlot = otherItem.getEquipmentSlot();
    }

    public EquipItem(Stats statIncreases, EquipmentSlot equipmentSlot) {
        super();
        this.statIncreases = statIncreases;
        this.equipmentSlot = equipmentSlot;
    }

    public EquipItem(String name, Rarity rarity, int price, Stats statIncreases, EquipmentSlot equipmentSlot) {
        super(name, rarity, price);
        this.statIncreases = statIncreases;
        this.equipmentSlot = equipmentSlot;
    }

    @Override
    public void onUse() {
        Player.Instance.getEquipment().equip(this);
    }

    @Override
    public void handleRarity() {
        
    }

    public void onEquip(){
        TextRenderer.printText("Equipped " + this);
    }

    public void onUnequip(){
        TextRenderer.printText("Unequipped " + this);
    }

    @Override
    public void writeToFile() {
        try {
            File file = new File("assets/items/equipItems/" + getName() + ".txt");
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("name=" + getName() +
                    "\nprice=" + getPrice() +
                    "\nequipmentSlot=" + equipmentSlot + "\n");
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

    @Override
    public void interpretFileData(ArrayList<String> fileData) {
        setName(fileData.get(0));
        setPrice(Integer.parseInt(fileData.get(1)));
        setEquipmentSlot(EquipmentSlot.valueOf(fileData.get(2)));

        statIncreases.interpretFileData(new ArrayList<String>(fileData.subList(3, fileData.size())));
    }

    public Stats getStatIncreases() {
        return statIncreases;
    }

    public void setStatIncreases(Stats statIncreases) {
        this.statIncreases = statIncreases;
    }

    public EquipmentSlot getEquipmentSlot() {
        return equipmentSlot;
    }

    public void setEquipmentSlot(EquipmentSlot equipmentSlot) {
        this.equipmentSlot = equipmentSlot;
    }
}
