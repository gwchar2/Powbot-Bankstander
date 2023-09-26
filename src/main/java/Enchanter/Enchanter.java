package Enchanter;

import org.powbot.api.rt4.Magic.Spell;
import org.powbot.api.script.AbstractScript;
import org.powbot.api.script.OptionType;
import org.powbot.api.script.ScriptCategory;
import org.powbot.api.script.ScriptConfiguration;
import org.powbot.api.script.ScriptManifest;
import org.powbot.api.script.ValueChanged;
import java.lang.reflect.Field;
import Enchanter.Data.EnchantType;

@ScriptManifest(name = "Open Enchanter", description = "Enchants bolts and items", version = "1.0.1", category = ScriptCategory.Magic, author = "Great Mental", markdownFileName = "README.md")

@ScriptConfiguration.List({
        @ScriptConfiguration(name = "Method", description = "Type of enchant to cast", allowedValues = { "BOLT",
                "LEVEL_1", "LEVEL_2", "LEVEL_3", "LEVEL_4", "LEVEL_5", "LEVEL_6", "LEVEL_7" }, visible = true),
        @ScriptConfiguration(name = "Item to Enchant", description = "What item do you want to enchant", allowedValues = {
                "ONYX_DRAGON", "ONYX", "DRAGONSTONE_DRAGON", "DRAGONSTONE",
                "DIAMOND_DRAGON", "DIAMOND", "RUBY_DRAGON", "RUBY",
                "TOPAZ_DRAGON", "TOPAZ", "EMERALD_DRAGON", "EMERALD", "PEARL_DRAGON", "PEARL", "JADE_DRAGON",
                "JADE", "SAPPHIRE_DRAGON", "SAPPHIRE", "OPAL_DRAGON", "OPAL" }, visible = true),
        @ScriptConfiguration(name = "Level to stop", description = "Stops when reaches this level", optionType = OptionType.INTEGER, visible = true, defaultValue = "99"),
})

/**
 * Enum<?> selectedEnum = (Enum<?>) field.get(null);
 * int unenchantedID = ((Enchantable) selectedEnum).getUnenchantedID();
 * int enchantedID = ((Enchantable) selectedEnum).getEnchantedID();
 * int levelReq = ((Enchantable) selectedEnum).getLevelReq();
 */
public class Enchanter extends AbstractScript {
    protected Spell spell;
    protected Class<?> itemToCast;
    private String className; // User input for class name
    private String enumConstantName; // User input for enum constant name

    @Override
    public void onStart() {
        className = getOption("Method");
        enumConstantName = getOption("Item To Enchant");
        spell = EnchantType.valueOf(getOption("Method")).getSpell();
        try {
            Class<?> clazz = Class.forName("Enchanter.Data." + className);
            Field field = clazz.getField(enumConstantName);
            Enum<?> selectedEnum = (Enum<?>) field.get(null);

            // Print the information
            // ("Selected Enum: " + selectedEnum);
            // ("Unenchanted ID: " + unenchantedID);
            // ("Enchanted ID: " + enchantedID);
            // ("Level Requirement: " + levelReq);
        } catch (ClassNotFoundException | IllegalArgumentException | IllegalAccessException | NoSuchFieldException
                | SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void poll() {
        // Looped tasks go here. Example below is of a simple fighter script

    }

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
        // Start your script with this function. Make sure your device is connected via
        // ADB, and only one is connected
        new Enchanter().startScript();
    }
}
