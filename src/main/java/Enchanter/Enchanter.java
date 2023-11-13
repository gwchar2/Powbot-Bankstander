package Enchanter;

import Enchanter.Data.BankAreas;
import Enchanter.Data.Enchantable;
import Enchanter.helpers.bankHelper;
import Enchanter.helpers.checks;
import org.powbot.api.Area;
import org.powbot.api.Condition;
import org.powbot.api.Tile;
import org.powbot.api.requirement.RunePowerRequirement;
import org.powbot.api.rt4.*;
import org.powbot.api.rt4.walking.model.Skill;
import org.powbot.api.script.AbstractScript;
import org.powbot.api.script.OptionType;
import org.powbot.api.script.ScriptCategory;
import org.powbot.api.script.ScriptConfiguration;
import org.powbot.api.script.ScriptManifest;
import org.powbot.api.script.ValueChanged;
import org.powbot.api.script.paint.Paint;
import org.powbot.api.script.paint.PaintBuilder;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.logging.Logger;

import static Enchanter.helpers.bankHelper.*;


@ScriptManifest(name = "Open Enchanter",
        description = "Enchants bolts and items",
        version = "1.0.1",
        category = ScriptCategory.Magic,
        author = "Great Mental",
        markdownFileName = "README.md")

@ScriptConfiguration.List({
        @ScriptConfiguration(name = "Method", description = "Type of enchant to cast", allowedValues = {"", "BOLT",
                "LEVEL_1", "LEVEL_2", "LEVEL_3", "LEVEL_4", "LEVEL_5", "LEVEL_6", "LEVEL_7"}),

        @ScriptConfiguration(name = "Item to Enchant", description = "What item do you want to enchant", allowedValues = {
                "ONYX_DRAGON", "ONYX", "DRAGONSTONE_DRAGON", "DRAGONSTONE",
                "DIAMOND_DRAGON", "DIAMOND", "RUBY_DRAGON", "RUBY",
                "TOPAZ_DRAGON", "TOPAZ", "EMERALD_DRAGON", "EMERALD", "PEARL_DRAGON", "PEARL", "JADE_DRAGON",
                "JADE", "SAPPHIRE_DRAGON", "SAPPHIRE", "OPAL_DRAGON", "OPAL"}, visible = false, defaultValue = "DEFAULT_VALUE"),

        @ScriptConfiguration(name = "Level to stop", description = "Level to Stop at. Leave empty to never stop!",
                optionType = OptionType.INTEGER, defaultValue = "0"),

        @ScriptConfiguration(name = "Where to enchant", description = "Bankstands here",
                allowedValues = {"GRAND_EXCHANGE", "VARROCK_WEST", "VARROCK_EAST", "EDGEVILLE"}, defaultValue = "DEFAULT_VALUE")
})


public class Enchanter extends AbstractScript {
    public static final Logger logger = Logger.getLogger("Enchanter : ");

    public static List<RunePowerRequirement> Requirements;
    public static Enchantable enchantableEnum; //The enum method selected by user (Narrowed down to item name), implements all enchantable.
    protected static String className; // User input for class name
    public static String enumConstantName; // User input for enum constant name
    public static String bankName; // User input for bank name
    public static Magic.Spell mySpell; // Chosen spell
    public static Area bankArea; // The final bank area
    public static Tile myBankTile;
    public static int maxLevel;
    public static Player player;
    public static boolean suitableWeapon;
    public static String userLog;
    public static int attemptCounter = 0;

    /**
     * Gets the data upon pressing Start.
     */
    @Override
    public void onStart() {
        player = Players.local();
        ConfigHandler.extractConfig(getOption("Method"),
                getOption("Item To Enchant"),
                getOption("Level to stop"),
                getOption("Method"),
                getOption("Where to enchant"));
        logger.info("Finished extracting from config!");
        logger.info("Chosen Bank: " + bankName + "\nTiles: " + Arrays.toString(BankAreas.GRAND_EXCHANGE.getBankArea().get_tiles()));
        logger.info("Your chosen Spell: " + mySpell.name() + " Spell level requirement: " + enchantableEnum.getLevelReq());
        logger.info("Item to enchant: " + enumConstantName + " Unenchanted ID: " + enchantableEnum.getUnenchantedID() + " Enchanted ID: " + enchantableEnum.getEnchantedID());
        logger.info("Level to stop: " + maxLevel);
        checks.checkRequirements();
        checks.castRequirements();
        Requirements.forEach(it -> logger.info("Rune: " + it.getPower() + " Amount: " + it.getAmount()));
        myBankTile = BankAreas.valueOf(bankName).makeTile(bankName);
        suitableWeapon = checks.checkWeapon();
        addPaint();
        //RunePowerRequirements.forEach(it -> amount.set(it.getAmount())); for each it in RunePowerReq list set(it.getAmount) to local int amount.
        //for (RunePowerRequirement requirement : RunePowerRequirements) {} Good for the withdraw from bank method !!
        //}

    }
    @Override
    public void poll() {
        Condition.wait(checks::atBank,1000,10);
        Condition.wait(bankHelper::openBank,1000,10);
        findCorrectStaff();
        withdrawRunes();
        Condition.wait(() -> mySpell.canCast(),150,10);
        withdrawItem();
        // if out of the area, turn searchedStaff==false, withdrawnRunes==false
    }

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

    private void addPaint() {
       Paint paint = PaintBuilder.newBuilder()
               .addString("Method: ", new Callable<String>() {
                   @Override
                   public String call() throws Exception {
                       return mySpell.name();
                   }
               })
               .addString("Stage: ", new Callable<String>() {
                   @Override
                   public String call() throws Exception {
                       return userLog;
                   }
               })
               .trackSkill(Skill.Magic)
               .trackInventoryItem(enchantableEnum.getEnchantedID(),"Enchants")
               .y(45)
               .x(40)
               .build();
        addPaint(paint);
    }
    public static void main(String[] args) {
        new Enchanter().startScript();
    }
}


