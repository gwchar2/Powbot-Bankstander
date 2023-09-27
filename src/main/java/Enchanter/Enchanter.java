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

@ScriptManifest(name = "Open Enchanter",
        description = "Enchants bolts and items",
        version = "1.0.1",
        category = ScriptCategory.Magic,
        author = "Great Mental",
        markdownFileName = "README.md")

@ScriptConfiguration.List({
        @ScriptConfiguration(name = "Method", description = "Type of enchant to cast", allowedValues = { "BOLT",
                "LEVEL_1", "LEVEL_2", "LEVEL_3", "LEVEL_4", "LEVEL_5", "LEVEL_6", "LEVEL_7" }),
        @ScriptConfiguration(name = "Item to Enchant", description = "What item do you want to enchant", allowedValues = {
                "ONYX_DRAGON", "ONYX", "DRAGONSTONE_DRAGON", "DRAGONSTONE",
                "DIAMOND_DRAGON", "DIAMOND", "RUBY_DRAGON", "RUBY",
                "TOPAZ_DRAGON", "TOPAZ", "EMERALD_DRAGON", "EMERALD", "PEARL_DRAGON", "PEARL", "JADE_DRAGON",
                "JADE", "SAPPHIRE_DRAGON", "SAPPHIRE", "OPAL_DRAGON", "OPAL" }),
        @ScriptConfiguration(name = "Level to stop", description = "Stops when reaches this level",
                optionType = OptionType.INTEGER, defaultValue = "99"),
        @ScriptConfiguration(name = "Where to enchant", description = "Bankstands here",
                allowedValues = {"Grand Exchange", "Varrock West", "Varrock East", "Edgeville", "Camelot"})
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
        checkRequirements();
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


    }

    public void checkRequirements(){
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
        this.className = getOption("Method");
        this.enumConstantName = getOption("Item To Enchant");
        this.maxLevel = getOption("Level to stop");
        this.mySpell = EnchantType.valueOf(getOption("Method")).getSpell();
        try {
            Class<?> clazz = Class.forName("Enchanter.Data." + className);
            Field field = clazz.getField(enumConstantName);
            Enum<?> selectedEnum = (Enum<?>) field.get(null);
            this.unenchantedItemID = ((Enchantable) selectedEnum).getUnenchantedID();
            this.enchantedItemID = ((Enchantable) selectedEnum).getEnchantedID();
            this.levelReq = ((Enchantable) selectedEnum).getLevelReq();
        } catch (ClassNotFoundException | IllegalArgumentException | IllegalAccessException | NoSuchFieldException
                 | SecurityException ignored) {
        }
    }

    /**
     * Calls for MethodHandler according to "Method" change.
     * @param newMethod Fixed string.
     */
    @ValueChanged(keyName = "Method")
    public void methodUpdated(String newMethod) {
        updateAllowedOptions("Method", ConfigHandler.handleMethodUpdate(newMethod));
    }

    public static void main(String[] args) {
        new Enchanter().startScript();
    }
}
