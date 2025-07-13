package entity.inventory;

import graphics.Color;

public abstract class Item {
    private String name;
    private Rarity rarity;

    public Item() {
        name = "Item";
        rarity = Rarity.COMMON;
    }

    public Item(Item otherItem){
        name = otherItem.getName();
        rarity = otherItem.getRarity();
    }

    public Item(String name, Rarity rarity, int stackLimit) {
        this.name = name;
        this.rarity = rarity;
    }

    public abstract void onUse();

    public void rerollRarity(){
        double roll = Math.random() * 100;

        if(roll > 50){
            rarity = Rarity.COMMON;
            return;
        }
        if(roll > 25){
            rarity = Rarity.UNCOMMON;
            return;
        }
        if(roll > 12.5){
            rarity = Rarity.RARE;
            return;
        }
        if(roll > 6.25){
            rarity = Rarity.EPIC;
            return;
        }
        if(roll > 3.125){
            rarity = Rarity.LEGENDARY;
            return;
        }
        if(roll > 1.6125){
            rarity = Rarity.MITHIC;
            return;
        }
        rarity = Rarity.UNCOMMON;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    @Override
    public String toString() {
        return Color.getRarityColor(rarity) + rarity.toString() + Color.resetColor()
                + " " + name;
    }
}
