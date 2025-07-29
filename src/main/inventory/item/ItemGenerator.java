package inventory.item;

import inventory.item.equipment.EquipItem;
import inventory.item.potions.Potion;
import inventory.item.potions.TemporaryPotion;
import inventory.item.special.BundleOfGold;
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
            return new EquipItem((EquipItem)selectedItem);
        }
        if (selectedItem.getClass().equals(Potion.class)) {
            return new Potion((Potion)selectedItem);
        }
        if (selectedItem.getClass().equals(TemporaryPotion.class)) {
            return new TemporaryPotion((TemporaryPotion)selectedItem);
        }
        if (selectedItem.getClass().equals(BundleOfGold.class)) {
            return new BundleOfGold((BundleOfGold)selectedItem);
        }
        return null;
    }

    public static void generateItemsFromFiles(){
        generateItems(new EquipItem(), "assets/items/equipItems");
        generateItems(new Potion(), "assets/items/potions/permanent");
        generateItems(new TemporaryPotion(), "assets/items/potions/temporary");
        generateItems(new BundleOfGold(), "assets/items/special/bundlesOfGold");
    }

    private static void generateItems(Item itemType, String filePath){
        File itemTypeFolder =  new File(filePath);

        for(File currentItemFile : itemTypeFolder.listFiles()) {
            items.add(getItemFromFile(currentItemFile, itemType));
        }
    }

    private static Item getItemFromFile(File itemFile, Item  itemType){
        Item newItem = itemType.copy();
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
