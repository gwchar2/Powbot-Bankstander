package Enchanter;

import Enchanter.Data.BankAreas;
import Enchanter.Data.Enchantable;
import org.powbot.api.Area;
import org.powbot.api.rt4.Equipment;
import org.powbot.api.rt4.Magic;
import org.powbot.api.rt4.walking.model.Skill;
import org.powbot.api.script.AbstractScript;
import org.powbot.api.script.OptionType;
import org.powbot.api.script.ScriptCategory;
import org.powbot.api.script.ScriptConfiguration;
import org.powbot.api.script.ScriptManifest;
import org.powbot.api.script.ValueChanged;
import java.util.Arrays;


@ScriptManifest(name = "Open Enchanter",
        description = "Enchants bolts and items",
        version = "1.0.1",
        category = ScriptCategory.Magic,
        author = "Great Mental",
        markdownFileName = "README.md")

@ScriptConfiguration.List({
        @ScriptConfiguration(name = "Method", description = "Type of enchant to cast", allowedValues = { "","BOLT",
                "LEVEL_1", "LEVEL_2", "LEVEL_3", "LEVEL_4", "LEVEL_5", "LEVEL_6", "LEVEL_7" }),

        @ScriptConfiguration(name = "Item to Enchant", description = "What item do you want to enchant", allowedValues = {
                "ONYX_DRAGON", "ONYX", "DRAGONSTONE_DRAGON", "DRAGONSTONE",
                "DIAMOND_DRAGON", "DIAMOND", "RUBY_DRAGON", "RUBY",
                "TOPAZ_DRAGON", "TOPAZ", "EMERALD_DRAGON", "EMERALD", "PEARL_DRAGON", "PEARL", "JADE_DRAGON",
                "JADE", "SAPPHIRE_DRAGON", "SAPPHIRE", "OPAL_DRAGON", "OPAL" }, visible = false, defaultValue = "DEFAULT_VALUE"),

        @ScriptConfiguration(name = "Level to stop", description = "Level to Stop at. Leave empty to never stop!",
                optionType = OptionType.INTEGER, defaultValue = "0"),

        @ScriptConfiguration(name = "Where to enchant", description = "Bankstands here",
                allowedValues = {"GRAND_EXCHANGE", "VARROCK_WEST", "VARROCK_EAST", "EDGEVILLE"},defaultValue = "DEFAULT_VALUE")
})


public class Enchanter extends AbstractScript{
    protected static Enchantable enchantableEnum; //The enum method selected by user (Narrowed down to item name), implements all enchantable.
    protected static String className; // User input for class name
    protected static String enumConstantName; // User input for enum constant name
    protected static String bankName; // User input for bank name
    protected static Magic.Spell mySpell; // Chosen spell
    protected static Area bankArea; // The final bank area
    protected static int maxLevel;
    @Override
    public void onStart() {
        ConfigHandler.extractConfig(getOption("Method"),
                getOption("Item To Enchant"),
                getOption("Level to stop"),
                getOption("Method"),
                getOption("Where to enchant"));
        System.out.println(bankName +"\n"+ Arrays.toString(BankAreas.GRAND_EXCHANGE.getBankArea().get_tiles()));
        System.out.println(mySpell+"\n"+enchantableEnum.getUnenchantedID()+"\n"+enchantableEnum.getEnchantedID()+"\n"+enchantableEnum.getLevelReq()+"\n"+maxLevel+"\n"+Equipment.itemAt(Equipment.Slot.MAIN_HAND).name()+"\n");
        System.out.println("Did it work?"+enchantableEnum.getName());
        // checkRequirements();
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
        if (Skill.Magic.realLevel() > enchantableEnum.getLevelReq() ||  Magic.Book.MODERN.name().compareTo(Magic.book().name()) != 0 || Skill.Magic.realLevel() > maxLevel) {
            System.out.println("False");
        }
        //List<Requirement> requiredItems = mySpell.requirements();
        //Item equippedWeapon = Equipment.itemAt(Equipment.Slot.MAIN_HAND);

    }

    /**
     * Calls for MethodHandler according to "Method" change.
     * @param newMethod Fixed string.
     */
    @ValueChanged(keyName = "Method")
    public void  updateMethod(String newMethod){
        updateAllowedOptions("Item to Enchant", ConfigHandler.methodUpdated(newMethod));
        if (newMethod.isEmpty())
            updateVisibility("Item to Enchant", false);
        else
            updateVisibility("Item to Enchant", true);
    }

    public static void main(String[] args) {
        new Enchanter().startScript();
    }
}
