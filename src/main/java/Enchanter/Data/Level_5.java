package Enchanter.Data;

import org.powbot.mobile.rscache.loader.ItemLoader;

public enum Level_5 implements Enchantable{
    DEFAULT_VALUE(1645  ,11980 , 68),
    DRAGONSTONE_RING(1645  ,11980 , 68),
    DRAGONSTONE_BRACELET(11115 ,11118  , 68),
    DRAGONSTONE_NECKLACE(1664  ,11105 , 68),
    DRAGONSTONE_AMULET(1702  ,1704 , 68);

    private final int unenchantedID;
    private final int enchantedID;
    private final int levelReq;


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
    public String getName() {
        return name();
    }


}

