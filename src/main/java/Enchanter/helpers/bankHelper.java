package Enchanter.helpers;

import org.powbot.api.Condition;
import org.powbot.api.requirement.RunePowerRequirement;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Equipment;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Item;
import org.powbot.mobile.script.ScriptManager;

import static Enchanter.Enchanter.*;
import static Enchanter.helpers.checks.atBank;
import static Enchanter.helpers.misc.removeRune;
import static Enchanter.helpers.misc.wieldStaff;
import static org.powbot.api.rt4.Bank.withdraw;


public abstract class bankHelper {
    public static boolean withdrawnRunes = false;
    public static boolean searchedStaff = false;

    /**
     * If user is at the correct bank, opens it
     * If not, walks to the bank and then opens it.
     * Equips correct staff and fixes requirement list.
     * @return True if bank is opened, False if cant open the bank after multiple attempts
     */
    public static boolean openBank() {
        userLog = "Opening the bank";
        if (Bank.opened()){
            userLog = "Bank is open";
            return true;
        }
        else if(Bank.inViewport()){
            Condition.wait(Bank::open, 50, 10);
            return true;
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
        return false;
    }

    /**
     * Withdraws the required runes to cast.
     */
    public static void withdrawRunes(){
        if (!searchedStaff) {
            withdrawnRunes = false;
            return;
        }
        if (Bank.open() && Bank.currentTab (0) && (!withdrawnRunes || !mySpell.canCast())) {
            if (Inventory.isNotEmpty()) Condition.wait(Bank::depositInventory,300,10);
            for (RunePowerRequirement it : Requirements) {
                if (it != null) {
                    String rune = it.getPower().name().toLowerCase() + " rune";
                    rune = Character.toUpperCase(rune.charAt(0)) + rune.substring(1);
                    userLog = "Withdrawing " + rune;
                    if (!Inventory.stream().name(rune).first().valid() && Inventory.stream().name(rune).first().stackSize() < 28 * it.getAmount() && Bank.stream().name(rune).first().valid()) {
                        withdraw(rune, Bank.Amount.ALL);
                        String finalRune = rune;
                        Condition.wait(() -> Inventory.stream().name(finalRune).first().valid(), 300, 10);
                    } else {
                        userLog = "No " + rune + " in the bank, pausing for 15 seconds";
                        Condition.sleep(15000);
                        ScriptManager.INSTANCE.stop();
                        return;
                    }
                }
            }
            withdrawnRunes = true;
        }
    }

    /**
     * If correct staff exists in bank, wield it, and removeRune().
     */
    public static void findCorrectStaff() {
        if (searchedStaff) return; // If already ran this method once, no need to run again !!
        if (Inventory.isFull()) Bank.depositInventory();
        if (suitableWeapon){
            removeRune(Equipment.itemAt(Equipment.Slot.MAIN_HAND).name());
            logger.info("Removed correct rune from requirement list");
            for (RunePowerRequirement its : Requirements){
                System.out.println(its.getPower().name());
            }
        }
        else {
            userLog = "Searching for correct staff";
            logger.info(userLog);
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
                userLog = "No staff in bank!";
                logger.info(userLog);
                return;
            }
            userLog = "Withdrawing staff";
            logger.info(userLog);
            if (mySpell.name().contains("LEVEL_1")){
                if (waterstaffBank.valid() || waterstaffInv.valid()){
                    if (waterstaffInv.valid()) {
                        wieldStaff(waterstaffInv);
                    }
                    else {
                        withdraw(waterstaffBank, 1);
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
                        withdraw(airstaffBank,1);
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
                        withdraw(firestaffBank,1);
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
                        withdraw(earthstaffBank,1);
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
                        withdraw(earthstaffBank,1);
                        earthstaffInv = Inventory.stream().name("Earth battlestaff", "Mystic earth staff", "Staff of earth").first();
                        earthstaffInv.interact("Wield");
                        removeRune("EARTH");
                    }
                    else if (waterstaffBank.valid()){
                        withdraw(waterstaffBank,1);
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
                        withdraw(earthstaffBank,1);
                        earthstaffInv = Inventory.stream().name("Earth battlestaff", "Mystic earth staff", "Staff of earth").first();
                        earthstaffInv.interact("Wield");
                        removeRune("EARTH");
                    }
                    else if (firestaffBank.valid()){
                        withdraw(firestaffBank,1);
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
        searchedStaff = true;
        if (Inventory.isNotEmpty()) Bank.depositInventory();
    }

    /**
     * Withdraws the required amount of items to enchant
     */
    public static void withdrawItem(){
        userLog = "Withdrawing "+enumConstantName;
        boolean enchantableItem = Inventory.stream().id(enchantableEnum.getUnenchantedID()).first().valid(); // Total amount of enchantable item in inventory
        if (!searchedStaff || !withdrawnRunes) return;
        if (!mySpell.casting() && !enchantableItem){ // If you are done casting and no more enchantable item in inventory
            if (!Bank.opened()) Bank.open();
            if (mySpell.name().contains("LEVEL_1"))
                Bank.depositAllExcept("Water rune", "Cosmic rune"); // bank all except runes
            else if (mySpell.name().contains("LEVEL_2"))
                Bank.depositAllExcept( "Cosmic rune", "Air rune"); // bank all except runes
            else if (mySpell.name().contains("LEVEL_3"))
                Bank.depositAllExcept("Cosmic rune","Fire rune"); // bank all except runes
            else if (mySpell.name().contains("LEVEL_4"))
                Bank.depositAllExcept( "Cosmic rune", "Earth rune"); // bank all except runes
            else if (mySpell.name().contains("LEVEL_5"))
                Bank.depositAllExcept("Water rune", "Cosmic rune", "Earth rune"); // bank all except runes
            else if (mySpell.name().contains("LEVEL_6"))
                Bank.depositAllExcept( "Cosmic rune", "Fire rune", "Earth rune"); // bank all except runes
            else if (mySpell.name().contains("LEVEL_7"))
                Bank.depositAllExcept("Cosmic rune", "Blood rune", "Soul rune"); // bank all except runes
            if (Bank.stream().id(enchantableEnum.getUnenchantedID()).first().valid() && Bank.stream().id(enchantableEnum.getUnenchantedID()).first().stackSize()>=28-Requirements.size()) { // If there are more of enchantable item in bank (Higher than minimum amount)
                withdraw(enchantableEnum.getUnenchantedID(), Inventory.emptySlotCount()); // Withdraw more.
            }
            else{ // If the item is not valid in bank OR if less than minimum amount
                userLog = "No item to withdraw!";
                ScriptManager.INSTANCE.stop();
            }
        }
        else if (!mySpell.canCast() && Bank.open() && !enchantableItem){ // If not casting, bank open, and still have enchantables in inventory
            userLog = "No more runes! Depositing & Closing script.";
            Bank.depositInventory(); // Deposit everything and close the script.
            Condition.sleep(15000);
            ScriptManager.INSTANCE.stop();
        }
       // get the total amount of casts available & compare to total amount of item in bank
       // get the price of runes used (if staff equipped, no element rune) from ge, and price of item enchanted item not enchanted, and enable profit counter.
    }

}



