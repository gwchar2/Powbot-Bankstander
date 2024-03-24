package Enchanter;

import Enchanter.Data.*;
import org.powbot.api.rt4.walking.model.Skill;
import org.powbot.api.script.paint.Paint;
import org.powbot.api.script.paint.PaintBuilder;

import java.io.ObjectInputFilter;
import java.util.logging.Logger;


public class ConfigHandler extends Enchanter {
    public static Logger LoggerFactory;
    public static final Logger logger = Logger.getLogger("Config Handler: ");

    /**
     * This method extracts the information received from the GUI.
     * @param method - requested spell to cast
     * @param item - requested item to enchant
     * @param level - level to stop at
     * @param spell - spell to be used
     * @param bank - requested bank
     */
    public static void extractConfig(String method, String item, int level, String spell, String bank) {
        if (method.isEmpty() || method.equals("BOLT"))
            className = "CrossbowBolt";
        if (method.equals("LEVEL_1"))
            className = "Level_1";
        if (method.equals("LEVEL_2"))
            className = "Level_2";
        if (method.equals("LEVEL_3"))
            className = "Level_3";
        if (method.equals("LEVEL_4"))
            className = "Level_4";
        if (method.equals("LEVEL_5"))
            className = "Level_5";
        if (method.equals("LEVEL_6"))
            className = "Level_6";
        if (method.equals("LEVEL_7"))
            className = "Level_7";
        if (item.isEmpty()) enumConstantName = "DEFAULT_VALUE";
        else enumConstantName = item;
        if (level == 0) maxLevel = 100;
        else maxLevel = level;
        if (spell.isEmpty()) mySpell = EnchantType.BOLT.getSpell();
        else mySpell = EnchantType.valueOf(spell).getSpell();
        bankArea = BankAreas.valueOf(bank).getBankArea();
        bankName = BankAreas.valueOf(bank).name();
        try {
            Class<?> enumClass = Class.forName("Enchanter.Data." + className);
            if (enumClass.isEnum()) {
                Enum<?> enumConstant = Enum.valueOf((Class<Enum>) enumClass, enumConstantName);
                if (enumConstant instanceof Enchantable) {
                    enchantableEnum = (Enchantable) enumConstant;
                }
            } else { //For testing purposes
                logger.info("The specified class is not an enum.");
            }
        } catch (ClassNotFoundException e) { //For testing purposes
            logger.info("Enum class not found: " + className);
        } catch (IllegalArgumentException e) { //For testing purposes
            logger.info("Enum constant not found: " + enumConstantName);
        }
    }

    /**
     * Changes the Method listed according to user input.
     *
     * @param newMethod - The chosen input
     * @return newString - The new string list of options.
     */
    public static String[] methodUpdated(String newMethod) {
        if (newMethod.equals("BOLT")) {
            String[] CrossbowBoltList = {"ONYX_DRAGON", "ONYX", "DRAGONSTONE_DRAGON", "DRAGONSTONE",
                    "DIAMOND_DRAGON", "DIAMOND", "RUBY_DRAGON", "RUBY",
                    "TOPAZ_DRAGON", "TOPAZ", "EMERALD_DRAGON", "EMERALD", "PEARL_DRAGON", "PEARL", "JADE_DRAGON",
                    "JADE", "SAPPHIRE_DRAGON", "SAPPHIRE", "OPAL_DRAGON", "OPAL"};
            return CrossbowBoltList;
        }
        if (newMethod.equals("LEVEL_1")) {
            String[] Level_1List = {
                    "SAPPHIRE_RING", "SAPPHIRE_BRACELET", "SAPPHIRE_NECKLACE", "SAPPHIRE_AMULET",
                    "OPAL_RING", "OPAL_BRACELET", "OPAL_NECKLACE", "OPAL_AMULET"};
            return Level_1List;
        }
        if (newMethod.equals("LEVEL_2")) {
            String[] Level_2List = {
                    "EMERALD_RING", "EMERALD_BRACELET", "EMERALD_NECKLACE", "EMERALD_AMULET",
                    "JADE_RING", "JADE_BRACELET", "JADE_NECKLACE", "JADE_AMULET"};
            return Level_2List;
        }
        if (newMethod.equals("LEVEL_3")) {
            String[] Level_3List = {
                    "RUBY_RING", "RUBY_BRACELET", "RUBY_NECKLACE", "RUBY_AMULET",
                    "TOPAZ_RING", "TOPAZ_BRACELET", "TOPAZ_NECKLACE", "TOPAZ_AMULET"};
            return Level_3List;
        }
        if (newMethod.equals("LEVEL_4")) {
            String[] Level_4List = {
                    "DIAMOND_RING", "DIAMOND_BRACELET", "DIAMOND_NECKLACE", "DIAMOND_AMULET"};
            return Level_4List;
        }
        if (newMethod.equals("LEVEL_5")) {
            String[] Level_5List = {
                    "DRAGONSTONE_RING", "DRAGONSTONE_BRACELET", "DRAGONSTONE_NECKLACE", "DRAGONSTONE_AMULET"};
            return Level_5List;
        }
        if (newMethod.equals("LEVEL_6")) {
            String[] Level_6List = {
                    "ONYX_RING", "ONYX_BRACELET", "ONYX_NECKLACE", "ONYX_AMULET"};
            return Level_6List;
        }
        if (newMethod.equals("LEVEL_7")) {
            String[] Level_7List = {
                    "ZENYTE_RING", "ZENYTE_BRACELET", "ZENYTE_NECKLACE", "ZENYTE_AMULET"};
            return Level_7List;
        }
        return new String[0];
    }


}


