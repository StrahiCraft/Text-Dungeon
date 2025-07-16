package entity.inventory.item;

import entity.inventory.item.equipment.EquipItem;
import entity.inventory.item.potions.Potion;
import entity.inventory.item.potions.TemporaryPotion;
import utility.file.FileReader;

import java.io.File;
import java.util.ArrayList;

/**
 * Generates item instances to copy from files located in assets/items/"itemType".
 */
public class ItemGenerator {
    private static ArrayList<Item> items = new ArrayList<>();

    public static Item generateItemInstance(){
        Item selectedItem = items.get((int)(Math.random() * items.size()));

        if (selectedItem.getClass().equals(EquipItem.class)) {
            return new EquipItem((EquipItem) selectedItem);
        }
        if (selectedItem.getClass().equals(Potion.class)) {
            return new Potion((Potion)selectedItem);
        }
        if (selectedItem.getClass().equals(TemporaryPotion.class)) {
            return new TemporaryPotion((TemporaryPotion) selectedItem);
        }
        return null;
    }

    public static void generateItemsFromFiles(){
        generateEquipItems();
        generatePotions();
        generateTemporaryPotions();
    }

    private static void generateEquipItems(){
        File itemTypeFolder =  new File("assets/items/equipItems");

        for(File currentItemFile : itemTypeFolder.listFiles()) {
            items.add(getEquipItemFromFile(currentItemFile));
        }
    }

    private static EquipItem getEquipItemFromFile(File itemFile){
        EquipItem newItem = new EquipItem();
        newItem.interpretFileData(FileReader.readFile(itemFile));

        return newItem;
    }

    private static void generatePotions(){
        File itemTypeFolder =  new File("assets/items/potions/permanent");

        for(File currentItemFile : itemTypeFolder.listFiles()) {
            items.add(getPotionFromFile(currentItemFile));
        }
    }

    private static Potion getPotionFromFile(File itemFile){
        Potion newItem = new Potion();
        newItem.interpretFileData(FileReader.readFile(itemFile));

        return newItem;
    }

    private static void generateTemporaryPotions(){
        File itemTypeFolder =  new File("assets/items/potions/temporary");

        for(File currentItemFile : itemTypeFolder.listFiles()) {
            items.add(getTemporaryPotionFromFile(currentItemFile));
        }
    }

    private static Potion getTemporaryPotionFromFile(File itemFile){
        TemporaryPotion newItem = new TemporaryPotion();
        newItem.interpretFileData(FileReader.readFile(itemFile));

        return newItem;
    }

    public static ArrayList<Item> getItems() {
        return items;
    }

    public static void setItems(ArrayList<Item> items) {
        ItemGenerator.items = items;
    }
}
