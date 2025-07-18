package entity.player.states;

import combat.CombatManager;
import dungeon.rooms.EnemyRoom;
import entity.player.Player;
import graphics.Color;
import graphics.TextRenderer;

import java.util.Scanner;

public class PlayerInCombat extends PlayerState {
    public PlayerInCombat() {
        super();
        possibleCommands.add(Color.getColor("green") + "use" + Color.resetColor());
        possibleCommands.add(Color.getColor("red") + "attack" + Color.resetColor());

        CombatManager.resetCombat();
        CombatManager.setEnemies(((EnemyRoom)Player.Instance.getCurrentRoom()).getEnemies());

        combatText();
    }

    @Override
    public boolean checkInput(String inputText, Scanner input) {
        if(!CombatManager.playerTurn()) {
            CombatManager.takeEnemyAttack();
            combatText();
            return true;
        }

        if(inputText.equals("check")) {
            TextRenderer.printText(CombatManager.asString());
            TextRenderer.printText(Color.getColor("blue") + "Player:\n" + Color.resetColor() +
                    Player.Instance.getStats() + "\n" +
                    Player.Instance.getInventory() + "\n" +
                    Player.Instance.getEquipment());
            combatText();
            return true;
        }

        String[] splitText = inputText.split(" ");
        switch (splitText[0]) {
            case "attack" -> {
                attack(splitText);
                combatText();
                return true;
            }
            case "use" -> {
                useItem(splitText);
                combatText();
                return true;
            }
        }

        return false;
    }

    private void attack(String[] instructions) {
        if(instructions.length != 2){
            TextRenderer.printText("To attack type in "
                    + Color.getColor("red") + "attack " + Color.resetColor() +
                    "followed by the index of the enemy (number next to the enemy without the '.')");
            return;
        }

        CombatManager.attackEnemy(Integer.parseInt(instructions[1]));
    }

    private void useItem(String[] instructions) {
        if(instructions.length != 2){
            TextRenderer.printText("To use an item type in "
                    + Color.getColor("green") + "use " + Color.resetColor() +
                    "followed by the item index (number next to the item without the '.')");
            return;
        }

        CombatManager.useItem(Integer.parseInt(instructions[1]));
    }

    private void combatText() {
        if(!CombatManager.playerTurn()){
            setInputQuestion("It is not your turn, press " + Color.getColor("red") + "enter " + Color.resetColor()
            + "to wait.");
            return;
        }

        setInputQuestion("It is your turn, what will you do?");
    }
}
