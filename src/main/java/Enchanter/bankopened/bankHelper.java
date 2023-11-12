package Enchanter.bankopened;
import Enchanter.helpers.checks;
import org.powbot.api.Condition;
import org.powbot.api.rt4.*;
import org.powbot.mobile.script.ScriptManager;
import static Enchanter.Enchanter.*;
import static Enchanter.helpers.checks.atBank;


public abstract class bankHelper {

    /**
     * If user is at the correct bank, opens it
     * If not, walks to the bank and then opens it.
     * Equips correct staff and fixes requirement list.
     */
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
    public static void getitemsneeded(){
        int amountOfRuneSlots = Requirements.size();
        int amountOfRoomForItem = 28-amountOfRuneSlots;



    }

    /**
     * If correct staff exists in bank, wield it, and removeRune().
     */
    public static void findCorrectStaff() {
        if (suitableWeapon){
            removeRune(Equipment.itemAt(Equipment.Slot.MAIN_HAND).name());
            logger.info("Removed correct rune from requirement list");
        }
        else {
            logger.info("Searching for correct staff");
            Bank.currentTab(0); // Goes to first tab
            Item staff = Bank.stream().nameContains("staff").first();
            Item airstaffBank = Bank.stream().name("Air battlestaff","Mystic air staff","Staff of air").first();
            Item airstaffInv = Inventory.stream().name("Air battlestaff","Mystic air staff","Staff of air").first();
            Item earthstaffBank = Bank.stream().name("Earth battlestaff", "Mystic earth staff", "Staff of earth").first();
            Item earthstaffInv = Inventory.stream().name("Earth battlestaff", "Mystic earth staff", "Staff of earth").first();
            Item firestaffBank = Bank.stream().name("Fire battlestaff", "Mystic fire staff", "Staff of fire").first();
            Item firestaffInv = Inventory.stream().name("Fire battlestaff", "Mystic fire staff", "Staff of fire").first();
            Item waterstaffBank = Bank.stream().name("Water battlestaff", "Mystic water staff", "Staff of water").first();
            Item waterstaffInv = Inventory.stream().name("Water battlestaff", "Mystic water staff", "Staff of water").first();
            if(!staff.valid()) {
                logger.info("No staff in bank!");
                return;
            }
            if (mySpell.name().contains("LEVEL_1")){
                if (waterstaffBank.valid() || waterstaffInv.valid()){
                    if (waterstaffInv.valid()) {
                        wieldStaff(waterstaffInv);
                    }
                    else {
                        Bank.withdraw(waterstaffBank, 1);
                        waterstaffInv = Inventory.stream().name("Water battlestaff", "Mystic water staff", "Staff of water").first();
                        waterstaffInv.interact("Wield");
                    }
                    removeRune("WATER");
                    suitableWeapon = true;
                }
            }
            else if (mySpell.name().contains("LEVEL_2")){
                if (airstaffBank.valid() || airstaffInv.valid()){
                    if (airstaffInv.valid()) {
                        airstaffInv.interact("Wield");
                    }
                    else if (airstaffBank.valid()){
                        Bank.withdraw(airstaffBank,1);
                        airstaffInv = Inventory.stream().name("Air battlestaff", "Mystic air staff", "Staff of air").first();
                        airstaffInv.interact("Wield");
                    }
                    removeRune("AIR");
                    suitableWeapon = true;
                    }
            }
            else if (mySpell.name().contains("LEVEL_3")){
                if (firestaffBank.valid() || firestaffInv.valid()){
                    if (firestaffInv.valid()) {
                        firestaffInv.interact("Wield");
                    }
                    else if (firestaffBank.valid()){
                        Bank.withdraw(firestaffBank,1);
                        firestaffInv = Inventory.stream().name("Fire battlestaff", "Mystic fire staff", "Staff of fire").first();
                        firestaffInv.interact("Wield");
                    }
                    removeRune("FIRE");
                    suitableWeapon = true;
                }
            }
            else if (mySpell.name().contains("LEVEL_4")){
                if (earthstaffBank.valid() || earthstaffInv.valid()){
                    if (earthstaffInv.valid()) {
                        earthstaffInv.interact("Wield");
                    }
                    else if (earthstaffBank.valid()){
                        Bank.withdraw(earthstaffBank,1);
                        earthstaffInv = Inventory.stream().name("Earth battlestaff", "Mystic earth staff", "Staff of earth").first();
                        earthstaffInv.interact("Wield");
                    }
                    removeRune("EARTH");
                    suitableWeapon = true;
                }
            }
            else if (mySpell.name().contains("LEVEL_5")){
                if (earthstaffBank.valid() || earthstaffInv.valid() || waterstaffInv.valid() || waterstaffBank.valid()){
                    if (earthstaffInv.valid()) {
                        earthstaffInv.interact("Wield");
                        removeRune("EARTH");
                    }
                    else if (waterstaffInv.valid()){
                        waterstaffInv.interact("Wield");
                        removeRune("WATER");
                    }
                    else if (earthstaffBank.valid()) {
                        Bank.withdraw(earthstaffBank,1);
                        earthstaffInv = Inventory.stream().name("Earth battlestaff", "Mystic earth staff", "Staff of earth").first();
                        earthstaffInv.interact("Wield");
                        removeRune("EARTH");
                    }
                    else if (waterstaffBank.valid()){
                        Bank.withdraw(waterstaffBank,1);
                        waterstaffInv = Inventory.stream().name("Water battlestaff", "Mystic water staff", "Staff of water").first();
                        waterstaffInv.interact("Wield");
                        removeRune("WATER");
                    }
                    suitableWeapon = true;
                }
            }
            else if (mySpell.name().contains("LEVEL_6")){
                if (earthstaffBank.valid() || earthstaffInv.valid() || firestaffBank.valid() || firestaffInv.valid()){
                    if (earthstaffInv.valid()) {
                        earthstaffInv.interact("Wield");
                        removeRune("EARTH");
                    }
                    else if (firestaffInv.valid()){
                        firestaffInv.interact("Wield");
                        removeRune("FIRE");
                    }
                    else if (earthstaffBank.valid()) {
                        Bank.withdraw(earthstaffBank,1);
                        earthstaffInv = Inventory.stream().name("Earth battlestaff", "Mystic earth staff", "Staff of earth").first();
                        earthstaffInv.interact("Wield");
                        removeRune("EARTH");
                    }
                    else if (firestaffBank.valid()){
                        Bank.withdraw(firestaffBank,1);
                        firestaffInv = Inventory.stream().name("Fire battlestaff", "Mystic fire staff", "Staff of fire").first();
                        firestaffInv.interact("Wield");
                        removeRune("FIRE");
                    }
                    suitableWeapon = true;
                }
            }
            else if (mySpell.name().contains("LEVEL_7")){
                logger.info("No such staff!");
            }
            else {
                logger.info("No staff in the bank!");
            }
        }

    }
    /**
     * Removes rune from requirement list according to equipped weapon
     * (Need to add special staffs like mud smoke etc)
     */
    public static void removeRune(String rune) {
            Requirements.removeIf(it -> it.getPower().name().contains(rune));
       // Requirements.removeIf(it -> Equipment.itemAt(Equipment.Slot.MAIN_HAND).name().contains(it.getPower().name().toLowerCase()));
    }
    public static void wieldStaff(Item item){
        item.interact("Wield");
        Condition.wait(() -> !item.valid(), 300, 10);
    }
}



