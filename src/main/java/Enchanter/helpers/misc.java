package Enchanter.helpers;

import org.powbot.api.Condition;
import org.powbot.api.rt4.Equipment;
import org.powbot.api.rt4.Item;

import static Enchanter.Enchanter.Requirements;

public class misc {
    /**
     * Removes rune from requirement list according to equipped weapon
     * (Need to add special staffs like mud smoke etc')
     * @param rune - Name of the rune used
     */
    public static void removeRune(String rune) {
        Requirements.removeIf(it -> rune.toLowerCase().contains(it.getPower().name().toLowerCase()) || it.getPower().name().contains(rune));
    }

    /**
     * Wields a specific staff
     * @param item - a staff
     */
    public static void wieldStaff(Item item){
        item.interact("Wield");
        Condition.wait(() -> Equipment.itemAt(Equipment.Slot.MAIN_HAND).name().equals(item.name()), 1000, 10);
    }
}
