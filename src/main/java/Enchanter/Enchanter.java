package Enchanter;

import Enchanter.Data.Enchantable;
import org.powbot.api.requirement.Requirement;
import org.powbot.api.rt4.Equipment;
import org.powbot.api.rt4.Item;
import org.powbot.api.rt4.Magic;
import org.powbot.api.rt4.Magic.Spell;
import org.powbot.api.rt4.walking.model.Skill;
import org.powbot.api.script.AbstractScript;
import org.powbot.api.script.OptionType;
import org.powbot.api.script.ScriptCategory;
import org.powbot.api.script.ScriptConfiguration;
import org.powbot.api.script.ScriptManifest;
import org.powbot.api.script.ValueChanged;
import java.lang.reflect.Field;
import Enchanter.Data.EnchantType;
import java.util.List;
import org.powbot.mobile.script.ScriptManager;
import java.util.logging.Logger;

@ScriptManifest(name = "Open Enchanter",
        description = "Enchants bolts and items",
        version = "1.0.1",
        category = ScriptCategory.Magic,
        author = "Great Mental",
        markdownFileName = "README.md")

@ScriptConfiguration.List({
        @ScriptConfiguration(name = "Method", description = "Type of enchant to cast", allowedValues = { "BOLT",
                "LEVEL_1", "LEVEL_2", "LEVEL_3", "LEVEL_4", "LEVEL_5", "LEVEL_6", "LEVEL_7" }, visible = true),
        @ScriptConfiguration(name = "Item to Enchant", description = "What item do you want to enchant", allowedValues = {
                "ONYX_DRAGON", "ONYX", "DRAGONSTONE_DRAGON", "DRAGONSTONE",
                "DIAMOND_DRAGON", "DIAMOND", "RUBY_DRAGON", "RUBY",
                "TOPAZ_DRAGON", "TOPAZ", "EMERALD_DRAGON", "EMERALD", "PEARL_DRAGON", "PEARL", "JADE_DRAGON",
                "JADE", "SAPPHIRE_DRAGON", "SAPPHIRE", "OPAL_DRAGON", "OPAL" }, visible = true),
        @ScriptConfiguration(name = "Level to stop", description = "Stops when reaches this level",
                optionType = OptionType.INTEGER, visible = true, defaultValue = "99"),
        @ScriptConfiguration(name = "Where to enchant", description = "Bankstands here",
                allowedValues = {"Grand Exchange", "Varrock West", "Varrock East", "Edgeville", "Camelot"},visible = true)
})


public class Enchanter extends AbstractScript {
    protected String className; // User input for class name
    protected String enumConstantName; // User input for enum constant name
    protected Spell mySpell;
    protected int unenchantedItemID;
    protected int enchantedItemID;
    protected int levelReq;
    protected int maxLevel;
    @Override
    public void onStart() {
        extractConfig(); //extracts the config
        checkRequirments();
        //check if in bank area (one of the config banks)
        //if not, teleport / make new path to go there
        //if there, make sure at correct tile.
        //if at correct tile, compare weapons / check if items in inventory (save as boolean)
        // open the bank tab
        // search for correct weapon (if needed) and search for extra runes
        //if no extra runes, set runes amount to the amount in inventory
        //if extra runes, take them all out, and set the amount to total amount in inventory
        //return can start enchanting
    }

    @Override
    public void poll() {
        // Looped tasks go here. Example below is of a simple fighter script

    }

    public void checkRequirments(){
        if (Skill.Magic.realLevel() > levelReq ||  Magic.Book.MODERN.name().compareTo(Magic.book().name()) > 0 || Skill.Magic.realLevel() > maxLevel) {
            ScriptManager.INSTANCE.stop();
            return;
        }
        List<Requirement> requiredItems = mySpell.requirements();
        Item equippedWeapon = Equipment.itemAt(Equipment.Slot.MAIN_HAND);




    }
    /**
     * This method extracts the configuration from the GUI which is presented via the class annotations.
     */
    public void extractConfig(){
        className = getOption("Method");
        enumConstantName = getOption("Item To Enchant");
        maxLevel = getOption("Level to stop");
        mySpell = EnchantType.valueOf(getOption("Method")).getSpell();
        try {
            Class<?> clazz = Class.forName("Enchanter.Data." + className);
            Field field = clazz.getField(enumConstantName);
            Enum<?> selectedEnum = (Enum<?>) field.get(null);
            unenchantedItemID = ((Enchantable) selectedEnum).getUnenchantedID();
            enchantedItemID = ((Enchantable) selectedEnum).getEnchantedID();
            levelReq = ((Enchantable) selectedEnum).getLevelReq();
        } catch (ClassNotFoundException | IllegalArgumentException | IllegalAccessException | NoSuchFieldException
                 | SecurityException ignored) {
        }
    }

    /**
     * This method changes the GUI according to selection by user.
     * @param newMethod Fixed string.
     */
    @ValueChanged(keyName = "Method")
    public void methodUpdated(String newMethod) {
        if (newMethod.equals("BOLT")) {
            String[] CrossbowBoltList = { "ONYX_DRAGON", "ONYX", "DRAGONSTONE_DRAGON", "DRAGONSTONE",
                    "DIAMOND_DRAGON", "DIAMOND", "RUBY_DRAGON", "RUBY",
                    "TOPAZ_DRAGON", "TOPAZ", "EMERALD_DRAGON", "EMERALD", "PEARL_DRAGON", "PEARL", "JADE_DRAGON",
                    "JADE", "SAPPHIRE_DRAGON", "SAPPHIRE", "OPAL_DRAGON", "OPAL" };
            updateAllowedOptions("Item to Enchant", CrossbowBoltList);
        }
        if (newMethod.equals("LEVEL_1")) {
            String[] Level_1List = {
                    "SAPPHIRE_RING", "SAPPHIRE_BRACELET", "SAPPHIRE_NECKLACE", "SAPPHIRE_AMULET",
                    "OPAL_RING", "OPAL_BRACELET", "OPAL_NECKLACE", "OPAL_AMULET" };
            updateAllowedOptions("Item to Enchant", Level_1List);
        }
        if (newMethod.equals("LEVEL_2")) {
            String[] Level_2List = {
                    "EMERALD_RING", "EMERALD_BRACELET", "EMERALD_NECKLACE", "EMERALD_AMULET",
                    "JADE_RING", "JADE_BRACELET", "JADE_NECKLACE", "JADE_AMULET" };
            updateAllowedOptions("Item to Enchant", Level_2List);
        }
        if (newMethod.equals("LEVEL_3")) {
            String[] Level_3List = {
                    "RUBY_RING", "RUBY_BRACELET", "RUBY_NECKLACE", "RUBY_AMULET",
                    "TOPAZ_RING", "TOPAZ_BRACELET", "TOPAZ_NECKLACE", "TOPAZ_AMULET" };
            updateAllowedOptions("Item to Enchant", Level_3List);
        }
        if (newMethod.equals("LEVEL_4")) {
            String[] Level_4List = {
                    "DIAMOND_RING", "DIAMOND_BRACELET", "DIAMOND_NECKLACE", "DIAMOND_AMULET" };
            updateAllowedOptions("Item to Enchant", Level_4List);
        }
        if (newMethod.equals("LEVEL_5")) {
            String[] Level_5List = {
                    "DRAGONSTONE_RING", "DRAGONSTONE_BRACELET", "DRAGONSTONE_NECKLACE", "DRAGONSTONE_AMULET" };
            updateAllowedOptions("Item to Enchant", Level_5List);
        }
        if (newMethod.equals("LEVEL_6")) {
            String[] Level_6List = {
                    "ONYX_RING", "ONYX_BRACELET", "ONYX_NECKLACE", "ONYX_AMULET" };
            updateAllowedOptions("Item to Enchant", Level_6List);
        }
        if (newMethod.equals("LEVEL_7")) {
            String[] Level_7List = {
                    "ZENYTE_RING", "ZENYTE_BRACELET", "ZENYTE_NECKLACE", "ZENYTE_AMULET" };
            updateAllowedOptions("Item to Enchant", Level_7List);
        }
    }

    public static void main(String[] args) {
        new Enchanter().startScript();
    }
}
