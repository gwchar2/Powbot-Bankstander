package Enchanter;

import Enchanter.Data.Enchantable;
import org.powbot.api.rt4.Magic;
import org.powbot.dax.shared.helpers.magic.Spell;

import java.lang.reflect.Field;

/**
 * This method changes the GUI according to selection by user.
 */

public class ConfigHandler extends Enchanter{

    public static String[] handleMethodUpdate(String newMethod) {
        if (newMethod.equals("BOLT")) {
            return new String[]{"ONYX_DRAGON", "ONYX", "DRAGONSTONE_DRAGON", "DRAGONSTONE",
                    "DIAMOND_DRAGON", "DIAMOND", "RUBY_DRAGON", "RUBY",
                    "TOPAZ_DRAGON", "TOPAZ", "EMERALD_DRAGON", "EMERALD", "PEARL_DRAGON", "PEARL", "JADE_DRAGON",
                    "JADE", "SAPPHIRE_DRAGON", "SAPPHIRE", "OPAL_DRAGON", "OPAL"};
        }
        if (newMethod.equals("LEVEL_1")) {
            return new String[]{
                    "SAPPHIRE_RING", "SAPPHIRE_BRACELET", "SAPPHIRE_NECKLACE", "SAPPHIRE_AMULET",
                    "OPAL_RING", "OPAL_BRACELET", "OPAL_NECKLACE", "OPAL_AMULET"};
        }
        if (newMethod.equals("LEVEL_2")) {
            return new String[]{
                    "EMERALD_RING", "EMERALD_BRACELET", "EMERALD_NECKLACE", "EMERALD_AMULET",
                    "JADE_RING", "JADE_BRACELET", "JADE_NECKLACE", "JADE_AMULET"};
        }
        if (newMethod.equals("LEVEL_3")) {
            return new String[]{
                    "RUBY_RING", "RUBY_BRACELET", "RUBY_NECKLACE", "RUBY_AMULET",
                    "TOPAZ_RING", "TOPAZ_BRACELET", "TOPAZ_NECKLACE", "TOPAZ_AMULET"};
        }
        if (newMethod.equals("LEVEL_4")) {
            return new String[]{
                    "DIAMOND_RING", "DIAMOND_BRACELET", "DIAMOND_NECKLACE", "DIAMOND_AMULET"};
        }
        if (newMethod.equals("LEVEL_5")) {
            return new String[]{
                    "DRAGONSTONE_RING", "DRAGONSTONE_BRACELET", "DRAGONSTONE_NECKLACE", "DRAGONSTONE_AMULET"};
        }
        if (newMethod.equals("LEVEL_6")) {
            return new String[]{
                    "ONYX_RING", "ONYX_BRACELET", "ONYX_NECKLACE", "ONYX_AMULET"};
        }
        if (newMethod.equals("LEVEL_7")) {
            return new String[]{
                    "ZENYTE_RING", "ZENYTE_BRACELET", "ZENYTE_NECKLACE", "ZENYTE_AMULET"};
        }
        return new String[0];
    }
}