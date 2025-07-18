package entity.inventory.item.equipment;

import entity.player.Player;
import graphics.Color;
import graphics.TextRenderer;

import java.util.Dictionary;
import java.util.Hashtable;

public class Equipment {
    private Dictionary<EquipmentSlot, EquipItem> equippedItems;

    public Equipment() {
        equippedItems = new Hashtable<>();

        equippedItems.get(EquipmentSlot.HEAD);
        equippedItems.get(EquipmentSlot.BODY);
        equippedItems.get(EquipmentSlot.HANDS);
        equippedItems.get(EquipmentSlot.LEGS);
        equippedItems.get(EquipmentSlot.FEET);
        equippedItems.get(EquipmentSlot.WEAPON);
    }

    public void equip(EquipItem equipItem){
        if(Player.Instance.getStats().getMaxSpeed() + equipItem.getStatIncreases().getMaxSpeed() <= 0) {
            TextRenderer.printText(Color.getColor("red") + "You will be to heavy to fight with that equipped!"
                    + Color.resetColor() + "\nGet faster if you wish to use it.");
            return;
        }

        Player.Instance.getInventory().removeItem(equipItem);

        if(equippedItems.get(equipItem.getEquipmentSlot()) != null) {
            EquipItem unequippedItem = equippedItems.get(equipItem.getEquipmentSlot());
            unequippedItem.onUnequip();
            Player.Instance.getInventory().addItem(unequippedItem);
        }

        equippedItems.put(equipItem.getEquipmentSlot(), equipItem);
        equipItem.onEquip();
    }

    public void unequip(EquipmentSlot equipmentSlot) {
        if(Player.Instance.getInventory().isFull()) {
            TextRenderer.printText("Cannot" +
                    Color.getColor("gray") + " unequip," + Color.resetColor() +
                    Color.getColor("red") + " inventory is full." + Color.resetColor());
            return;
        }

        if(equippedItems.get(equipmentSlot) == null) {
            TextRenderer.printText("Equipment slot " + equipmentSlot + " is empty.");
            return;
        }

        EquipItem unequippedItem = equippedItems.get(equipmentSlot);
        unequippedItem.onUnequip();
        Player.Instance.getInventory().addItem(unequippedItem);

        equippedItems.remove(equipmentSlot);
        equippedItems.get(equipmentSlot);
    }

    public Equipment(Dictionary<EquipmentSlot, EquipItem> equippedItems) {
        this.equippedItems = equippedItems;
    }

    public EquipItem getEquippedItem(EquipmentSlot equipmentSlot){
        return equippedItems.get(equipmentSlot);
    }

    public Dictionary<EquipmentSlot, EquipItem> getEquippedItems() {
        return equippedItems;
    }

    public void setEquippedItems(Dictionary<EquipmentSlot, EquipItem> equippedItems) {
        this.equippedItems = equippedItems;
    }

    @Override
    public String toString() {
        String equipment = "Equipment:\n" +
                equipmentFromSlot(EquipmentSlot.HEAD) +
                equipmentFromSlot(EquipmentSlot.BODY) +
                equipmentFromSlot(EquipmentSlot.HANDS) +
                equipmentFromSlot(EquipmentSlot.LEGS) +
                equipmentFromSlot(EquipmentSlot.FEET) +
                equipmentFromSlot(EquipmentSlot.WEAPON);

        return equipment;
    }

    private String equipmentFromSlot(EquipmentSlot equipmentSlot){
        StringBuilder equipment = new StringBuilder();

        equipment.append(equipmentSlot).append(": ");
        if(equippedItems.get(equipmentSlot) == null) {
            equipment.append(Color.getColor("red")).append("empty").append(Color.resetColor()).append('\n');
        }
        else {
            equipment.append(equippedItems.get(equipmentSlot)).append('\n');
        }

        return equipment.toString();
    }
}
