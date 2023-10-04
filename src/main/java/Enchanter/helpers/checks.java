package Enchanter.helpers;
import Enchanter.Enchanter;
import Enchanter.ConfigHandler;
import org.powbot.api.Condition;
import org.powbot.api.requirement.RunePowerRequirement;
import org.powbot.api.rt4.Equipment;
import org.powbot.api.rt4.Magic;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.walking.model.Skill;
import org.powbot.api.script.ScriptState;

import java.util.logging.Logger;
import java.util.stream.Collectors;

public class checks extends Enchanter {

    public static Logger LoggerFactory;
    public static final Logger logger = Logger.getLogger("Requirement checks : ");

    /**
     * Checks the requirements needed / available from user.
     */
    public static boolean checkRequirements() {
       if(Skill.Magic.realLevel() >= enchantableEnum.getLevelReq() && Magic.Book.MODERN.name().compareTo(Magic.book().name()) == 0 && Skill.Magic.realLevel() <= maxLevel)
           logger.info("You meet the requirements.");
       else {
           logger.info("You don't meet the requirements.");
           return false;
       }
       return true;
    }

    public static void castRequirements() {
        Enchanter.RunePowerRequirements = mySpell.requirements()
                .stream()
                .filter(RunePowerRequirement.class::isInstance)
                .map(RunePowerRequirement.class::cast)
                .collect(Collectors.toList());
    }

    public static boolean atBank() {
        if (bankArea.contains(player.tile())) {
            if (player.tile().equals(myBankTile)) {
                logger.info("User is at the: " + bankName + " Bank");
                return true;
            } else {
                logger.info("Need to walk to the bank booth");
                Movement.step(myBankTile);
                if (Condition.wait(() -> player.inMotion(), 50, 15)) {
                    Condition.wait(() -> !player.inMotion(), 150, 25);
                }
            }
        } else {
            logger.info("Need to walk to the bank area");
            Movement.builder(myBankTile).setRunMin(25).move();
            if (Condition.wait(() -> player.inMotion(), 50, 15)) {
                Condition.wait(() -> !player.inMotion(), 150, 25);
            }
        }
        if (player.tile().equals(myBankTile)) return true;
        else {
            logger.info("Something broke");
            return false;
        }
    }

    public static boolean checkWeapon(){
        if (mySpell.name().contains("JEWELLERY") && Equipment.itemAt(Equipment.Slot.MAIN_HAND).name().contains(RunePowerRequirements.get(1).getPower().name().toLowerCase())){
            logger.info("Main Hand Weapon is instead of a rune! : " + Equipment.itemAt(Equipment.Slot.MAIN_HAND).name());
            return false;
        }
        else {
            logger.info("Need to check bank for a weapon");
            return true;
        }
    }

}
