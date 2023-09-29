package Enchanter;

import Enchanter.Data.BankAreas;
import Enchanter.Data.Enchantable;
import org.powbot.api.Area;
import org.powbot.api.requirement.RunePowerRequirement;
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
import java.util.List;
import java.util.stream.Collectors;



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


public class Enchanter extends AbstractScript {
    protected static List<RunePowerRequirement> RunePowerRequirements;
    protected static Enchantable enchantableEnum; //The enum method selected by user (Narrowed down to item name), implements all enchantable.
    protected static String className; // User input for class name
    protected static String enumConstantName; // User input for enum constant name
    protected static String bankName; // User input for bank name
    protected static Magic.Spell mySpell; // Chosen spell
    protected static Area bankArea; // The final bank area
    protected static int maxLevel;

    /**
     * Calls for MethodHandler according to "Method" change.
     *
     * @param newMethod Fixed string.
     */
    @ValueChanged(keyName = "Method")
    public void updateMethod(String newMethod) {
        updateAllowedOptions("Item to Enchant", ConfigHandler.methodUpdated(newMethod));
        if (newMethod.isEmpty())
            updateVisibility("Item to Enchant", false);
        else
            updateVisibility("Item to Enchant", true);
    }

    /**
     * Gets the data upon pressing Start.
     */
    @Override
    public void onStart() {
        ConfigHandler.extractConfig(getOption("Method"),
                getOption("Item To Enchant"),
                getOption("Level to stop"),
                getOption("Method"),
                getOption("Where to enchant"));
        getLog().info("Finished extracting from config!");
        getLog().info("Chosen Bank: " + bankName + "\nTiles: " + Arrays.toString(BankAreas.GRAND_EXCHANGE.getBankArea().get_tiles()));
        getLog().info("Your chosen Spell: " + mySpell.name() + " Spell level requirement: "+enchantableEnum.getLevelReq());
        getLog().info("Item to enchant: " + enumConstantName + " Unenchanted ID: " +enchantableEnum.getUnenchantedID()+ " Enchanted ID: "+enchantableEnum.getEnchantedID());
        getLog().info("Level to stop: " + maxLevel);
        getLog().info("Main Hand Weapon: " + Equipment.itemAt(Equipment.Slot.MAIN_HAND).name());
        checkRequirements();
        RunePowerRequirements.forEach(it -> getLog().info("Rune: " + it.getPower() + " Amount: " + it.getAmount()));
        //RunePowerRequirements.forEach(it -> amount.set(it.getAmount())); for each it in RunePowerReq list set(it.getAmount) to local int amount.


        //for (RunePowerRequirement requirement : RunePowerRequirements) {} Good for the withdraw from bank method !!
        //}

        // finish requirements method
        // checkMagicLevel() -- check can cast according to magic level
        // checkIfAtBank() -- check if you are at the bank area already
        // if not at bank, move to the bank.
        // moveToBank() - teleports to the bank, or walks to it.
        // if you are at the bank :
        // getrunesneeded()
        // if have element staff in bank (or inventory, or equipped) that is = to element rune, wield it.
        // check for the runes , withdraw all.
        // check for the item chosen, save as int the total amount in the bank. maybe try "TAOR" = amountinbank() (total amount of runes = ) or "TAOI" (total amount of item)
        // get the price of runes used (if staff equipped, no element rune) from ge, and price of item enchanted item not enchanted, and enable profit counter.
        // startCasting() - has designed click paterns, and fast clicking for bolts.
    }
    @Override
    public void poll() {

    }

    /**
     * Checks the requirements needed / available from user.
     */
    public void checkRequirements() {
        if (Skill.Magic.realLevel() < enchantableEnum.getLevelReq() || Magic.Book.MODERN.name().compareTo(Magic.book().name()) != 0 || Skill.Magic.realLevel() > maxLevel) {
            getLog().info("You don't meet the requirements");
        }
        RunePowerRequirements = mySpell.requirements()
                .stream()
                .filter(RunePowerRequirement.class::isInstance)
                .map(RunePowerRequirement.class::cast)
                .collect(Collectors.toList());

    }

    public static void main (String[]args){
        new Enchanter().startScript();
    }

}
