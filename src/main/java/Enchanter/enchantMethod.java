package Enchanter;

import org.powbot.api.Condition;
import org.powbot.api.Random;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Movement;

import static Enchanter.Enchanter.*;
import static Enchanter.helpers.bankHelper.*;

public class enchantMethod {
    public static int[] mousepath1 = {10, 25, 4, 14, 6, 19, 3, 8, 26, 7, 16, 2, 15, 22, 11, 18, 5, 21, 12, 27, 23, 1, 20, 17, 13, 24, 9};
    public static int[] mousepath2 = {23, 6, 11, 5, 26, 12, 1, 7, 19, 17, 4, 24, 15, 20, 3, 22, 14, 10, 13, 27, 21, 2, 25, 9, 18, 8, 16};
    public static int[] mousepath3 = {13, 25, 3, 7, 6, 8, 21, 19, 14, 15, 11, 2, 1, 23, 26, 10, 5, 18, 22, 4, 12, 20, 9, 16, 27, 17, 24};
    public static int[] mousepath4 = {19, 25, 9, 21, 27, 10, 24, 12, 2, 7, 23, 18, 14, 8, 5, 20, 13, 6, 22, 16, 26, 11, 4, 1, 3, 15, 17};
    public static int[] mousepath5 = {7, 10, 12, 16, 1, 21, 24, 6, 18, 27, 26, 5, 22, 14, 20, 3, 8, 17, 15, 2, 25, 9, 23, 13, 19, 11, 4};

    public static void enchant() {
        userLog = "Starting to enchant";
        logger.info(userLog);
        int[] mousepath = new int[0];
        java.util.Random random = new java.util.Random();
        int i = 0;
        if (mySpell.canCast() && Inventory.stream().id(enchantableEnum.getUnenchantedID()).first().valid()) {
            int randomNum = Random.nextInt(1, 5);
            switch (randomNum) {
                case 1:
                    mousepath = mousepath1;
                    break;
                case 2:
                    mousepath = mousepath2;
                    break;
                case 3:
                    mousepath = mousepath3;
                    break;
                case 4:
                    mousepath = mousepath4;
                    break;
                case 5:
                    mousepath = mousepath5;
                    break;
            }
            logger.info("Chosen mousepath: " + randomNum);
            if (!searchedStaff || !withdrawnRunes) return;
            if (!player.tile().equals(myBankTile)) {
                if (bankArea.contains(player.tile())) {
                    userLog = "Need to walk to the bank booth";
                    logger.info(userLog);
                    Movement.step(myBankTile);
                    if (Condition.wait(() -> player.inMotion(), 50, 15)) {
                        Condition.wait(() -> !player.inMotion(), 150, 25);
                    }
                } else {
                    searchedStaff = false;
                    withdrawnRunes = false;
                    return;
                }
            }
            if (Bank.opened()) Bank.close();
            for (i = 0; i < mousepath.length; i++) {
                if (Inventory.itemAt(mousepath[i]).id() == enchantableEnum.getUnenchantedID()) {
                    int[] finalMousepath = mousepath;
                    int finalI = i;
                    userLog = "Enchanting inventory spot: " + mousepath[i];
                    logger.info(userLog);
                    mySpell.cast();
                    Condition.wait(() -> mySpell.cast(),25,10);
                    Inventory.itemAt(finalMousepath[finalI]).interact("Cast");
                    Condition.wait(() -> Inventory.itemAt(finalMousepath[finalI]).id() != enchantableEnum.getUnenchantedID(), 110, 33);
                }
                i++;
            }
            if (!Inventory.stream().id(enchantableEnum.getUnenchantedID()).first().valid()) withdrawItem();
        }
        else if (!mySpell.canCast()) {
            withdrawnRunes = false;
            return;
        }
    }
}

