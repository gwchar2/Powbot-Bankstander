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
import static Enchanter.helpers.checks.checkWeapon;
import static org.powbot.dax.shared.helpers.BankHelper.openBank;

public abstract class bankHelper {

    public static <staffList> void getitemsneeded(){


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

    public static void openBank() {
        userLog = "Opening the bank";
        if (Bank.opened()){
            userLog = "Bank is open";
            return;
        }
        else if(Bank.inViewport()){
            Condition.wait(Bank::open, 50, 10);
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
            attemptCounter++;
            atBank();
            openBank();
        }
    }

}
