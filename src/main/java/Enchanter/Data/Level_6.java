package Enchanter.Data;

import org.powbot.mobile.rscache.loader.ItemLoader;

public enum Level_6 implements Enchantable{
    ONYX_RING(ItemLoader.lookup("Onyx ring").id(), ItemLoader.lookup("Ring of stone").id(), 87),
    ONYX_BRACELET(ItemLoader.lookup("Onyx bracelet").id(),ItemLoader.lookup("Berserker necklace").id(), 87),
    ONYX_NECKLACE(ItemLoader.lookup("Onyx necklace").id(),ItemLoader.lookup("Regen bracelet").id(), 87),
    ONYX_AMULET(ItemLoader.lookup("Onyx amulet").id(),ItemLoader.lookup("Amulet of fury").id(), 87);

    private int unenchantedID;
    private int enchantedID;
    private int levelReq;

    Level_6(int unenchantedID, int enchantedID, int levelReq){
        this.unenchantedID = unenchantedID;
        this.enchantedID = enchantedID;
        this.levelReq = levelReq;
    }

    public int getUnenchantedID(){
        return unenchantedID;
    }

    public int getEnchantedID(){
        return enchantedID;
    }

    public int getLevelReq(){
        return levelReq;
    }

 
}

