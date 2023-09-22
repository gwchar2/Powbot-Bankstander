package Enchanter.Data;

import org.powbot.mobile.rscache.loader.ItemLoader;

public enum Level_4 implements Enchantable{
    DIAMOND_RING(ItemLoader.lookup("Diamond ring").id(),ItemLoader.lookup("Ring of life").id(), 57),
    DIAMOND_BRACELET(ItemLoader.lookup("Diamond bracelet").id(), ItemLoader.lookup("Phoenix necklace").id(), 57),
    DIAMOND_NECKLACE(ItemLoader.lookup("Diamond necklace").id(), ItemLoader.lookup("Abyssal bracelet").id(), 57),
    DIAMOND_AMULET(ItemLoader.lookup("Diamond amulet").id(),ItemLoader.lookup("Amulet of power").id(), 57);

    private int unenchantedID;
    private int enchantedID;
    private int levelReq;


    Level_4(int unenchantedID, int enchantedID, int levelReq){
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

