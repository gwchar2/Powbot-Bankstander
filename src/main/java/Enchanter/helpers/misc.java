package Enchanter.helpers;

import org.powbot.api.Condition;
import org.powbot.api.rt4.Item;

import static Enchanter.Enchanter.Requirements;

public class misc {
    /**
     * Removes rune from requirement list according to equipped weapon
     * (Need to add special staffs like mud smoke etc)
     */
    public static void removeRune(String rune) {
        Requirements.removeIf(it -> it.getPower().name().contains(rune));
    }

    /**
     * Wields a specific staff
     * @param item - a staff
     */
    public static void wieldStaff(Item item){
        item.interact("Wield");
        Condition.wait(() -> !item.valid(), 300, 10);
    }
}
