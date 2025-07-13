package entity.inventory;

import java.util.ArrayList;

public class Inventory {
    private int slotCount;
    private ArrayList<Item> items;

    public Inventory() {
        slotCount = 1;
        items = new ArrayList<>();
    }

    public Inventory(int slotCount, ArrayList<Item> items) {
        this.slotCount = slotCount;
        this.items = items;
    }

    public void addItem(Item newItem){
        items.add(newItem);
    }

    public void removeItem(int index){
        items.remove(index);
    }

    public void removeItem(String itemName){
        removeItem(itemName, null);
    }

    public void removeItem(String itemName, Rarity rarity) {
        items.remove(getItem(itemName, rarity));
    }

    public void useItem(String itemName){
        useItem(itemName, null);
    }

    public void useItem(String itemName, Rarity rarity) throws NullPointerException {
        getItem(itemName, rarity).onUse();
        removeItem(itemName, rarity);
    }

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
}
