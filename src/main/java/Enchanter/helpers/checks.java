package Enchanter.helpers;

import Enchanter.Enchanter;
import org.powbot.api.Condition;
import org.powbot.api.requirement.RunePowerRequirement;
import org.powbot.api.rt4.Equipment;
import org.powbot.api.rt4.Magic;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.walking.model.Skill;
import org.powbot.mobile.script.ScriptManager;

import java.util.logging.Logger;
import java.util.stream.Collectors;

public class checks extends Enchanter {

    public static final Logger logger = Logger.getLogger("Requirement checks : ");

    /**
     * Checks to see if level required is lower than current level, and if current level is lower than "Stop Level"
     * @return True when done checking the requirements
     */
    public static boolean checkRequirements() {
        if (Skill.Magic.realLevel() >= enchantableEnum.getLevelReq() && Magic.Book.MODERN.name().compareTo(Magic.book().name()) == 0 && Skill.Magic.realLevel() <= maxLevel)
            logger.info("You meet the requirements.");
        else {
            logger.info("You don't meet the requirements.");
            ScriptManager.INSTANCE.stop();
        }
        return true;
    }
    /**
     * Makes a list of required runes
     */
    public static void castRequirements() {
        Requirements = mySpell.requirements().stream().filter(RunePowerRequirement.class::isInstance).map(RunePowerRequirement.class::cast).collect(Collectors.toList());
    }

    /**
     * Checks if the user is at the bank
     * If the user is not at the bank - moves the user there.
     * @return true if at bank, false if not.
     */
    public static boolean atBank() {
        if (bankArea.contains(player.tile())) {
            if (player.tile().equals(myBankTile)) {
                Condition.wait(() -> player.inMotion(), 100, 15);
                logger.info("User is at the: " + bankName + " Bank");
                return true;
            } else {
                userLog = "Need to walk to the bank booth";
                logger.info(userLog);
                Movement.step(myBankTile);
                if (Condition.wait(() -> player.inMotion(), 100, 15)) {
                    Condition.wait(() -> !player.inMotion(), 150, 25);
                }
            }
        } else {
            userLog = "Need to walk to the bank area";
            logger.info(userLog);
            Movement.builder(myBankTile).setRunMin(25).move();
            if (Condition.wait(() -> player.inMotion(), 50, 15)) {
                Condition.wait(() -> !player.inMotion(), 150, 25);
            }
        }
        if (player.tile().equals(myBankTile)) return true;
        else {
            logger.info("Not on exact tile");
            return false;
        }
    }

    /**
     * Checks if the weapon equipped counts instead of a rune
     * @return True if yes, False if no.
     */
    public static boolean checkWeapon() {
       for (RunePowerRequirement it : Requirements) {
            if (Equipment.itemAt(Equipment.Slot.MAIN_HAND).name().toLowerCase().contains(it.getPower().name().toLowerCase())) {
                logger.info("Main Hand Weapon is instead of a rune! : " + Equipment.itemAt(Equipment.Slot.MAIN_HAND).name());
                return true;
            }
        }
        logger.info("Need to check bank for a weapon");
        return false;
    }
}
