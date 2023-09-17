package Enchanter.Data;

import org.powbot.mobile.rscache.loader.ItemLoader;

public enum Level_5 implements Enchantable{
    DRAGONSTONE_RING(ItemLoader.lookup("Dragonstone ring").id(), ItemLoader.lookup("Ring of wealth").id(), 68),
    DRAGONSTONE_BRACELET(ItemLoader.lookup("Dragonstone bracelet").id(), ItemLoader.lookup("Skills necklace").id(), 68),
    DRAGONSTONE_NECKLACE(ItemLoader.lookup("Dragonstone necklace").id(), ItemLoader.lookup("Combat bracelet").id(), 68),
    DRAGONSTONE_AMULET(ItemLoader.lookup("Dragonstone amulet").id(), ItemLoader.lookup("Amulet of glory").id(), 68);

    private int unenchantedID;
    private int enchantedID;
    private int levelReq;

    Level_5(int unenchantedID, int enchantedID, int levelReq){
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

