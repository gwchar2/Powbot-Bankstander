package Enchanter.bankopened;
import org.powbot.api.Condition;
import org.powbot.api.requirement.RunePowerRequirement;

import java.util.List;
import java.util.stream.Collectors;

import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Equipment;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Item;
import org.powbot.api.rt4.magic.Rune;
import org.powbot.api.rt4.magic.Staff;
import org.powbot.mobile.script.ScriptManager;

import static Enchanter.Enchanter.*;
import static Enchanter.helpers.checks.atBank;

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
        if (!Bank.opened()) openBank();
        Bank.currentTab(1);
        userLog = "Withdrawing from bank";
        logger.info(userLog);
        for (RunePowerRequirement it : Requirements){
            Bank.withdraw(it.getPower().name(), Bank.Amount.ALL);
        }
        if (listDecider){
            for (Staff it : staffList){
                if (Bank.stream().contains(Staff.valueOf(it.name()).getId()))
                Bank.withdraw(staff.getId(), Bank.Amount.ONE);
                if(!Inventory.open()) Inventory.open();
                    staff.inventory();
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

    }

}
