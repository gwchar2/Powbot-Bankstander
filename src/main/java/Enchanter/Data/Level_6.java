package Enchanter.Data;

import org.powbot.mobile.rscache.loader.ItemLoader;

public enum Level_6 implements Enchantable{
    ONYX_RING(6575 ,6583 , 87),
    ONYX_BRACELET(11130  ,11133 , 87),
    ONYX_NECKLACE(6577 ,11128  , 87),
    ONYX_AMULET(6581 ,6585  , 87);

    private final int unenchantedID;
    private final int enchantedID;
    private final int levelReq;

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

