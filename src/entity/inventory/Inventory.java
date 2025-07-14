package entity.inventory;

import entity.inventory.item.Item;
import entity.inventory.item.Rarity;
import graphics.Color;
import graphics.TextRenderer;

import java.util.ArrayList;

public class Inventory {
    private int slotCount;
    private ArrayList<Item> items;

    public Inventory() {
        slotCount = 1;
        items = new ArrayList<>();
    }

    public Inventory(int slotCount){
        this.slotCount = slotCount;
        items = new ArrayList<>();
    }

    public Inventory(int slotCount, ArrayList<Item> items) {
        this.slotCount = slotCount;
        this.items = items;
    }

    /**
     * Adds an item to the inventory.
     * @param newItem
     * The item that will be added.
     * @return
     * True if the item was added successfully and False if it wasn't / the inventory was full.
     */
    public boolean addItem(Item newItem){
        if(items.size() + 1 > slotCount){
            TextRenderer.printText(Color.getColor("red") + "Inventory is full" + Color.resetColor());
            return false;
        }
        items.add(newItem);
        return true;
    }

    public boolean removeItem(int index){
        if(index > items.size() || index < 1){
            TextRenderer.printText(Color.getColor("red") + "There is no item in that slot!" + Color.resetColor());
            return false;
        }

        items.remove(index - 1);
        return true;
    }

    public void removeItem(Item item){
        items.remove(item);
    }

    public boolean removeItem(String itemName){
        return removeItem(itemName, null);
    }

    public boolean removeItem(String itemName, Rarity rarity) {
        Item itemToRemove = getItem(itemName, rarity);

        if(itemToRemove == null){
            TextRenderer.printText(Color.getColor("red") + "There is no such item!" + Color.resetColor());
            return false;
        }

        items.remove(itemToRemove);
        return true;
    }

    public boolean useItem(int index){
        if(index > items.size() || index < 1){
            TextRenderer.printText(Color.getColor("red") + "There is no item in that slot!" + Color.resetColor());
            return false;
        }

        items.get(index - 1).onUse();
        items.remove(index - 1);
        return true;
    }

    public boolean useItem(String itemName){
        return useItem(itemName, null);
    }

    public boolean useItem(String itemName, Rarity rarity) throws NullPointerException {
        Item itemToUse = getItem(itemName, rarity);

        if(itemToUse == null){
            TextRenderer.printText(Color.getColor("red") + "There is no such item!" + Color.resetColor());
            return false;
        }

        itemToUse.onUse();
        return removeItem(itemName, rarity);
    }

    /**
     * Gets the item from the items list by its name and rarity.
     * @param itemName The name of the item.
     * @param rarity The rarity of the item.
     * @return An item from the inventory if it's found, NULL if not.
     */
    private Item getItem(String itemName, Rarity rarity){
        for(Item item : items){
            if(item.getName().equals(itemName) &&
                    (item.getRarity().equals(rarity) || rarity == null)){
                return item;
            }
        }

        return null;
    }

    public int getSlotCount() {
        return slotCount;
    }

    public void setSlotCount(int slotCount) {
        this.slotCount = slotCount;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        StringBuilder inventory = new StringBuilder();
        inventory.append("Inventory: ");

        if(items.size() < slotCount / 2){
            inventory.append(Color.getColor("bright green"));
        }
        else if(items.size() < slotCount){
            inventory.append(Color.getColor("bright yellow"));
        }
        else {
            inventory.append(Color.getColor("red"));
        }

        inventory.append(items.size()).append(Color.resetColor()).append("/").append(slotCount).append('\n');

        for(int i = 0; i < items.size(); i++){
            inventory.append(i + 1).append(". ").append(items.get(i).toString()).append('\n');
        }

        return inventory.toString();
    }
}
