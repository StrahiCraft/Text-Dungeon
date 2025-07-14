package entity.inventory.item.equipment;

import entity.inventory.item.Item;
import entity.inventory.item.Rarity;
import entity.player.Player;
import utility.Stats;

public class EquipItem extends Item {
    private Stats statIncreases;
    private EquipmentSlot equipmentSlot;

    public EquipItem(){
        super();
        statIncreases = new Stats();
        equipmentSlot = EquipmentSlot.HEAD;
    }

    public EquipItem(Stats statIncreases, EquipmentSlot equipmentSlot) {
        super();
        this.statIncreases = statIncreases;
        this.equipmentSlot = equipmentSlot;
    }

    public EquipItem(String name, Rarity rarity, Stats statIncreases, EquipmentSlot equipmentSlot) {
        super(name, rarity);
        this.statIncreases = statIncreases;
        this.equipmentSlot = equipmentSlot;
    }

    @Override
    public void onUse() {
        Player.Instance.getInventory().removeItem(this);
        Player.Instance.getEquipment().equip(this);
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
