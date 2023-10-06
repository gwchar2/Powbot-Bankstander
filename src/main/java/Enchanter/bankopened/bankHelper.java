package Enchanter.bankopened;

import org.powbot.api.Condition;
import org.powbot.api.Point;
import org.powbot.api.requirement.Requirement;
import org.powbot.api.requirement.RunePowerRequirement;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.powbot.api.rt4.*;
import org.powbot.api.rt4.magic.Rune;
import org.powbot.api.rt4.magic.Staff;
import org.powbot.mobile.rscache.loader.ItemLoader;
import org.powbot.mobile.script.ScriptManager;
import static Enchanter.Enchanter.*;
import static Enchanter.helpers.checks.atBank;
import static org.powbot.dax.shared.helpers.BankHelper.openBank;

public abstract class bankHelper {
    public static List<Staff> staffList;
    public static <staffList> void getitemsneeded(){
        boolean listDecider = true;
        if (!suitableWeapon){
            Requirements.removeIf(it -> Equipment.itemAt(Equipment.Slot.MAIN_HAND).name().contains(it.getPower().name().toLowerCase()));
            listDecider = false;
        }
        else {
            for (RunePowerRequirement it : Requirements) {
                if (it.getPower().isElemental()) {
                    staffList.add(it.getPower().getStaff());
                }
            }
        }
        userLog = "Withdrawing from bank";
        logger.info(userLog);
        if (!Bank.opened()) Condition.wait(() -> Bank.opened(), 300, 10);
        Bank.currentTab(0);
        if (!Inventory.opened()) Condition.wait(() -> Inventory.opened(), 300, 10);
        if (listDecider){
            for (Staff it : staffList){
                if(Inventory.stream().any().name().contains(it.name())) {
                    Condition.wait(it::equipped, 300, 10);
                    return ;
                    for (RunePowerRequirement its : Requirements){
                        if (its.getPower().getStaff().getId() == it.getId()){
                         //equipped from inventory to weapon slot   its.
                        }
                    }
                }
                else if (Bank.stream().any().name().contains("Staff")) {
                    Bank.withdraw(it.getId(), Bank.Amount.ONE);
                    Condition.wait(it::equipped, 300, 10);
                    return ;
                }
        }
        for (RunePowerRequirement it : Requirements){
            if (Bank.stream().any().name().contains(Rune.valueOf(it.getPower().name()).toString())){ // checks if RUNE is in the spell requirements
                Bank.withdraw(it.getPower().name(), Bank.Amount.ALL);
            else {
                userLog = "No runes exist in the bank!";
                logger.info(userLog);
                ScriptManager.INSTANCE.pause();
            }
        }
    }
// Remove items from the list that share the same string as the weapon
        if (suitableWeapon) {
            requirements.removeIf(requirement -> requirement.getName().toLowerCase().contains(weaponName.toLowerCase()));
        }



    }
    public static void openBank(){
        userLog = "Opening the bank";
        if (Bank.opened()){
            userLog = "Bank is open";
            return;
        }
        else if(Bank.inViewport()){
            Condition.wait(() -> Bank.open(), 50, 10);
            return;
        }
        else if(!Bank.inViewport()){
            userLog = "You are not near the bank!";
            if (attemptCounter == 2){
                userLog = "Could not find the bank twice!";
                logger.info(userLog);
                attemptCounter = 0;
                ScriptManager.INSTANCE.stop();
            }
            userLog = "Walking to the bank again!";
            logger.info(userLog);
            atBank();
            attemptCounter++;
            openBank();
        }

    }
    public static void withdrawFromBank(){

    }
    public static void closeBank(){

    }
    public static void castRequirements() {
        Requirements = mySpell.requirements()
                .stream()
                .filter(RunePowerRequirement.class::isInstance)
                .map(RunePowerRequirement.class::cast)
                .collect(Collectors.toList());
        Rune runeRequirements = new List<Rune>(Rune.)

    }

}
